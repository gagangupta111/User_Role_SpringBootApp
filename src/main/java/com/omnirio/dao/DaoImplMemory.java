package com.omnirio.dao;

import com.omnirio.model.Account;
import com.omnirio.model.CustomResponse;
import com.omnirio.model.Role;
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
    static Map<String, Role> roles = new HashMap<>();
    Map<String, List<Account>> userID_Accounts = new HashMap<>();
    Map<String, Account> accountID_Accounts = new HashMap<>();

    static {

        Role role = new Role();
        role.setRoleID(Utilities.generateUniqueID());
        role.setRoleName("BRANCH_MANAGER");
        role.setCode("BM");
        roles.put(role.getRoleName(), role);

        role = new Role();
        role.setRoleID(Utilities.generateUniqueID());
        role.setRoleName("CUSTOMER");
        role.setCode("C");
        roles.put(role.getRoleName(), role);

    }

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

        User bm_user = userID_Users.get(userID);
        User updated = null;

        if (bm_user == null){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage("Branch Manager User!");
            customResponse.setSuccess(false);
            return customResponse;

        }else if (user.getUserID() == null){

            // error
            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage("Invalid User!");
            customResponse.setSuccess(false);
            return customResponse;

        }else {

            if (roles.get("BRANCH_MANAGER").getRoleName().equals(bm_user.getRoleName())){

                updated = userID_Users.get(user.getUserID());

                updated.setUserName(user.getUserName());
                updated.setRoleName(user.getRoleName());
                updated.setPhoneNumber(user.getPhoneNumber());
                updated.setGender(user.getGender());
                updated.setDob(user.getDob());
                updated.setBranch(user.getBranch());

            }else {
                // error
                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage("Not a branch Manager!");
                customResponse.setSuccess(false);
                return customResponse;
            }
        }

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
