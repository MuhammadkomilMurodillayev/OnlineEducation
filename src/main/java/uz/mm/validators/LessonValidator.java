package uz.mm.validators;

import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class LessonValidator implements BaseGenericValidator {

    public boolean checkFile(MultipartFile multipartFile){
        return Objects.requireNonNull(multipartFile.getContentType()).startsWith("video");
    }
}
