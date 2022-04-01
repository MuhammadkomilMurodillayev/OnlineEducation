package uz.mm.services.base;

import uz.mm.dto.BaseGenericDto;
import uz.mm.dto.GenericDto;

import java.io.Serializable;

public interface GenericCrudService<
        D extends GenericDto,
        CD extends BaseGenericDto,
        UD extends GenericDto,
        K extends Serializable
        > extends GenericService<D, K> {

    K create(CD createDto);

    void delete(K id);

    void update(UD updateDto);
}
