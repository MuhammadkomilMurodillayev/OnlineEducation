package uz.mm.validators.course;

import org.springframework.stereotype.Component;
import uz.mm.dto.course.CourseSectionCreateDto;
import uz.mm.dto.course.CourseSectionUpdateDto;
import uz.mm.exeptions.ValidationException;
import uz.mm.validators.AbstractValidator;

@Component
public class CourseSectionValidator
        extends AbstractValidator<
        CourseSectionCreateDto,
        CourseSectionUpdateDto,
        String> {
    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(CourseSectionCreateDto courseSectionCreateDto) throws ValidationException {

    }

    @Override
    public void validOnUpdate(CourseSectionUpdateDto cd) throws ValidationException {

    }
}
