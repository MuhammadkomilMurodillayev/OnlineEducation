package uz.mm.repositories.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import uz.mm.entity.base.BaseGenericEntity;

import java.io.Serializable;

@NoRepositoryBean
public interface AbstractRepository<
        E extends BaseGenericEntity,
        K extends Serializable
        > extends JpaRepository<E, K>, BaseGenericRepository {
}