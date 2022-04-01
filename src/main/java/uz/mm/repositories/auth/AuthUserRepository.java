package uz.mm.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.mm.entity.user.AuthUser;
import uz.mm.repositories.base.BaseGenericRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, String>, BaseGenericRepository {

    Optional<AuthUser> findByUsernameAndDeletedFalse(String username);

    @Transactional
    @Modifying
    @Query(value = "update main.auth_users set is_deleted = 1 where id = ?1",nativeQuery = true)
    void deleted(String id);

    @Transactional
    @Modifying
    @Query(value = "update main.auth_users set status = 100 where id = ?1",nativeQuery = true)
    void blocked(String id);

    @Transactional
    @Modifying
    @Query(value = "update main.auth_users set status = 0 where id = ?1",nativeQuery = true)
    void changeStatus(String userId);

    @Query(value = "select * from main.auth_users au  where au.email = ?1 or au.username = ?2",nativeQuery = true)
    AuthUser existsByEmailOrUsername (String email, String username);
}
