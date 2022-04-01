package uz.mm.mappers.user;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.mm.dto.user.UserCreateDto;
import uz.mm.dto.user.UserDto;
import uz.mm.dto.user.UserUpdateDto;
import uz.mm.entity.user.AuthUser;
import uz.mm.mappers.base.AbstractMapper;

@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends AbstractMapper<AuthUser, UserDto, UserCreateDto, UserUpdateDto> {
}
