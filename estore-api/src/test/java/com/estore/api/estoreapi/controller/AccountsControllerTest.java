package com.estore.api.estoreapi.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import com.estore.api.estoreapi.persistence.AccountsDAO;
import com.estore.api.estoreapi.model.Account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test the Account Controller class
 * 
 * @author SWEN Faculty
 */
@Tag("Controller-tier")
public class AccountsControllerTest {
    private AccountsController accountsController;
    private AccountsDAO mockAccountsDAO;

    /**
     * Before each test, create a new AccountsController object and inject
     * a mock Accounts DAO
     */
    @BeforeEach
    public void setupAccountsController() {
        mockAccountsDAO = mock(AccountsDAO.class);
        accountsController = new AccountsController(mockAccountsDAO);
    }
    //maksim
    @Test
    public void testGetAccount() throws IOException {  // getAccount may throw IOException
       // Setup
        
       Account account = new Account("test",1);
       // When the same id is passed in, our mock Accounts DAO will return the Account object
       when(mockAccountsDAO.getAccount(account.getId())).thenReturn(account);

       // Invoke
       ResponseEntity<Account> response = accountsController.getAccount(account.getId());

       // Analyze
       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertEquals(account,response.getBody());
   }

    

    
    //maksim
    @Test
    public void testGetAccountNotFound() throws Exception { // createAccount may throw IOException
        // Setup
        int accountId = 99;
        // When the same id is passed in, our mock Accounts DAO will return null, simulating
        // no account found
        when(mockAccountsDAO.getAccount(accountId)).thenReturn(null);

        // Invoke
        ResponseEntity<Account> response = accountsController.getAccount(accountId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }
    //maksim
    @Test
    public void testGetAccountHandleException() throws Exception { // createAccount may throw IOException
            // Setup
        int accountId = 99;
        // When getAccount is called on the Mock Accounts DAO, throw an IOException
        doThrow(new IOException()).when(mockAccountsDAO).getAccount(accountId);

        // Invoke
        ResponseEntity<Account> response = accountsController.getAccount(accountId);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());

    }


    

    //khaled
    @Test
    public void testCreateAccount() throws IOException {  // createAccount may throw IOException
        // Setup
        
        Account account = new Account("test",1);

        // when createAccount is called, return true simulating successful
        // creation and save
        when(mockAccountsDAO.createAccount(account)).thenReturn(account);
        Account[] accountsArray = new Account[0];
        when(mockAccountsDAO.findAccounts(account.getName())).thenReturn(accountsArray);

        // Invoke
        ResponseEntity<Account> response = accountsController.createAccount(account);

        // Analyze
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(account,response.getBody());
    }
    //khaled
    @Test
    public void testCreateAccountFailed() throws IOException {  // createAccount may throw IOException
        // Setup
        
        Account account = new Account("test",1);
        

        // when createAccount is called, return false simulating failed
        // creation and save
        when(mockAccountsDAO.createAccount(account)).thenReturn(account);
        Account[] accountsArray = new Account[1];
        accountsArray[0] = account;
        when(mockAccountsDAO.findAccounts(account.getName())).thenReturn(accountsArray);

        // Invoke
        ResponseEntity<Account> response = accountsController.createAccount(account);

        // Analyze
        assertEquals(HttpStatus.CONFLICT,response.getStatusCode());
    }
    //khaled
    @Test
    public void testCreateAccountHandleException() throws IOException {  // createAccount may throw IOException
        // Setup
        
        Account account = new Account("test",1);

        // When createAccount is called on the Mock Accounts DAO, throw an IOException
        doThrow(new IOException()).when(mockAccountsDAO).createAccount(account);
        Account[] accountsArray = new Account[0];
        when(mockAccountsDAO.findAccounts(account.getName())).thenReturn(accountsArray);

        // Invoke
        ResponseEntity<Account> response = accountsController.createAccount(account);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    //mohammad
    @Test
    public void testUpdateAccount() throws IOException { // updateAccount may throw IOException
        // Setup
        
        Account account = new Account("test",1);

        // when updateAccount is called, return true simulating successful
        // update and save
        when(mockAccountsDAO.updateAccount(account)).thenReturn(account);
        ResponseEntity<Account> response = accountsController.updateAccount(account);

        account.setName("change test");

        // Invoke
        response = accountsController.updateAccount(account);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(account,response.getBody());


    }


    
    //mohammad
    @Test
    public void testUpdateProdutFailed() throws IOException { // updateAccount may throw IOException
        // Setup
        
        Account account = new Account("test",1);
        // when updateAccount is called, return true simulating successful
        // update and save
        when(mockAccountsDAO.updateAccount(account)).thenReturn(null);

        // Invoke
        ResponseEntity<Account> response = accountsController.updateAccount(account);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


    }
    //mohammad
    @Test
    public void testUpdateAccountHandleException() throws IOException { // updateAccount may throw IOException
            // Setup
        
        Account account = new Account("test",1);
        // when updateAccount is called, return true simulating successful
        // update and save
        when(mockAccountsDAO.updateAccount(account)).thenReturn(null);

        // Invoke
        ResponseEntity<Account> response = accountsController.updateAccount(account);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());


    }
       //aya
       @Test
    public void testGetAccounts() throws IOException { // getAccounts may throw IOException

        // Setup
        Account[] accounts = new Account[2];
        
        accounts[0] = new Account("test1",1);

       
        accounts[1] = new Account("test2",2);

        // When getAccounts is called return the accounts created above
        when(mockAccountsDAO.getAccounts()).thenReturn(accounts);

        // Invoke
        ResponseEntity<Account[]> response = accountsController.getAccounts();

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(accounts,response.getBody());
 
    }
    //aya
      @Test
    public void testGetAccountsHandleException() throws IOException { // getAccounts may throw IOException
        // Setup
        // When getAccounts is called on the Mock Accounts DAO, throw an IOException
        doThrow(new IOException()).when(mockAccountsDAO).getAccounts();

        // Invoke
        ResponseEntity<Account[]> response = accountsController.getAccounts();

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }


    
    //mohammad
    @Test
    public void testSearchAccounts() throws IOException { // findAccounts may throw IOException
        // Setup
        String searchString = "est";
        Account[] accounts = new Account[2];
       
        Account account1 = new Account("test1",1);

        
        Account account2 = new Account("test2",2);
        accounts[0] = account1;
        accounts[1] = account2;
        
        // When findAccounts is called with the search string, return the two
        /// accounts above
        when(mockAccountsDAO.findAccounts(searchString)).thenReturn(accounts);

        // Invoke
        ResponseEntity<Account[]> response = accountsController.searchAccounts(searchString);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(accounts,response.getBody());

    }

    //hrishikesh
    @Test
    public void testSearchAccountsHandleException() throws IOException { // findAccounts may throw IOException
        // Setup
        String searchString = "an";
        // When createAccount is called on the Mock Accounts DAO, throw an IOException
        doThrow(new IOException()).when(mockAccountsDAO).findAccounts(searchString);

        // Invoke
        ResponseEntity<Account[]> response = accountsController.searchAccounts(searchString);

        // Analyze
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }

    //khaled
    @Test
    public void testDeleteAccount() throws IOException { // deleteAccount may throw IOException
        // Setup
        int accountId = 99;
        // when deleteAccount is called return true, simulating successful deletion
        when(mockAccountsDAO.deleteAccount(accountId)).thenReturn(true);

        // Invoke
        ResponseEntity<Account> response = accountsController.deleteAccount(accountId);

        // Analyze
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }
    //khaled
    @Test
    public void testDeleteAccountNotFound() throws IOException { // deleteAccount may throw IOException
        // Setup
        int accountId = 99;
        // when deleteAccount is called return false, simulating failed deletion
        when(mockAccountsDAO.deleteAccount(accountId)).thenReturn(false);

        // Invoke
        ResponseEntity<Account> response = accountsController.deleteAccount(accountId);

        // Analyze
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }
       //khaled
       @Test
       public void testDeleteAccountHandleException() throws IOException { // deleteAccount may throw IOException
           // Setup
           int accountId = 99;
           // When deleteAccount is called on the Mock Accounts DAO, throw an IOException
           doThrow(new IOException()).when(mockAccountsDAO).deleteAccount(accountId);
   
           // Invoke
           ResponseEntity<Account> response = accountsController.deleteAccount(accountId);
   
           // Analyze
           assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
       }
   
   
    
}




