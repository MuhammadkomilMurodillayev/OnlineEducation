package uz.mm.services.course;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.mm.dto.course.CourseSectionCreateDto;
import uz.mm.dto.course.CourseSectionDto;
import uz.mm.dto.course.CourseSectionUpdateDto;
import uz.mm.mappers.course.CourseSectionMapper;
import uz.mm.repositories.course.CourseSectionRepository;
import uz.mm.services.base.AbstractService;
import uz.mm.services.base.GenericCrudService;
import uz.mm.validators.course.CourseSectionValidator;

import java.util.List;

@Service
public class CourseSectionService extends AbstractService<
        CourseSectionRepository,
        CourseSectionMapper,
        CourseSectionValidator>
        implements GenericCrudService<
        CourseSectionDto,
        CourseSectionCreateDto,
        CourseSectionUpdateDto,
                String> {
    protected CourseSectionService(@Qualifier("courseSectionMapper") CourseSectionMapper mapper, CourseSectionValidator validator, CourseSectionRepository repository) {
        super(mapper, validator, repository);
    }

    @Override
    public String create(CourseSectionCreateDto createDto) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(CourseSectionUpdateDto updateDto) {

    }

    @Override
    public CourseSectionDto get(String id) {
        return null;
    }

    @Override
    public List<CourseSectionDto> getAll() {
        return null;
    }
}
