package uz.mm.services.base;

import uz.mm.mappers.base.BaseGenericMapper;
import uz.mm.repositories.base.BaseGenericRepository;
import uz.mm.validators.BaseGenericValidator;

public abstract class AbstractService<
        R extends BaseGenericRepository,
        M extends BaseGenericMapper,
        V extends BaseGenericValidator
        > implements BaseGenericService {

    protected final M mapper;
    protected final V validator;
    protected final R repository;

    protected AbstractService(M mapper, V validator, R repository) {
        this.mapper = mapper;
        this.validator = validator;
        this.repository = repository;
    }
}