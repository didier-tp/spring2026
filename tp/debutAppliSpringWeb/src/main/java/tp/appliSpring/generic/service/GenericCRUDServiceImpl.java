package tp.appliSpring.generic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.generic.converter.GenericMapper;
import tp.appliSpring.generic.exception.EntityNotFoundException;
import tp.appliSpring.generic.model.WithIdAsString;


@Slf4j
//@Service
@Transactional
public class GenericCRUDServiceImpl<T extends WithIdAsString,E,ID> extends GenericRDServiceImpl<T,E,ID> implements GenericCRUDService<T> {

    public GenericCRUDServiceImpl(Class<T> modelClass ,
                                  Class<E> entityClass,
                                  Class<ID> pkClass,
                                  CrudRepository<E,ID> crudRepository,
                                  GenericMapper genericMapper){
        super(modelClass,entityClass,pkClass,crudRepository,genericMapper);
    }

    @Override
    public T create(T obj) {
        E entity = genericMapper.map(obj,modelClass,entityClass);
        E savedEntity = crudRepository.save(entity);
        return  genericMapper.map(savedEntity,entityClass,modelClass);
    }

    @Override
    public T update(T obj) throws EntityNotFoundException {
        ID id = genericMapper.map(obj.extractId(),pkClass);
        if(!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id="+id);
        E entity = genericMapper.map(obj,modelClass,entityClass);
        E updatedEntity = crudRepository.save(entity);
        return genericMapper.map(updatedEntity,entityClass,modelClass);
    }

}
