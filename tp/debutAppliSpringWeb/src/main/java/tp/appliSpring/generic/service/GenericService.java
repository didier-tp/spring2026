package tp.appliSpring.generic.service;

import tp.appliSpring.generic.exception.EntityNotFoundException;
import tp.appliSpring.generic.model.WithId;

import java.util.List;
import java.util.Optional;

public interface GenericService<T extends WithId,ID> {
    public Optional<T> findById(ID id);
    public T searchById(ID id)throws EntityNotFoundException;
    public List<T> searchAll();
    public T create(T obj);
    public T update(T obj)throws EntityNotFoundException;
    public void removeById(ID id)throws EntityNotFoundException;
}
