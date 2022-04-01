package uz.mm.repositories.file;

import org.springframework.stereotype.Repository;
import uz.mm.entity.file.FileStorage;
import uz.mm.repositories.base.AbstractRepository;

import java.util.Optional;

@Repository
public interface FileStorageRepository extends AbstractRepository<FileStorage, String> {

    Optional<FileStorage> findByIdAndDeletedFalse(String id);
}
