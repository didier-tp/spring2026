package tp.appliSpring.generic.service;

import tp.appliSpring.generic.exception.EntityNotFoundException;
import tp.appliSpring.generic.model.WithIdAsString;


/*
NB: T is a model type (DTO)
inside T there is an id field (of type String : ok for mongoDB or convert from Long in SQL DB)
ID is the type of the identifier in persistent entity (ex: Long, String, ...)
 */

public interface GenericCRUDService<T extends WithIdAsString>  extends GenericRDService<T> {
    public T create(T obj);
    public T update(T obj)throws EntityNotFoundException;
}
