package com.estore.api.estoreapi.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.InventoryDAO;
import com.estore.api.estoreapi.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Product Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class InventoryControllerTest {
    private InventoryController inventoryController;
    private InventoryDAO mockInventoryDAO;

    /**
     * Before each test, create a new InventoryController object and inject
     * a mock Inventory DAO
     */
    @BeforeEach
    public void setupInventoryController() {
        mockInventoryDAO = mock(InventoryDAO.class);
        inventoryController = new InventoryController(mockInventoryDAO);
    }
    //maksim
    @Test
    public void testGetProduct() throws IOException {  // getProduct may throw IOException
        // Setup
        String[] woodtype = new String[1];
        woodtype[0] = "oak";
        Product product = new Product("test",1,1,woodtype,1);
        // When the same id is passed in, our mock Inventory DAO will return the Product object
        when(mockInventoryDAO.getProduct(product.getId())).thenReturn(product);

        // Invoke
        ResponseEntity<Product> response = inventoryController.getProduct(product.getId());

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }

    
    //maksim
    @Test
    public void testGetProductNotFound() throws Exception { // createProduct may throw IOException
        // Setup
        int productId = 99;
        // When the same id is passed in, our mock Inventory DAO will return null, simulating
        // no product found
        when(mockInventoryDAO.getProduct(productId)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = inventoryController.getProduct(productId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }
    //maksim
    @Test
    public void testGetProductHandleException() throws Exception { // createProduct may throw IOException
        // Setup
        int productId = 99;
        // When getProduct is called on the Mock Inventory DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).getProduct(productId);

        // Invoke
        ResponseEntity<Product> response = inventoryController.getProduct(productId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());

    }

    //khaled
    @Test
    public void testCreateProduct() throws IOException {  // createProduct may throw IOException
        // Setup
        String[] woodtype = new String[1];
        woodtype[0] = "oak";
        Product product = new Product("test",1,1,woodtype,1);

        // when createProduct is called, return true simulating successful
        // creation and save
        when(mockInventoryDAO.createProduct(product)).thenReturn(product);
        Product[] productsArray = new Product[0];
        when(mockInventoryDAO.findProducts(product.getName())).thenReturn(productsArray);

        // Invoke
        ResponseEntity<Product> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(product,response.getBody());
    }
    //khaled
    @Test
    public void testCreateProductFailed() throws IOException {  // createProduct may throw IOException
        // Setup
        String[] woodtype = new String[1];
        woodtype[0] = "oak";
        Product product = new Product("test",1,1,woodtype,1);
        

        // when createProduct is called, return false simulating failed
        // creation and save
        when(mockInventoryDAO.createProduct(product)).thenReturn(product);
        Product[] productsArray = new Product[1];
        productsArray[0] = product;
        when(mockInventoryDAO.findProducts(product.getName())).thenReturn(productsArray);

        // Invoke
        ResponseEntity<Product> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }
    //khaled
    @Test
    public void testCreateProductHandleException() throws IOException {  // createProduct may throw IOException
        // Setup
        String[] woodtype = new String[1];
        woodtype[0] = "oak";
        Product product = new Product("test",1,1,woodtype,1);

        // When createProduct is called on the Mock Inventory DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).createProduct(product);
        Product[] productsArray = new Product[0];
        when(mockInventoryDAO.findProducts(product.getName())).thenReturn(productsArray);

        // Invoke
        ResponseEntity<Product> response = inventoryController.createProduct(product);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    //mohammad
    @Test
    public void testUpdateProduct() throws IOException { // updateProduct may throw IOException
        // Setup
        String[] woodtype = new String[1];
        woodtype[0] = "oak";
        Product product = new Product("test",1,1,woodtype,1);

        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockInventoryDAO.updateProduct(product)).thenReturn(product);
        ResponseEntity<Product> response = inventoryController.updateProduct(product);

        product.setName("change test");

        // Invoke
        response = inventoryController.updateProduct(product);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(product,response.getBody());
    }


    
    //mohammad
    @Test
    public void testUpdateProdutFailed() throws IOException { // updateProduct may throw IOException
        // Setup
        String[] woodtype = new String[1];
        woodtype[0] = "oak";
        Product product = new Product("test",1,1,woodtype,1);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockInventoryDAO.updateProduct(product)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = inventoryController.updateProduct(product);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }
    //mohammad
    @Test
    public void testUpdateProductHandleException() throws IOException { // updateProduct may throw IOException
        // Setup
        String[] woodtype = new String[1];
        woodtype[0] = "oak";
        Product product = new Product("test",1,1,woodtype,1);
        // when updateProduct is called, return true simulating successful
        // update and save
        when(mockInventoryDAO.updateProduct(product)).thenReturn(null);

        // Invoke
        ResponseEntity<Product> response = inventoryController.updateProduct(product);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
    //aya
       @Test
    public void testGetProducts() throws IOException { // getProducts may throw IOException

        // Setup
        Product[] products = new Product[2];
        String[] woodtype1 = new String[1];
        woodtype1[0] = "oak";
        products[0] = new Product("test1",1,1,woodtype1,1);

        String[] woodtype2 = new String[1];
        woodtype2[0] = "oak";
        products[1] = new Product("test2",2,1,woodtype2,1);

        // When getProducts is called return the products created above
        when(mockInventoryDAO.getProducts()).thenReturn(products);

        // Invoke
        ResponseEntity<Product[]> response = inventoryController.getProducts();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(products,response.getBody());
 

    }
    //aya
      @Test
    public void testGetProductsHandleException() throws IOException { // getProducts may throw IOException
        // Setup
        // When getProducts is called on the Mock Inventory DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).getProducts();

        // Invoke
        ResponseEntity<Product[]> response = inventoryController.getProducts();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    
    //mohammad
    @Test
    public void testSearchProducts() throws IOException { // findProducts may throw IOException
        // Setup
        String searchString = "est";
        Product[] products = new Product[2];
        String[] woodtype1 = new String[1];
        woodtype1[0] = "oak";
        Product product1 = new Product("test1",1,1,woodtype1,1);

        String[] woodtype2 = new String[1];
        woodtype2[0] = "oak";
        Product product2 = new Product("test2",2,1,woodtype2,1);

        products[0] = product1;
        products[1] = product2;

        // When findProducts is called with the search string, return the two
        /// products above
        when(mockInventoryDAO.findProducts(searchString)).thenReturn(products);

        // Invoke
        ResponseEntity<Product[]> response = inventoryController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(products,response.getBody());

    }
    //hrishikesh
    @Test
    public void testSearchProductsHandleException() throws IOException { // findProducts may throw IOException
        // Setup
        String searchString = "an";
        // When createProduct is called on the Mock Inventory DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).findProducts(searchString);

        // Invoke
        ResponseEntity<Product[]> response = inventoryController.searchProducts(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    //khaled
    @Test
    public void testDeleteProduct() throws IOException { // deleteProduct may throw IOException
        // Setup
        int productId = 99;
        // when deleteProduct is called return true, simulating successful deletion
        when(mockInventoryDAO.deleteProduct(productId)).thenReturn(true);

        // Invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    //khaled
    @Test
    public void testDeleteProductNotFound() throws IOException { // deleteProduct may throw IOException
        // Setup
        int productId = 99;
        // when deleteProduct is called return false, simulating failed deletion
        when(mockInventoryDAO.deleteProduct(productId)).thenReturn(false);

        // Invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
    //khaled
    @Test
    public void testDeleteProductHandleException() throws IOException { // deleteProduct may throw IOException
        // Setup
        int productId = 99;
        // When deleteProduct is called on the Mock Inventory DAO, throw an IOException
        doThrow(new IOException()).when(mockInventoryDAO).deleteProduct(productId);

        // Invoke
        ResponseEntity<Product> response = inventoryController.deleteProduct(productId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}




