package uz.mm.dto.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.mm.dto.BaseGenericDto;


@Getter
@Setter
public class FileStorageCreateDto implements BaseGenericDto {

    String lessonId;

    MultipartFile file;

}
