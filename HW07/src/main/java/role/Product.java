package role;

import java.util.Date;

@SuppressWarnings("unused")
public class Product {

    private int id;
    private String name;
    private String createDate;
    private Category category_id;
    private Brand brand_id;

    public Product() {
    }

    public Product(int id, String name, String createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

}
