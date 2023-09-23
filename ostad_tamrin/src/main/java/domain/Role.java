package domain;

import base.domain.Entity;

@SuppressWarnings("unused")
//Entity
public class Role extends Entity {

    private String name;
    //many to many
    private Permission[] permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Permission[] getPermissions() {
        return permissions;
    }

    public void setPermissions(Permission[] permissions) {
        this.permissions = permissions;
    }
}
