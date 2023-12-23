package base.service;

import base.domin.BaseEntity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseEntityService<ID extends Serializable,T extends BaseEntity<ID>> {
    T save(T entity);

    void deleteById(ID id);

    Collection<T> findAll();

    Optional<T> findById(ID id);

    Optional<T> login(String username, String password);

    List<T> saveAll(List<T> entities);

    void beginTransaction();

    void commitTransaction();

    void rollBack();
}
