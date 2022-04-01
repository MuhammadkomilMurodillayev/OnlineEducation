package uz.mm.dto.lesson;

import org.springframework.web.multipart.MultipartFile;
import uz.mm.dto.BaseGenericDto;

public class LessonCreateDto implements BaseGenericDto {
    MultipartFile videoFile;
}
