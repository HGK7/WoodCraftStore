package com.estore.api.estoreapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.estore.api.estoreapi.persistence.AccountsDAO;
import com.estore.api.estoreapi.model.Account;


@RestController
@RequestMapping("accounts")
public class AccountsController {
    private static final Logger LOG = Logger.getLogger(AccountsController.class.getName());
    private AccountsDAO accountsDao;

    
    public AccountsController(AccountsDAO accountsDao) {
        this.accountsDao = accountsDao;
    }

    /**
     * Responds to the GET request for a account for the given id
     * 
     * @param id The id used to locate the account
     * 
     * @return ResponseEntity with Account object and HTTP status of OK if found
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
     
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id) {
        LOG.info("GET /accounts/" + id);
        try {
            Account account = accountsDao.getAccount(id);
            if (account != null)
                return new ResponseEntity<Account>(account,HttpStatus.OK);
            else
                return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }  
    catch(IOException e) {
        LOG.log(Level.SEVERE,e.getLocalizedMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }


    /**
     * Responds to the GET request for all accounts whose name contains
     * the text in name
     * 
     * @param name The name parameter which contains the text used to find the accounts
     * 
     * @return ResponseEntity with array of Account objects (may be empty) and
     * HTTP status of OK
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     * 
     * 
     */
    @GetMapping("")
    public ResponseEntity<Account[]> getAccounts(){
        LOG.info("GET /accounts");
        try {
                return new ResponseEntity<Account[]>(accountsDao.getAccounts(),HttpStatus.OK);
            }  
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @GetMapping("/")
    public ResponseEntity<Account[]> searchAccounts(@RequestParam String name) {
        LOG.info("GET /accounts/?name="+name);
        
        try {
            Account[] accountsArray = accountsDao.findAccounts(name);
            if (accountsArray!= null)
                return new ResponseEntity<Account[]>(accountsArray,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage()); 
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Creates a account with the provided account object
     * 
     * @param account - The account to create
     * 
     * @return ResponseEntity with created Account object and HTTP status of CREATED
     * ResponseEntity with HTTP status of CONFLICT if Account object already exists
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PostMapping("")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        LOG.info("POST /accounts " + account);
        try {
            for(Account accounts:accountsDao.findAccounts(account.getName())){
                if (account.getName().equals(accounts.getName())){

                    return new ResponseEntity<Account>(HttpStatus.CONFLICT);

                }
            }
            Account newAccount = accountsDao.createAccount(account);
            
            return new ResponseEntity<Account>(newAccount,HttpStatus.CREATED);
            
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the Accounts with the provided Account object, if it exists
     * 
     * @param account The Account to update
     * 
     * @return ResponseEntity with updated Account object and HTTP status of OK if updated
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        LOG.info("PUT /accounts " + account);

        // Replace below with your implementation
        try {
            Account updatedAccount = accountsDao.updateAccount(account);
            if (updatedAccount != null)
                return new ResponseEntity<Account>(updatedAccount, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    

    /**
     * Deletes a account with the given id
     * 
     * @param id The id of the account to deleted
     * 
     * @return ResponseEntity HTTP status of OK if deleted
     * ResponseEntity with HTTP status of NOT_FOUND if not found
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Account> deleteAccount(@PathVariable int id) {
        LOG.info("DELETE /accounts/" + id);
        try {
            if (accountsDao.deleteAccount(id))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
