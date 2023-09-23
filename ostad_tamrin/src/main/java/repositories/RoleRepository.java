package repositories;

import domain.Role;
import domain.User;
@SuppressWarnings("unused")

public interface RoleRepository {
    Role[] findall();

    Role findByIRd(Long id);


    Role save(Role role);

    Role update(Role role);


}
