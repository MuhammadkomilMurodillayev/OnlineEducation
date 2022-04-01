package uz.mm.services.base;

import uz.mm.dto.BaseGenericDto;

import java.io.Serializable;
import java.util.List;

public interface GenericService <
        D extends BaseGenericDto,
        K extends Serializable
        > extends BaseGenericService{

    D get(K id);

    List<D> getAll();
}