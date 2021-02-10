package com.omnirio.util;

import com.omnirio.model.Account;
import com.omnirio.model.User;
import org.json.JSONObject;

import java.util.UUID;

public class Utilities {

    public static String generateUniqueID() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    /// Accounts Mapping
    public static JSONObject accountToJson(Account account) {

        JSONObject object = new JSONObject();
        try {

            object = account.getAccountID() != null ? object.put("accountID", account.getAccountID()) : object;
            object = account.getAccountType() != null ? object.put("accountType", account.getAccountType()) : object;
            object = account.getBranch() != null ? object.put("branch", account.getBranch()) : object;
            object = account.getCustomerID() != null ? object.put("customerID", account.getCustomerID()) : object;
            object = account.getFlagAge() != null ? object.put("flagAge", account.getFlagAge()) : object;
            object = account.getOpenDate() != null ? object.put("openDate", account.getOpenDate()) : object;

        } catch (Exception e) {

        }
        return object;
    }

    public static Account jsonToAccount(JSONObject jsonObject) {

        Account account = new Account();

        try {
            account.setAccountID(jsonObject.has("accountID") ? jsonObject.getString("accountID") : null);
            account.setAccountType(jsonObject.has("accountType") ? jsonObject.getString("accountType") : null);
            account.setBranch(jsonObject.has("branch") ? jsonObject.getString("branch") : null);
            account.setCustomerID(jsonObject.has("customerID") ? jsonObject.getString("customerID") : null);
            account.setFlagAge(jsonObject.has("flagAge") ? jsonObject.getString("flagAge") : null);
            account.setOpenDate(jsonObject.has("openDate") ? jsonObject.getString("openDate") : null);
        } catch (Exception e) {

        }
        return account;
    }

    /// User mapping

    public static JSONObject userToJson(User user) {

        JSONObject object = new JSONObject();
        try {

            object = user.getUserID() != null ? object.put("userID", user.getUserID()) : object;
            object = user.getBranch() != null ? object.put("branch", user.getBranch()) : object;

            object = user.getDob() != null ? object.put("dob", user.getDob()) : object;
            object = user.getGender() != null ? object.put("gender", user.getGender()) : object;
            object = user.getPhoneNumber() != null ? object.put("phoneNumber", user.getPhoneNumber()) : object;
            object = user.getRoleName() != null ? object.put("roleName", user.getRoleName()) : object;

            object = user.getUserName() != null ? object.put("userName", user.getUserName()) : object;

        } catch (Exception e) {

        }
        return object;
    }

    public static User jsonToUser(JSONObject jsonObject) {

        User user = new User();

        try {
            user.setUserID(jsonObject.has("userID") ? jsonObject.getString("userID") : null);
            user.setBranch(jsonObject.has("branch") ? jsonObject.getString("branch") : null);
            user.setDob(jsonObject.has("dob") ? jsonObject.getString("dob") : null);
            user.setGender(jsonObject.has("gender") ? jsonObject.getString("gender") : null);
            user.setPhoneNumber(jsonObject.has("phoneNumber") ? jsonObject.getString("phoneNumber") : null);
            user.setRoleName(jsonObject.has("roleName") ? jsonObject.getString("roleName") : null);
            user.setUserName(jsonObject.has("userName") ? jsonObject.getString("userName") : null);

        } catch (Exception e) {

        }

        return user;
    }

}
