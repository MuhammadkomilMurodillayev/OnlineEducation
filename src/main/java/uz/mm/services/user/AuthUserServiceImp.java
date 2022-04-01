package uz.mm.services.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.mm.dto.auth.AuthUserDto;
import uz.mm.dto.auth.SessionDto;
import uz.mm.dto.responce.AppErrorDto;
import uz.mm.dto.responce.DataDto;
import uz.mm.dto.user.UserCreateDto;
import uz.mm.dto.user.UserDto;
import uz.mm.dto.user.UserUpdateDto;
import uz.mm.entity.user.AuthUser;
import uz.mm.entity.user.UserActivationCode;
import uz.mm.enums.Role;
import uz.mm.exeptions.user.AuthUserEmailAlreadyTakenExeption;
import uz.mm.mappers.user.AuthUserMapper;
import uz.mm.properties.ServerProperties;
import uz.mm.repositories.auth.AuthUserRepository;
import uz.mm.repositories.auth.UserActivationCodeRepository;
import uz.mm.services.base.AbstractService;
import uz.mm.validators.user.AuthUserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AuthUserServiceImp extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator> implements AuthUserService, UserDetailsService {

    private final JavaMailSender javaMailSender;
    private final ServerProperties serverProperties;
    private final ObjectMapper objectMapper;
    private final UserActivationCodeRepository userActivationCodeRepository;

    @Autowired
    public AuthUserServiceImp(@Lazy AuthUserMapper mapper,
                              AuthUserValidator validator,
                              AuthUserRepository repository,
                              JavaMailSender javaMailSender,
                              ServerProperties serverProperties,
                              ObjectMapper objectMapper, UserActivationCodeRepository userActivationCodeRepository) {
        super(mapper, validator, repository);
        this.javaMailSender = javaMailSender;
        this.serverProperties = serverProperties;
        this.objectMapper = objectMapper;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    public ResponseEntity<DataDto<SessionDto>> getToken(AuthUserDto dto) {

        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(serverProperties.getServerUrl() + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("success") && json_auth.get("success").asBoolean()) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new DataDto<>(sessionDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new DataDto<>(objectMapper.readValue(json_auth.get("error").toString(),
                    AppErrorDto.class)), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .message(e.getLocalizedMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        }
    }

    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.findByUsernameAndDeletedFalse(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities("ROLE_" + user.getRole())
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }


    public void blocked(String id) {
        repository.blocked(id);
    }

    @Override
    public String create(UserCreateDto createDto) {

        AuthUser authUser = repository.existsByEmailOrUsername(createDto.getEmail(), createDto.getUsername());

        if (Objects.nonNull(authUser))
            throw new AuthUserEmailAlreadyTakenExeption("Bad request !!!");

        String random = UUID.randomUUID().toString();
        authUser = mapper.fromCreateDto(createDto);
        authUser.setId(UUID.randomUUID().toString());
        authUser.setRole(Role.USER);
        authUser.setStatus((short) 110);
        AuthUser save = repository.save(authUser);

        UserActivationCode userActivationCode = new UserActivationCode(save.getId(), random, save.getEmail());
        userActivationCodeRepository.save(userActivationCode);

        sendEmail(createDto.getEmail(), random);

        return "Account created. You can activated account with email !!!";
    }

    @Override
    public void delete(String id) {
        repository.deleted(id);
    }

    @Override
    public void update(UserUpdateDto updateDto) {

    }

    @Override
    public UserDto get(String id) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        return null;
    }

    public String verifyEmail(String activationCode, String email) {
        //Todo checked time
        UserActivationCode userActivationCode = userActivationCodeRepository.checkingCode(activationCode, email);

        if (Objects.isNull(userActivationCode))
            return "No Activation";

        repository.changeStatus(userActivationCode.getUserId());
        return "Activation";
    }

    @Async
    public boolean sendEmail(String sendingEmail, String key) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("boom_academy@gmail.com");
            message.setTo(sendingEmail);
            message.setSubject("SECURITY REGISTRATION");
            message.setText("<a href='http://localhost:8080/api/auth/verifyEmail?activationCode=" + key + "&email=" + sendingEmail + "'>Confirmation</a>");
            System.out.println("message = " + "<a href='http://localhost:8080/api/auth/verifyEmail?activationCode=" + key + "&email=" + sendingEmail + "'>Confirmation</a>");
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

