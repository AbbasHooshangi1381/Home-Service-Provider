package base.repository;

import base.domain.Entity;

public interface BaseEntityRepository {
    Entity [] findall();
    Entity findById(Long id);
    Void deleteById(Long id);
    Long count();
    Entity save(Entity entity);
    Entity update(Entity entity);
    Boolean existById(Long id);





}
