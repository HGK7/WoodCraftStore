package com.estore.api.estoreapi.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.estore.api.estoreapi.model.Account;
import com.estore.api.estoreapi.model.AccountTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Test the Accounts File DAO class
 * 
 */
@Tag("Persistence-tier")
public class AccountsFileDaoTest {
    AccountsFileDAO accountsFileDAO;
    Account[] testAccounts;
    ObjectMapper mockObjectMapper;

    /**
     * Before each test, we will create and inject a Mock Object Mapper to
     * isolate the tests from the underlying file
     * @throws IOException
     */
    @BeforeEach
    public void setupAccountsFileDAO() throws IOException {
        mockObjectMapper = mock(ObjectMapper.class);
        testAccounts = new Account[3];
        testAccounts[0] = new Account("notacount1", 99);
        testAccounts[1] = new Account("account2", 100);
        testAccounts[2] = new Account("account3", 101);

        // When the object mapper is supposed to read from the file
        // the mock object mapper will return the account array above
        when(mockObjectMapper
            .readValue(new File("doesnt_matter.txt"),Account[].class))
                .thenReturn(testAccounts);
                accountsFileDAO = new AccountsFileDAO("doesnt_matter.txt",mockObjectMapper);
    }

      //aya
    @Test
    public void testGetAccounts() {
        // Invoke
        Account[] accounts = accountsFileDAO.getAccounts();

        // Analyze
        assertEquals(accounts.length, testAccounts.length);
        for (int i = 0; i < testAccounts.length;++i)
            assertEquals(accounts[i], testAccounts[i]);
    }



    
    //Hrishikesh
    @Test
    public void testFindAccounts() {
        // Invoke
        Account[] accounts = accountsFileDAO.findAccounts("acc");

        // Analyze
        assertEquals(0,accounts.length);
        
    }

    //maksim
    @Test
    public void testGetAccount() {
        // Invoke
        Account account = accountsFileDAO.getAccount(99);

        // Analzye
        assertEquals(account, testAccounts[0]);

    }

    
    //khaled
    @Test
    public void testDeleteAccount() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> accountsFileDAO.deleteAccount(99),
                            "Unexpected exception thrown");

        // Analzye
        assertEquals(result,true);
        // We check the internal tree map size against the length
        // of the test accounts array - 1 (because of the delete)
        // Because accounts attribute of AccountsFileDAO is package private
        // we can access it directly
        assertEquals(accountsFileDAO.accounts.size(), testAccounts.length-1);
    }
    //khaled 
    @Test
    public void testCreateAccount() {
        // Setup
        Account account = new Account("test", 102);

        // Invoke
        Account result = assertDoesNotThrow(() -> accountsFileDAO.createAccount(account),
                                "Unexpected exception thrown");

        // Analyze
        assertNotNull(result);
        Account actual = accountsFileDAO.getAccount(account.getId());
        assertEquals(actual.getId(),account.getId());
        assertEquals(actual.getName(),account.getName());
    }
    //mohammad 
    @Test
    public void testUpdateAccount() {
         // Setup
         Account account = new Account("test", 101);

         // Invoke
         Account result = assertDoesNotThrow(() -> accountsFileDAO.updateAccount(account),
                                 "Unexpected exception thrown");
 
         // Analyze
         assertNotNull(result);
         Account actual = accountsFileDAO.getAccount(account.getId());
         assertEquals(actual, account);
 

    }
        //aya
        @Test
    public void testSaveException() throws IOException{
        doThrow(new IOException())
            .when(mockObjectMapper)
                .writeValue(any(File.class),any(Account[].class));

        Account account = new Account("test", 102);

        assertThrows(IOException.class,
                        () -> accountsFileDAO.createAccount(account),
                        "IOException not thrown");
    }

    


    
    //maksim
    @Test
    public void testGetAccountNotFound() {
       // Invoke
       Account account = accountsFileDAO.getAccount(98);

       // Analyze
       assertEquals(account, null);
    }
    //khaled
    @Test
    public void testDeleteAccountNotFound() {
        // Invoke
        boolean result = assertDoesNotThrow(() -> accountsFileDAO.deleteAccount(98),
                                                "Unexpected exception thrown");

        // Analyze
        assertEquals(result,false);
        assertEquals(accountsFileDAO.accounts.size(), testAccounts.length);
    }

    //mohammad
    @Test
    public void testUpdateAccountNotFound() {
        // Setup
        Account account = new Account("Test", 102);

        // Invoke
        Account result = assertDoesNotThrow(() -> accountsFileDAO.updateAccount(account),
            "Unexpected exception thrown");
        
        // Analyze
        assertNull(result);
        

    }
     //aya
        @Test
    public void testConstructorException() throws IOException {
        // Setup
        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
        // We want to simulate with a Mock Object Mapper that an
        // exception was raised during JSON object deseerialization
        // into Java objects
        // When the Mock Object Mapper readValue method is called
        // from the AccountsFileDAO load method, an IOException is
        // raised
        doThrow(new IOException())
            .when(mockObjectMapper)
                .readValue(new File("doesnt_matter.txt"),Account[].class);

        // Invoke & Analyze
        assertThrows(IOException.class,
                        () -> new AccountsFileDAO("doesnt_matter.txt",mockObjectMapper),
                        "IOException not thrown");
    }
}



    


