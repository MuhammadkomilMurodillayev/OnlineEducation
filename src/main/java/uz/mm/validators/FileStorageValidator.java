package uz.mm.validators;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.mm.exeptions.fileStore.FileStorageException;

@Component
public class FileStorageValidator implements BaseGenericValidator {
    public void checkVideoFile(MultipartFile multipartFile) {
        if (!multipartFile.getContentType().startsWith("video")) {
            throw new FileStorageException("this content invalid, please input video content");
        }
    }
}
