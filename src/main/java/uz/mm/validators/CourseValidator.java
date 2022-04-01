package uz.mm.validators;

import org.springframework.stereotype.Component;
import uz.mm.dto.course.CourseCreateDto;
import uz.mm.dto.course.CourseUpdateDto;
import uz.mm.exeptions.ValidationException;

@Component
public class CourseValidator extends AbstractValidator<
        CourseCreateDto,
        CourseUpdateDto,
        String> {

    @Override
    public void validateKey(String id) throws ValidationException {

    }

    @Override
    public void validOnCreate(CourseCreateDto courseCreateDto) throws ValidationException {


    }

    @Override
    public void validOnUpdate(CourseUpdateDto cd) throws ValidationException {

    }
}
