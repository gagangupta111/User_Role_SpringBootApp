package com.omnirio.service;

import com.omnirio.dao.DaoInterface;
import com.omnirio.model.Account;
import com.omnirio.model.CustomResponse;
import com.omnirio.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    @Qualifier("InMemory")
    private DaoInterface dao;

    public CustomResponse getAllUsers(){

        return dao.getAllUsers();

    }

    public CustomResponse getUser(String userID){

        return dao.getUser(userID);

    }

    public CustomResponse createUser(User user){

        return dao.createUser(user);

    }

    public CustomResponse userUpdateUser(String userID, User user){

        return dao.userUpdateUser(userID, user);
    }

    public CustomResponse userDeleteUser(String userID){

        return dao.userDeleteUser(userID);
    }

    public CustomResponse userUpdateAccount(String accountID, Account account){

        return dao.userUpdateAccount(accountID, account);
    }


    public CustomResponse userDeleteAccount(String accountID){

        return dao.userDeleteAccount(accountID);
    }

    public CustomResponse getUserAllAccounts(String userID){

        return dao.getUserAllAccounts(userID);
    }



}
