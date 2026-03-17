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
public class GenericRDServiceImpl<T extends WithIdAsString,E,ID> extends GenericRServiceImpl<T,E,ID> implements GenericRDService<T> {

    public GenericRDServiceImpl(Class<T> modelClass ,
                                Class<E> entityClass,
                                Class<ID> pkClass,
                                CrudRepository<E,ID> crudRepository,
                                GenericMapper genericMapper){
        super(modelClass,entityClass,pkClass,crudRepository,genericMapper);
    }


    @Override
    public void removeById(String sId) throws EntityNotFoundException {
        ID id = (ID) genericMapper.map(sId,pkClass);
        if(!crudRepository.existsById(id))
            throw new EntityNotFoundException("entity not found with id="+id);
        crudRepository.deleteById(id);
    }
}
