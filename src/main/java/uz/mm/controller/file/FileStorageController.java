package uz.mm.controller.file;


import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.mm.controller.AbstractController;
import uz.mm.dto.file.FileStorageCreateDto;
import uz.mm.dto.file.FileStorageDto;
import uz.mm.services.file.FileStorageService;

import java.util.List;

@RestController
@RequestMapping(value = "/file/")
public class FileStorageController extends AbstractController<FileStorageService> {

    public FileStorageController(FileStorageService service) {
        super(service);
    }

    @PostMapping(value = "upload/{lessonId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public void store(@PathVariable(name = "lessonId") String lessonId, @RequestBody MultipartFile multipartFile) {

        FileStorageCreateDto dto = new FileStorageCreateDto();
        dto.setFile(multipartFile);
        dto.setLessonId(lessonId);
        String fileId = service.create(dto);

        System.out.print(fileId);
    }

    @GetMapping(value = "load/{id}")
    public ResponseEntity<FileStorageDto> getFile(@PathVariable(name = "id") String id) {
        FileStorageDto dto = service.get(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "loadAll")
    public ResponseEntity<List<FileStorageDto>> getAllFile() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping(value = "loadAsResource/{id}")
    public ResponseEntity<Resource> getFileAsResource(@PathVariable(name = "id") String id) {

        FileStorageDto fileStorageDto = service.getAsResource(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileStorageDto.getOriginalName() + "\"").body(fileStorageDto.getFile());
    }

    @DeleteMapping(value = "delete/{id}")
    public void deleteAll(@PathVariable(name = "id") String id) {
        service.delete(id);
    }

}
