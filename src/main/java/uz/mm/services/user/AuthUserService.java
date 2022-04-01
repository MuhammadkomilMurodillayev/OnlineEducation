package uz.mm.services.user;

import uz.mm.dto.user.UserCreateDto;
import uz.mm.dto.user.UserDto;
import uz.mm.dto.user.UserUpdateDto;
import uz.mm.services.base.GenericCrudService;

public interface AuthUserService extends GenericCrudService<
        UserDto,
        UserCreateDto,
        UserUpdateDto,
        String
        > {

    }