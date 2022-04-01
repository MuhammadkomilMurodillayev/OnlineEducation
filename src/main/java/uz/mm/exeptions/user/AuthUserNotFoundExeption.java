package uz.mm.exeptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthUserNotFoundExeption extends RuntimeException {
    AuthUserNotFoundExeption(String message) {
        super(message);
    }
}
