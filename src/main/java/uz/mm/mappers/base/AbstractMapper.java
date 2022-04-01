package uz.mm.mappers.base;

import uz.mm.dto.BaseGenericDto;
import uz.mm.dto.GenericDto;
import uz.mm.entity.base.BaseGenericEntity;

import java.util.List;

public interface AbstractMapper<
        E extends BaseGenericEntity,
        D extends GenericDto,
        CD extends BaseGenericDto,
        UP extends  GenericDto
        > extends BaseGenericMapper{
    D toDto(E entity);

    List<D> toDto(List<E> entities);

    E fromCreateDto(CD createDto);

    E fromUpdateDto(UP updateDto);
}