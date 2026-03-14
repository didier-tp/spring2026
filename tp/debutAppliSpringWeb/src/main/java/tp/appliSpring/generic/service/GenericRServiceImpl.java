package tp.appliSpring.generic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import tp.appliSpring.generic.converter.GenericMapper;
import tp.appliSpring.generic.exception.EntityNotFoundException;
import tp.appliSpring.generic.model.WithIdAsString;

import java.util.List;
import java.util.Optional;


@Slf4j
//@Service
@Transactional
public class GenericRServiceImpl<T extends WithIdAsString,E,ID> implements GenericRService<T> {

    protected Class<T> modelClass;
    protected Class<E> entityClass ;
    protected Class<ID> pkClass ;
    protected CrudRepository<E,ID> crudRepository;
    protected GenericMapper genericMapper;

    public GenericRServiceImpl(Class<T> modelClass ,
                               Class<E> entityClass,
                               Class<ID> pkClass,
                               CrudRepository<E,ID> crudRepository,
                               GenericMapper genericMapper){
        this.modelClass=modelClass;
        this.entityClass=entityClass;
        this.pkClass=pkClass;
        this.crudRepository=crudRepository;
        this.genericMapper=genericMapper;
    }

    @Override
    public Optional<T> findById(String sId) {
        ID id = (ID) genericMapper.map(sId,pkClass);
        log.debug("GenericServiceDirectImpl.findById , sId="+sId+" mapped to id="+id + " of type "+pkClass.getName());
        Optional<E> optionalE = crudRepository.findById(id);
        if(optionalE.isPresent())
            return Optional.of(genericMapper.map(optionalE.get(), entityClass,modelClass));
        else
            return Optional.empty();
    }

    @Override
    public T searchById(String sId) throws EntityNotFoundException {
        ID id = (ID) genericMapper.map(sId,pkClass);
        if(!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id="+id);
        E e = crudRepository.findById(id).get();
        return genericMapper.map(e, entityClass,modelClass);
    }

    @Override
    public List<T> searchAll() {
        List<E> entityList = (List<E>)crudRepository.findAll();
        return genericMapper.map(entityList, entityClass,modelClass);
    }
}
