package uz.mm.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.mm.entity.user.UserActivationCode;

public interface UserActivationCodeRepository extends JpaRepository<UserActivationCode, String> {

    @Query(value = "select * from main.user_activation_code au  where au.activation_code = ?1 and au.email = ?2 and au.used_code = 0",nativeQuery = true)
    UserActivationCode checkingCode(String activationCode, String email);
}
