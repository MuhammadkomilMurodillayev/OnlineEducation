package uz.mm.controller;

import uz.mm.services.base.BaseGenericService;

public abstract class AbstractController<S extends BaseGenericService> {
    protected final S service;
    protected final String API = "/api";
    protected final String VERSION = "/v1";
    protected final String PATH = API + VERSION;



    public AbstractController(S service) {
        this.service = service;
    }
}

