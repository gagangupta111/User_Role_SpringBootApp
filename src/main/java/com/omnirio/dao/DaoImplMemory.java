package com.omnirio.dao;

import com.omnirio.model.Account;
import com.omnirio.model.CustomResponse;
import com.omnirio.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("InMemory")
public class DaoImplMemory implements DaoInterface{

    @Override
    public CustomResponse getAllUsers() {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage("ABC");
        customResponse.setSuccess(true);

        Map<String, Object> map = new HashMap<>();
        customResponse.setInfo(map);
        return customResponse;
    }

    @Override
    public CustomResponse getUser(String userID) {
        return null;
    }

    @Override
    public CustomResponse createUser(User user) {
        return null;
    }

    @Override
    public CustomResponse getAllAccounts() {
        return null;
    }

    @Override
    public CustomResponse getAccount(String accountID) {
        return null;
    }

    @Override
    public CustomResponse createAccount(Account account) {
        return null;
    }

    @Override
    public CustomResponse userUpdateUser(String userID, User user) {
        return null;
    }

    @Override
    public CustomResponse userDeleteUser(String userID) {
        return null;
    }

    @Override
    public CustomResponse userUpdateAccount(String accountID, Account account) {
        return null;
    }

    @Override
    public CustomResponse userDeleteAccount(String accountID) {
        return null;
    }

    @Override
    public CustomResponse getUserAllAccounts(String userID) {
        return null;
    }
}
