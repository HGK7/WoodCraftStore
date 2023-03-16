package com.estore.api.estoreapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * The unit test suite for the Product class
 *
 */
@Tag("Model-tier")
public class ProductTest {
    //hrishikesh
    @Test
    public void testCtor() {
        // Setup
        String expected_name = "testCtor";
        int expected_id = 10;
        int expected_price = 15;
        String[] expected_woodtype = new String[1];
        expected_woodtype[0] = "oak";
        int expected_stock = 10;

        // Invoke
        Product product = new Product(expected_name,expected_id,expected_price,expected_woodtype,expected_stock);

        // Analyze
        assertEquals(expected_name,product.getName());
        assertEquals(expected_id,product.getId());
        assertEquals(expected_price,product.getPrice());
        assertEquals(expected_woodtype,product.getWoodType());
        assertEquals(expected_stock,product.getStock());
    }
    //hrishikesh
    @Test
    public void testName() {
        // Setup
        int id = 99;
        String name = "testName";
        int expected_id = 10;
        int expected_price = 15;
        String[] expected_woodtype = new String[1];
        expected_woodtype[0] = "oak";
        int expected_stock = 10;

        Product product = new Product(name,expected_id,expected_price,expected_woodtype,expected_stock);

        String expected_name = "testNameChanged";

        // Invoke
        product.setName(expected_name);

        // Analyze
        assertEquals(expected_name,product.getName());
    }
    @Test
    public void testSetters() {
        // Setup
        String name = "testName";
        int id = 10;
        int price = 15;
        String[] woodType = new String[1];
        woodType[0] = "oak";
        int stock = 10;


        Product product = new Product(name,id,price,woodType,stock);

        String expected_name = "testNameChanged";
        int expected_price = 20;
        String[] expected_woodType = new String[1];
        expected_woodType[0] = "Birch";
        int expected_stock = 20;
        


        // Invoke
        product.setName(expected_name);
        product.setPrice(expected_price);
        product.setWoodType(expected_woodType);
        product.setStock(expected_stock);
        

        // Analyze
        assertEquals(expected_name,product.getName());
        assertEquals(expected_price,product.getPrice());
        assertEquals(expected_woodType,product.getWoodType());
        assertEquals(expected_stock,product.getStock());
    }
    @Test
    public void testInStock() {
        // Setup
        String expected_name = "testToString";
        int expected_id = 10;
        int expected_price = 15;
        String[] expected_woodtype = new String[1];
        expected_woodtype[0] = "oak";
        int expected_stock = 10;
        Product product = new Product(expected_name,expected_id,expected_price,expected_woodtype,expected_stock);
        String expected_string = String.format(Product.STRING_FORMAT,expected_name,expected_id,expected_price,expected_woodtype,expected_stock);
        

        // Analyze
        assertEquals(true,product.inStock());
    }
    //hrishikesh
    @Test
    public void testToString() {
        // Setup
        String expected_name = "testToString";
        int expected_id = 10;
        int expected_price = 15;
        String[] expected_woodtype = new String[1];
        expected_woodtype[0] = "oak";
        int expected_stock = 10;
        Product product = new Product(expected_name,expected_id,expected_price,expected_woodtype,expected_stock);
        String expected_string = String.format(Product.STRING_FORMAT,expected_name,expected_id,expected_price,expected_woodtype,expected_stock);
        

        // Invoke
        String actual_string = product.toString();

        // Analyze
        assertEquals(expected_string,actual_string);
    }
}
