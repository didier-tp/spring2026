package tp.appliSpring.bank.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import tp.appliSpring.bank.persistence.entity.UserEntity;

/*
public interface UserRepository
        extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    //complementary inheritance of JpaSpecificationExecutor<T>
}
*/
public interface UserRepository
        extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> , PagingAndSortingRepository<UserEntity,Long> {
    //complementary inheritance of JpaSpecificationExecutor<T>
}