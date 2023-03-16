package com.estore.api.estoreapi.persistence;

import java.io.IOException;
import com.estore.api.estoreapi.model.Account;

public interface AccountsDAO {
    
    /**
    Gets all accounts
    @return an array of accounts
    */
    
    

    Account[] getAccounts() throws IOException;

    /**
     finds all the accounts with the containing text in their name
     @param containsText The text the user is searching 
     @return an array of accounts that are found
     */
    Account[] findAccounts(String containsText) throws IOException;

    /**
     Gets the account with the corresponding ID
     @param id The id the user is searching for 
     @return the account with the corrosponding ID
     */
    Account getAccount(int id) throws IOException;

    /**
     Creates the Account
     @param account The account object the user wants to create
     @return The account if created successfully
     */
    Account createAccount(Account account) throws IOException;

    /**
     Updates and saves a account
     @param account object to be updated and saved
     @return updated account or null if the account is not found
     */
    Account updateAccount(Account account) throws IOException;

    /**
     Deletes a account
     @param id the id of the account to be deleted
     @return true if the account is delted successfully, False if not
     */
    boolean deleteAccount(int id) throws IOException;
}
