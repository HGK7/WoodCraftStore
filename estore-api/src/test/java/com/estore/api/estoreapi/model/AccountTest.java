package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Account class
 *
 */
@Tag("Model-tier")
public class AccountTest {
    //hrishikesh
    @Test
    public void testCtor() {
        // Setup
        String expected_name = "testCtor";
        int expected_id = 10;
        ArrayList<CartItem> expected_cart = new ArrayList<CartItem>();

        // Invoke
        Account account = new Account(expected_name,expected_id);

        // Analyze
        assertEquals(expected_name,account.getName());
        assertEquals(expected_id,account.getId());
        assertEquals(expected_cart, account.getCart());
    }
    //hrishikesh
    @Test
    public void testName() {
        // Setup
        // Setup
        String expected_name = "testName";
        int expected_id = 10;

        // Invoke
        Account account = new Account(expected_name,expected_id);
        expected_name = "testNameChanged";

        // Invoke
        account.setName(expected_name);

        // Analyze
        assertEquals(expected_name,account.getName());
    }

    //Written By Hrishikesh, moved into right file by Khaled
    @Test
    public void testAddToCart() {

        // Setup
        CartItem expected_cart_item=new CartItem(1,"Cat", "Birch", 10);

        // Invoke
        Account account = new Account("Test",99);
        account.addToCart(1,"Cat","Birch",10);


        // Analyze
        assertEquals(expected_cart_item.getProductId(),account.getCart().get(0).getProductId());
        assertEquals(expected_cart_item.getPrice(),account.getCart().get(0).getPrice());
        assertEquals(expected_cart_item.getQuantity(),account.getCart().get(0).getQuantity());
        assertEquals(expected_cart_item.getWoodType(),account.getCart().get(0).getWoodType());

    }
    //hrishikesh
    @Test
    public void testToString() {
       // Setup
       String expected_name = "testName";
       int expected_id = 10;
       ArrayList<CartItem> expected_cart = new ArrayList<CartItem>();

       // Invoke
       Account account = new Account(expected_name,expected_id);
       String expected_string = String.format(Account.STRING_FORMAT,expected_name,expected_id,expected_cart.toString());

        // Invoke
        String actual_string = account.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }
}
