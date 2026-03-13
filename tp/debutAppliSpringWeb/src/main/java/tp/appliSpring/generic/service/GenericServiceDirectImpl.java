package tp.appliSpring.generic.service;

import tp.appliSpring.generic.exception.EntityNotFoundException;
import tp.appliSpring.generic.model.WithId;

import org.springframework.data.repository.CrudRepository;
import tp.appliSpring.generic.converter.GenericMapper;

import java.util.List;
import java.util.Optional;

/*
implementation en s'appuyant directement sur le dao/repository
(sans abstraction spi.Loader/Saver et sans adaptateur de persistance)
 */

public class GenericServiceDirectImpl<T extends WithId,E,ID> implements GenericService<T,ID> {

    private Class<T> modelClass;
    private Class<E> entityClass ;
    private CrudRepository<E,ID> crudRepository;
    protected GenericMapper genericMapper;

    public GenericServiceDirectImpl(Class<T> modelClass ,
                                    Class<E> entityClass,
                                    CrudRepository<E,ID> crudRepository,
                                    GenericMapper genericMapper){
        this.modelClass=modelClass;
        this.entityClass=entityClass;
        this.crudRepository=crudRepository;
        this.genericMapper=genericMapper;
    }

    @Override
    public Optional<T> findById(ID id) {
        Optional<E> optionalE = crudRepository.findById(id);
        if(optionalE.isPresent())
            return Optional.of(genericMapper.map(optionalE.get(), modelClass));
        else
            return Optional.empty();
    }

    @Override
    public T searchById(ID id) throws EntityNotFoundException {
        if(!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id="+id);
        E e = crudRepository.findById(id).get();
        return genericMapper.map(e, modelClass);
    }

    @Override
    public List<T> searchAll() {
        List<E> entityList = (List<E>)crudRepository.findAll();
        return genericMapper.map(entityList, modelClass);
    }

    @Override
    public T create(T obj) {
        E entity = genericMapper.map(obj,entityClass);
        E savedEntity = crudRepository.save(entity);
        return  genericMapper.map(savedEntity,modelClass);
    }

    @Override
    public T update(T obj) throws EntityNotFoundException {
        ID id = (ID) obj.extractId();
        if(!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id="+id);
        E entity = genericMapper.map(obj,entityClass);
        E updatedEntity = crudRepository.save(entity);
        return genericMapper.map(updatedEntity,modelClass);
    }

    @Override
    public void removeById(ID id) throws EntityNotFoundException {
        if(!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id="+id);
        crudRepository.deleteById(id);
    }
}
