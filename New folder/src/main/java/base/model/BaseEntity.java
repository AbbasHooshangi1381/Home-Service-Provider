package base.model;

import java.io.Serializable;

public class BaseEntity<ID extends Serializable> {
    private ID id;


    public BaseEntity(ID id) {
        this.id = id;
    }


    public int getId() {
        return (int) id;
    }

    public void setId(ID id) {
        this.id = id;
    }




    ////////////////////////////////



    ///////////////////////////////////

}
