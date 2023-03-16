package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Product;

public interface InventoryDAO {
    
    /**
    Gets all products
    @return an array of products
    */
    Product[] getProducts() throws IOException;

    /**
     finds all the poducts with the containing text in their name
     @param containsText The text the user is searching 
     @return an array of products that are found
     */
    Product[] findProducts(String containsText) throws IOException;

    /**
     Gets the product with the corresponding ID
     @param id The id the user is searching for 
     @return the product with the corrosponding ID
     */
    Product getProduct(int id) throws IOException;

    /**
     Creates the product
     @param product The product object the user wants to create
     @return The product if created successfully
     */
    Product createProduct(Product product) throws IOException;

    /**
     Updates and saves a product
     @param product object to be updated and saved
     @return updated product or null if the product is not found
     */
    Product updateProduct(Product product) throws IOException;

    /**
     Deletes a product
     @param id the id of the product to be deleted
     @return true if the product is delted successfully, False if not
     */
    boolean deleteProduct(int id) throws IOException;
}
