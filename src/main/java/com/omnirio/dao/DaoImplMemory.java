package com.omnirio.dao;

import com.omnirio.model.Account;
import com.omnirio.model.CustomResponse;
import com.omnirio.model.User;
import com.omnirio.util.Utilities;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Qualifier("InMemory")
public class DaoImplMemory implements DaoInterface{

    Map<String, User> userID_Users = new HashMap<>();
    Map<String, List<Account>> userID_Accounts = new HashMap<>();
    Map<String, Account> accountID_Accounts = new HashMap<>();

    @Override
    public CustomResponse getAllUsers() {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage("All Users!");
        customResponse.setSuccess(true);

        Map<String, Object> map = new HashMap<>();

        JSONArray array = new JSONArray();
        for (User user : userID_Users.values()){
            array.put(Utilities.userToJson(user));
        }

        map.put("User", array);
        customResponse.setInfo(map);
        return customResponse;
    }

    @Override
    public CustomResponse getUser(String userID) {

        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage("All Users!");
        customResponse.setSuccess(true);

        Map<String, Object> map = new HashMap<>();
        map.put("User", Utilities.userToJson(userID_Users.get(userID)));
        customResponse.setInfo(map);
        return customResponse;
    }

    @Override
    public CustomResponse createUser(User user) {

        user.setUserID(Utilities.generateUniqueID());
        userID_Users.put(user.getUserID(), user);

        CustomResponse customResponse = new CustomResponse();
        customResponse.setMessage("User Created!");
        customResponse.setSuccess(true);

        Map<String, Object> map = new HashMap<>();
        map.put("User", Utilities.userToJson(user));
        customResponse.setInfo(map);
        return customResponse;
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
