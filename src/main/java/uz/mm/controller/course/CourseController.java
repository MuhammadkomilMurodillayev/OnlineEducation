package uz.mm.controller.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mm.controller.AbstractController;
import uz.mm.dto.course.CourseCreateDto;
import uz.mm.dto.course.CourseDto;
import uz.mm.dto.responce.DataDto;
import uz.mm.services.course.CourseService;


@RestController(value = "/course/")
public class CourseController extends AbstractController<CourseService> {


    public CourseController(CourseService service) {
        super(service);
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<DataDto<CourseDto>> get(@PathVariable String id) {
        return null;
    }

    @PostMapping("create")
    public ResponseEntity<DataDto<String>> create(@RequestBody CourseCreateDto dto){
        String id = service.create(dto);
        return new ResponseEntity<>(new DataDto<>(id), HttpStatus.OK);
    }

}
