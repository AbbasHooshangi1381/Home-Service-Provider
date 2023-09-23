package repositories;

import domain.Permission;
import domain.User;
@SuppressWarnings("unused")

public interface PermissionRepository {
    Permission[] findall();

    Permission findByIRd(Long id);


    Permission save(Permission permission);

    Permission update(Permission permission);





}
