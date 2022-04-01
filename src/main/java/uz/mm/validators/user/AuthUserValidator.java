package uz.mm.validators.user;

import org.springframework.stereotype.Component;
import uz.mm.dto.user.UserCreateDto;
import uz.mm.dto.user.UserUpdateDto;
import uz.mm.exeptions.ValidationException;
import uz.mm.validators.AbstractValidator;

@Component
public class AuthUserValidator extends AbstractValidator<UserCreateDto, UserUpdateDto, String> {

    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(UserCreateDto userCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(UserUpdateDto cd) throws ValidationException {

    }
}

