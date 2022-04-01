package uz.mm.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mm.controller.AbstractController;
import uz.mm.dto.auth.AuthUserDto;
import uz.mm.dto.auth.SessionDto;
import uz.mm.dto.responce.DataDto;
import uz.mm.dto.user.UserCreateDto;
import uz.mm.services.user.AuthUserServiceImp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class AuthUserController extends AbstractController<AuthUserServiceImp> {

    public AuthUserController(AuthUserServiceImp service) {
        super(service);
    }

    @GetMapping(value = PATH + "/auth/register")
    public ResponseEntity<String> create(@ModelAttribute UserCreateDto createDto) {
        String str = service.create(createDto);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @GetMapping(value = "api/auth/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestParam String activationCode, @RequestParam String email) {
        String str = service.verifyEmail(activationCode, email);
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/auth/token", method = RequestMethod.POST)
    public ResponseEntity<DataDto<SessionDto>> token(@RequestBody AuthUserDto dto) {
        return service.getToken(dto);
    }

    @RequestMapping(value = PATH + "/auth/refresh-token", method = RequestMethod.GET)
    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return service.refreshToken(request, response);
    }

    @RequestMapping(value = PATH + "/auth/deleted/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> deleted(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = PATH + "/auth/blocked/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> blocked(@PathVariable String id) {
        service.blocked(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
