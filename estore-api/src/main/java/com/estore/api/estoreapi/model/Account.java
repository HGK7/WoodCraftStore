package com.estore.api.estoreapi.model;

import java.beans.DesignMode;
import java.util.ArrayList;
import java.util.logging.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.estore.api.estoreapi.model.Product;


/** Represents a Account entity */

public class Account {
    private static final Logger LOG = Logger.getLogger(Account.class.getName());

    // Package private for tests
    static final String STRING_FORMAT = "Account [name=%s, id=%d, cart=%s]";

    @JsonProperty("name") private String name;
    @JsonProperty("id") private int id;
    @JsonProperty("cart") private ArrayList<CartItem> cart;
    
    public Account(@JsonProperty("name") String name,@JsonProperty("id") int id) {
        this.name = name;
        this.id = id;
        this.cart = new ArrayList<CartItem>();
    }

    /**Id getter*/
    public int getId() {return id;}

    /**Name Setter and Getter*/
    public void setName(String name) {this.name = name;}

    public String getName() {return name;}

    /**Cart Getter */
    public ArrayList<CartItem> getCart() {
        return this.cart;
    }
    
    /**
     * adds an item to a cart by creating a new CartItem instance and adding it to this accounts cart
     *
     * @param id the id of the item being added to the cart
     * @param name The name of the item being added to the cart
     * @param woodType The wood type of the item being added to the cart
     * @param price The price of the item being added to the cart
     */    
    
     public void addToCart(int id, String name,String woodType,int price) {

        CartItem item=new CartItem(id, name, woodType,price);
        this.cart.add(item);
        
    }


    @Override
    public String toString() {
        return String.format(STRING_FORMAT,name,id,cart.toString());
    }

   
}
