package base.model;

import java.io.Serializable;

public class BaseEntity<ID extends Serializable> {
    private ID id;

    public BaseEntity(ID id) {
        this.id = id;
    }

    public BaseEntity(Number id, String name, Double price, Integer stock) {

    }

    public int getId() {
        return (int) id;
    }

    public void setId(ID id) {
        this.id = id;
    }




    ////////////////////////////////

    public String getName() {
        return this.getName();


    }

    public String getPrice() {
        return this.getPrice();

    }


    ///////////////////////////////////

}
