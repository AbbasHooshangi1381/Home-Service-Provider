package repositories;

import domain.User;
@SuppressWarnings("unused")
public interface UserRepository {
    User[] findall();

    User findByIRd(Long id);

    Void deleteById(Long id);

    User save(User user);

    User update(User user);

    long count();




}
