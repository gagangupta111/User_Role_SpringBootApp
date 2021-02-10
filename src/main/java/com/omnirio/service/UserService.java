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

    public CustomResponse userDeleteUser(String bm_id, String userID){

        return dao.userDeleteUser(bm_id, userID);
    }

    public CustomResponse userUpdateAccount(String bm_userID, Account account){

        return dao.userUpdateAccount(bm_userID, account);
    }


    public CustomResponse userDeleteAccount(String bm_user_id, String accountID){

        return dao.userDeleteAccount(bm_user_id, accountID);
    }

    public CustomResponse getUserAllAccounts(String userID){

        return dao.getUserAllAccounts(userID);
    }



}
