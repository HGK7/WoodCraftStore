package com.estore.api.estoreapi.model;

import java.beans.DesignMode;
import java.util.logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.estore.api.estoreapi.model.Product;


public class CartItem {
    private static final Logger LOG = Logger.getLogger(Account.class.getName());

    static final String STRING_FORMAT = "CartItem [id=%d, name=%s, woodType=%s, total price=%d, quantity=%d]";

    @JsonProperty("id") private int id;
    @JsonProperty("woodType") private String woodType;
    @JsonProperty("name") private String name;
    @JsonProperty("quantity") private int quantity;
    @JsonProperty("price") private int price;
    
    public CartItem(@JsonProperty("id") int id,@JsonProperty("name") String name,@JsonProperty("woodType") String woodType,@JsonProperty("price") int price) {

        this.id = id;
        this.name = name;
        this.woodType = woodType;
        this.quantity = 1;
        this.price = price;
    }
    //**Id Getter */
    public int getProductId() {
        return id;
    }

    //**name Getter */
    public String getName(){
        return name;
    }
    
    //**woodtype Getter */
    public String getWoodType() {
        return woodType;
    }

    //**quantity Getter */
    public int getQuantity() {
        return quantity;
    }

    //**quantity Setter */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //**price Getter */
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format(STRING_FORMAT,id,name,woodType,price,quantity);
    }

}
