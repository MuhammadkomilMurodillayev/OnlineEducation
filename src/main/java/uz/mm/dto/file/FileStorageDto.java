package uz.mm.dto.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import uz.mm.dto.GenericDto;

@Getter
@Setter
public class FileStorageDto extends GenericDto {

    String lessonId;

    Resource file;

    String path;

    String originalName;

    String type;

}
