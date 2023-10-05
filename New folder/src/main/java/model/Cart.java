package model;

import base.model.BaseEntity;

public class Cart extends BaseEntity<Integer> {
    private Integer product_id;
    private Integer cart_id;
    private Integer count;
    private Integer user_id;





    public Cart(  Integer product_id, Integer count , int userId) {
        super(product_id);
        this.product_id = product_id;
        this.count = count;
        this.user_id = userId;
    }


    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(Integer cart_id) {
        this.cart_id = cart_id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
