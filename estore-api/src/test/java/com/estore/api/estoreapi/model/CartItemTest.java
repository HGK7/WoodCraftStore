package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Account class
 *
 */
@Tag("Model-tier")
public class CartItemTest {

    //This Test tests all methods except SetQuantity and CartItem.toString
    //Hrishikesh Kumar, Reviewed and updated by Khaled
    @Test
    public void testGetters() {

        // Setup
        int expectedId = 1;
        String expectedName = "Cat";
        String expectedWoodType = "Birch";
        int expectedPrice = 10;
        int expectedQuantity = 1;

        // Invoke
        CartItem cartItem=new CartItem(1,"Cat", "Birch", 10);


        // Analyze
        assertEquals(cartItem.getProductId(),expectedId);
        assertEquals(cartItem.getPrice(),expectedPrice);
        assertEquals(cartItem.getName(),expectedName);
        assertEquals(cartItem.getQuantity(),expectedQuantity);
        assertEquals(cartItem.getWoodType(),expectedWoodType);

    }
    
    //Hrishikesh Kumar
    @Test
    public void TestSetQuantity() {

         // Setup
         int expectedQuantity = 20; 

         // Invoke
         CartItem cartItem=new CartItem(1,"Cat", "Birch", 20);
         cartItem.setQuantity(20);

         //Analyze
         assertEquals(cartItem.getQuantity(),expectedQuantity);

    }

    //Test for CartItem.toString
    //Khaled 
    @Test
    public void testToString(){
        //Setup
        String expectedString = "CartItem [id=1, name=Cat, woodType=Birch, total price=20, quantity=1]";

        //invoke
        CartItem cartItem=new CartItem(1,"Cat", "Birch", 20);
        String actualString = cartItem.toString();
    }


}
