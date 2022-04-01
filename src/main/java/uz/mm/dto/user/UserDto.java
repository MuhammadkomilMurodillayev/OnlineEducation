package uz.mm.dto.user;

import lombok.Getter;
import lombok.Setter;
import uz.mm.dto.GenericDto;

@Getter
@Setter
public class UserDto extends GenericDto {
    private String fullName;

    private String username;

    private String password;

    private String role_id;

    private Double balance;

    private String photo_url;

    private String email;

    private String phone;
}

