package com.estore.api.estoreapi.model;

import java.beans.DesignMode;
import java.util.logging.Logger;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Represents a Product entity */

public class Product {
    private static final Logger LOG = Logger.getLogger(Product.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Product [name=%s, id=%d, price=%d, woodType=%s, stock=%d]";

    @JsonProperty("name") private String name;
    @JsonProperty("id") private int id;
    @JsonProperty("price") private int price;
    @JsonProperty("woodType") private String[] woodType;
    @JsonProperty("stock") private int stock;
    
    public Product(@JsonProperty("name") String name, @JsonProperty("id") int id,@JsonProperty("price") int price, @JsonProperty("woodType") String[] woodType, @JsonProperty("stock") int stock ) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.woodType = woodType;
        this.stock = stock;
    }


    /**Id getter*/
    public int getId() {return id;}

    /**Name Setter and Getter*/
    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    /**Price Setter and Getter */
    public void setPrice(int price) {this.price = price;}

    public int getPrice() {return price;}

    /**Woodtype Setter and Getter*/
    public void setWoodType(String[] woodType) {this.woodType = woodType;}

    public String[] getWoodType() {return woodType;}

    /**Stock setter and getter*/
    public void setStock(int stock) {this.stock = stock;}
    public int getStock() {return stock;}

    /**inStock returns true if a product is in stock and false otherwise*/
    public boolean inStock(){return this.stock > 0;}

    
    @Override
    public String toString() {
        return String.format(STRING_FORMAT,name,id,price,woodType,stock);
    }
}