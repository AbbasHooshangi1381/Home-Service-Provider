package domain;

import base.domain.Entity;

@SuppressWarnings("unused")
//Entity
public class Permission extends Entity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
