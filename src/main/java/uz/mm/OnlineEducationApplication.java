package uz.mm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.mm.entity.user.AuthUser;
import uz.mm.enums.Role;
import uz.mm.properties.ServerProperties;
import uz.mm.properties.OpenApiProperty;
import uz.mm.repositories.auth.AuthUserRepository;

import java.util.UUID;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties({
        ServerProperties.class, OpenApiProperty.class
})
public class OnlineEducationApplication {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public OnlineEducationApplication(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(OnlineEducationApplication.class, args);
    }

//    @Bean
    CommandLineRunner runner() {
        return (args) -> {
            authUserRepository.deleteAll();
            String encode = passwordEncoder.encode("123");
            System.out.println("encode = " + encode);
            AuthUser admin = AuthUser.childBuilder()
                    .id(UUID.randomUUID().toString())
                    .username("admin")
                    .password(encode)
                    .role(Role.ADMIN)
                    .status((short)0)
                    .fullName("adminov admin adminovich")
                    .phone("+998946651234")
                    .balance(0D)
                    .email("admin@gmail.com")
                    .build();
            authUserRepository.save(admin);
        };
    }


}
