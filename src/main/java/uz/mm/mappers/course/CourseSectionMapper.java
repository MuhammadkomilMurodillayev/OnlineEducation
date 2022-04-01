package uz.mm.mappers.course;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.mm.dto.course.CourseSectionCreateDto;
import uz.mm.dto.course.CourseSectionDto;
import uz.mm.dto.course.CourseSectionUpdateDto;
import uz.mm.entity.courses.Section;
import uz.mm.mappers.base.AbstractMapper;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public class CourseSectionMapper
        implements AbstractMapper<Section, CourseSectionDto, CourseSectionCreateDto, CourseSectionUpdateDto> {
    @Override
    public CourseSectionDto toDto(Section entity) {
        return null;
    }

    @Override
    public List<CourseSectionDto> toDto(List<Section> entities) {
        return null;
    }

    @Override
    public Section fromCreateDto(CourseSectionCreateDto createDto) {
        return null;
    }

    @Override
    public Section fromUpdateDto(CourseSectionUpdateDto updateDto) {
        return null;
    }
}
