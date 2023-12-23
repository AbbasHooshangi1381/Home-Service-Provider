package base.repository;

import base.domin.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface BaseEntityRepository<ID extends Serializable, T extends BaseEntity<ID>> {

    T save(T entity);

    void deleteById(ID id);

    Collection<T> findAll();

    Optional<T> login(String username, String password);

    Optional<T> findById(ID id);

    List<T> saveAll(List<T> entities);

    void beginTransaction();

    void commitTransaction();

    void rollBack();
}
