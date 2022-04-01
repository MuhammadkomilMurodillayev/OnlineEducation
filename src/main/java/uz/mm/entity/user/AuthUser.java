package uz.mm.entity.user;

import lombok.*;
import uz.mm.entity.base.Auditable;
import uz.mm.enums.Role;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name = "auth_users", indexes = {
        @Index(name = "auth_user_status_index", columnList = "status"),
        @Index(name = "auth_user_email_index", columnList = "email"),
        @Index(name = "auth_user_phone_index", columnList = "phone")
})
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends Auditable {

    private String fullName;

    @Column(nullable = false,unique = true)
    private String username;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private Double balance;

    private String photo_url;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Builder(builderMethodName = "childBuilder")

    public AuthUser(String id, LocalDateTime createdAt, LocalDateTime updatedAt, boolean deleted, Short status, String fullName, String username, String password, Role role, Double balance, String photo_url, String email, String phone) {
        super(id, createdAt, updatedAt, deleted, status);
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
        this.photo_url = photo_url;
        this.email = email;
        this.phone = phone;
    }
}
