package com.estore.api.estoreapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.core.joran.conditional.ElseAction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import com.estore.api.estoreapi.model.Product;
import com.estore.api.estoreapi.model.Account;
import com.estore.api.estoreapi.model.CartItem;

@Component
public class AccountsFileDAO implements AccountsDAO {
    private static final Logger LOG = Logger.getLogger(AccountsFileDAO.class.getName());
    Map<Integer,Account> accounts;// Provides a local cache of the accounts objects
                                // so that we don't need to read from the file
                                // each time
    private ObjectMapper objectMapper;  // Provides conversion between Account
                                        // objects and JSON text format written
                                        // to the file
    private static int nextId;  // The next Id to assign to a new account
    private String filename;    // Filename to read from and write to

    /**
     * Creates a Accounts File Data Access Object
     * 
     * @param filename Filename to read from and write to
     * @param objectMapper Provides JSON Object to/from Java Object serialization and deserialization
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    public AccountsFileDAO(@Value("${accounts.file}") String filename,ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;
        load();  // load the accounts from the file
    }

    /**
     * Generates the next id for a new account
     * 
     * @return The next id
     */
    private synchronized static int nextId() {
        int id = nextId;
        ++nextId;
        return id;
    }

    /**
     * Generates an array of accounts from the tree map
     * 
     * @return  The array of accounts, may be empty
     */
    private Account[] getAccountsArray() {
        return getAccountsArray(null);
    }

    /**
     * Generates an array of accounts from the tree map for any
     * accounts that contains the text specified by containsText in the account name
     * 
     * If containsText is null, the array contains all of the accounts
     * in the tree map
     * 
     * @return  The array of accounts, may be empty
     */
    private Account[] getAccountsArray(String containsText) { // if containsText == null, no filter
        ArrayList<Account> accountsArrayList = new ArrayList<>();

        for (Account account : accounts.values()) {
            if (containsText == null || account.getName().toLowerCase().equals(containsText.toLowerCase())) {
                accountsArrayList.add(account);
            }
        }

        Account[] accountArray = new Account[accountsArrayList.size()];
        accountsArrayList.toArray(accountArray);
        return accountArray;
    }

    /**
     * Saves the accounts from the map into the file as an array of JSON objects
     * 
     * @return true if the accounts were written successfully
     * 
     * @throws IOException when file cannot be accessed or written to
     */
    private boolean save() throws IOException {
        Account[] accountArray = getAccountsArray();

        // Serializes the Java Objects to JSON objects into the file
        // writeValue will thrown an IOException if there is an issue
        // with the file or reading from the file
        objectMapper.writeValue(new File(filename),accountArray);
        return true;
    }

    /**
     * Loads accounts from the JSON file into the map
     * 
     * Also sets next id to one more than the greatest id found in the file
     * 
     * @return true if the file was read successfully
     * 
     * @throws IOException when file cannot be accessed or read from
     */
    private boolean load() throws IOException {
        accounts = new TreeMap<>();
        nextId = 0;

        // Deserializes the JSON objects from the file into an array of accounts
        // readValue will throw an IOException if there's an issue with the file
        // or reading from the file
        Account[] accountArray = objectMapper.readValue(new File(filename),Account[].class);

        // Add each account to the tree map and keep track of the greatest id
        for (Account account : accountArray) {
            accounts.put(account.getId(),account);
            if (account.getId() > nextId)
                nextId = account.getId();
        }
        // Make the next id one greater than the maximum from the file
        ++nextId;
        return true;
    }

    /**@inheritDoc*/
    @Override
    public Account[] getAccounts() {
        synchronized(accounts) {
            return getAccountsArray();
        }
    }

    /**@inheritDoc*/
    @Override
    public Account[] findAccounts(String containsText) {
        synchronized(accounts) {
            return getAccountsArray(containsText);
        }
    }

    /**@inheritDoc*/
    @Override
    public Account getAccount(int id) {
        synchronized(accounts){
            if (accounts.containsKey(id))
                return accounts.get(id); 
            else
                return null;
            
    }
}

    /**@inheritDoc*/
    @Override
    public Account createAccount(Account account) throws IOException {
        synchronized(accounts) {
            Account newAccount = new Account(account.getName(),nextId());
            accounts.put(newAccount.getId(),newAccount);
            save(); // may throw an IOException
            return newAccount;
        }
    }

    /**@inheritDoc*/
    @Override
    public Account updateAccount(Account account) throws IOException {
        synchronized(accounts) {
            if (accounts.containsKey(account.getId()) == false)
                return null;  // account does not exist

            accounts.put(account.getId(), account);
            save(); // may throw an IOException
            return account;
        }
    }

    /**@inheritDoc*/
    @Override
    public boolean deleteAccount(int id) throws IOException {
        synchronized(accounts) {
            if (accounts.containsKey(id)) {
                accounts.remove(id);
                return save();
            }
            else
                return false;
        }
    }

}
