package com.omnirio.dao;

import com.omnirio.model.Account;
import com.omnirio.model.CustomResponse;
import com.omnirio.model.Role;
import com.omnirio.model.User;
import com.omnirio.util.Errors;
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

        User user = userID_Users.get(userID);
        if (user == null){
            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVALID_USER_ID.getMessage());
            customResponse.setInfo(Errors.INVALID_USER_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;
        }

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
        User updated = userID_Users.get(user.getUserID());

        if (bm_user == null){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVAID_BRANCH_MANAGER_ID.getMessage());
            customResponse.setInfo(Errors.INVAID_BRANCH_MANAGER_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else if (user.getUserID() == null || updated == null){

            // error
            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVALID_USER_ID.getMessage());
            customResponse.setInfo(Errors.INVALID_USER_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else if (bm_user.getBranch() == null || !bm_user.getBranch().equalsIgnoreCase(updated.getBranch())){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.BRANCH_MISMATCH.getMessage());
            customResponse.setInfo(Errors.BRANCH_MISMATCH.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else {

            if (roles.get("BRANCH_MANAGER").getRoleName().equals(bm_user.getRoleName())){

                updated.setUserName(user.getUserName());
                updated.setRoleName(user.getRoleName());
                updated.setPhoneNumber(user.getPhoneNumber());
                updated.setGender(user.getGender());
                updated.setDob(user.getDob());
                updated.setBranch(user.getBranch());

                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage("User Updated!");
                customResponse.setSuccess(true);

                Map<String, Object> map = new HashMap<>();
                map.put("User", Utilities.userToJson(updated));
                customResponse.setInfo(map);
                return customResponse;

            }else {
                // error
                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage(Errors.NOT_BRANCH_MANAGER.getMessage());
                customResponse.setInfo(Errors.NOT_BRANCH_MANAGER.AsMap());
                customResponse.setSuccess(false);
                return customResponse;
            }
        }
    }

    @Override
    public CustomResponse userDeleteUser(String bm_id, String userID) {

        User bm_user = userID_Users.get(bm_id);
        User user = userID_Users.get(userID);

        if (bm_user == null){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVAID_BRANCH_MANAGER_ID.getMessage());
            customResponse.setInfo(Errors.INVAID_BRANCH_MANAGER_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else if (user == null){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVALID_USER_ID.getMessage());
            customResponse.setInfo(Errors.INVALID_USER_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else if (bm_user.getBranch() == null || !bm_user.getBranch().equalsIgnoreCase(user.getBranch())){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.BRANCH_MISMATCH.getMessage());
            customResponse.setInfo(Errors.BRANCH_MISMATCH.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else {

            if (roles.get("BRANCH_MANAGER").getRoleName().equals(bm_user.getRoleName())){

                userID_Users.remove(userID);

                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage("User Deleted!");
                customResponse.setSuccess(true);

                Map<String, Object> map = new HashMap<>();
                map.put("User", Utilities.userToJson(user));
                customResponse.setInfo(map);
                return customResponse;

            }else {
                // error
                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage(Errors.NOT_BRANCH_MANAGER.getMessage());
                customResponse.setInfo(Errors.NOT_BRANCH_MANAGER.AsMap());
                customResponse.setSuccess(false);
                return customResponse;
            }
        }

    }

    @Override
    public CustomResponse userUpdateAccount(String bm_userID, Account account) {

        User bm_user = userID_Users.get(bm_userID);
        Account toBeUpdatedAccount = account.getAccountID() == null ? null : Utilities.getAccounts(account.getAccountID());

        if (bm_user == null){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVAID_BRANCH_MANAGER_ID.getMessage());
            customResponse.setInfo(Errors.INVAID_BRANCH_MANAGER_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else if (toBeUpdatedAccount == null){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVALID_ACCOUNT_ID.getMessage());
            customResponse.setInfo(Errors.INVALID_ACCOUNT_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else if (bm_user.getBranch() == null || !bm_user.getBranch().equalsIgnoreCase(toBeUpdatedAccount.getBranch())){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.BRANCH_MISMATCH.getMessage());
            customResponse.setInfo(Errors.BRANCH_MISMATCH.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else {

            if (roles.get("BRANCH_MANAGER").getRoleName().equals(bm_user.getRoleName())){

                Utilities.updateAccount(account.getAccountID(), account);

                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage("Account Updated!");
                customResponse.setSuccess(true);

                Map<String, Object> map = new HashMap<>();
                map.put("Account", Utilities.accountToJson(account));
                customResponse.setInfo(map);
                return customResponse;

            }else {
                // error
                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage(Errors.NOT_BRANCH_MANAGER.getMessage());
                customResponse.setInfo(Errors.NOT_BRANCH_MANAGER.AsMap());
                customResponse.setSuccess(false);
                return customResponse;
            }
        }
    }

    @Override
    public CustomResponse userDeleteAccount(String bm_user_id, String accountID) {

        User bm_user = userID_Users.get(bm_user_id);
        Account toBeDeletedAccount = accountID == null ? null : Utilities.getAccounts(accountID);

        if (bm_user == null){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVAID_BRANCH_MANAGER_ID.getMessage());
            customResponse.setInfo(Errors.INVAID_BRANCH_MANAGER_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else if (toBeDeletedAccount == null){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.INVALID_ACCOUNT_ID.getMessage());
            customResponse.setInfo(Errors.INVALID_ACCOUNT_ID.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else if (bm_user.getBranch() == null || !bm_user.getBranch().equalsIgnoreCase(toBeDeletedAccount.getBranch())){

            CustomResponse customResponse = new CustomResponse();
            customResponse.setMessage(Errors.BRANCH_MISMATCH.getMessage());
            customResponse.setInfo(Errors.BRANCH_MISMATCH.AsMap());
            customResponse.setSuccess(false);
            return customResponse;

        }else {

            if (roles.get("BRANCH_MANAGER").getRoleName().equals(bm_user.getRoleName())){

                Utilities.deleteAccount(accountID);

                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage("Account Deleted!");
                customResponse.setSuccess(true);

                Map<String, Object> map = new HashMap<>();
                map.put("Account", Utilities.accountToJson(toBeDeletedAccount));
                customResponse.setInfo(map);
                return customResponse;

            }else {
                // error
                CustomResponse customResponse = new CustomResponse();
                customResponse.setMessage(Errors.NOT_BRANCH_MANAGER.getMessage());
                customResponse.setInfo(Errors.NOT_BRANCH_MANAGER.AsMap());
                customResponse.setSuccess(false);
                return customResponse;
            }
        }

    }
}
