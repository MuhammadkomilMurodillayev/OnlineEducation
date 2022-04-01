package uz.mm.mappers.course;

import org.mapstruct.Mapper;
import uz.mm.dto.course.CourseCreateDto;
import uz.mm.dto.course.CourseDto;
import uz.mm.dto.course.CourseUpdateDto;
import uz.mm.entity.courses.Course;
import uz.mm.mappers.base.AbstractMapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper extends AbstractMapper<
        Course,
        CourseDto,
        CourseCreateDto,
        CourseUpdateDto> {

    @Override
    CourseDto toDto(Course entity);

    @Override
    List<CourseDto> toDto(List<Course> entities);

    @Override
    Course fromCreateDto(CourseCreateDto createDto);

    @Override
    Course fromUpdateDto(CourseUpdateDto updateDto);
}
