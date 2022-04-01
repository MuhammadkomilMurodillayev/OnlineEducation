package uz.mm.services.course;

import org.springframework.stereotype.Service;
import uz.mm.dto.course.CourseCreateDto;
import uz.mm.dto.course.CourseDto;
import uz.mm.dto.course.CourseUpdateDto;
import uz.mm.entity.courses.Course;
import uz.mm.mappers.course.CourseMapper;
import uz.mm.repositories.course.CourseRepository;
import uz.mm.services.base.AbstractService;
import uz.mm.services.base.GenericCrudService;
import uz.mm.validators.CourseValidator;

import java.util.List;

@Service
public class CourseService extends AbstractService<
        CourseRepository,
        CourseMapper,
        CourseValidator>
        implements GenericCrudService<
        CourseDto,
        CourseCreateDto,
        CourseUpdateDto,
                String> {

    protected CourseService(CourseMapper mapper, CourseValidator validator, CourseRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(CourseCreateDto createDto) {
        validator.validOnCreate(createDto);
        Course course = mapper.fromCreateDto(createDto);
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(CourseUpdateDto updateDto) {

    }

    @Override
    public CourseDto get(String id) {
        return null;
    }

    @Override
    public List<CourseDto> getAll() {
        return null;
    }
}
