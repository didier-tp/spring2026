package tp.appliSpring.generic.service;

import tp.appliSpring.generic.exception.EntityNotFoundException;
import tp.appliSpring.generic.model.WithIdAsString;

import java.util.List;
import java.util.Optional;

/*
NB: T is a model type (DTO)
inside T there is an id field (of type String : ok for mongoDB or convert from Long in SQL DB)
ID is the type of the identifier in persistent entity (ex: Long, String, ...)
 */

public interface GenericRService<T extends WithIdAsString> {
    public Optional<T> findById(String sId);
    public T searchById(String sId)throws EntityNotFoundException;
    public List<T> searchAll();
}
