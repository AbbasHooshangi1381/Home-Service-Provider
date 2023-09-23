package repositories;

import base.repository.BaseEntityRepository;
import domain.User;
@SuppressWarnings("unused")
public interface UserRepository extends BaseEntityRepository {
    User findByUsername(String name);

    User [] findByUsernameAndLastname(String username);

    boolean existByUsername(String username);






}
