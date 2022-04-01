package uz.mm.validators;

import uz.mm.dto.BaseGenericDto;
import uz.mm.dto.GenericDto;
import uz.mm.exeptions.ValidationException;

import java.io.Serializable;

public abstract class AbstractValidator<CD extends BaseGenericDto, UD extends GenericDto, K extends Serializable> implements BaseGenericValidator {

    public abstract void validateKey(K id) throws
            ValidationException;

    public abstract void validOnCreate(CD cd) throws ValidationException;

    public abstract void validOnUpdate(UD cd) throws ValidationException;


}

