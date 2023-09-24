package role;
@SuppressWarnings("unused")
public class Product {

    private int id;
    private String name;
    private String createDate;
    private Category category_id;
    private Brand brand_id;

    public Product() {
    }

    public Product(int id, String name, String createDate, Category category_id, Brand brand_id) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
        this.category_id = category_id;
        this.brand_id = brand_id;
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

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public Brand getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Brand brand_id) {
        this.brand_id = brand_id;
    }
}
