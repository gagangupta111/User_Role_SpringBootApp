package com.omnirio.util;

import com.omnirio.model.Account;
import com.omnirio.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Utilities {

    public static final String ACCOUNT_URL = "http://localhost:8081/omnirio/account";

    public static String generateUniqueID() {
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID;
    }

    public static boolean deleteAccount(String accountID) {

        try {
            Map<String, Object> res = new HashMap<>();

            String updateURL = ACCOUNT_URL + "/" + accountID;

            HttpClient clientUpdate = new DefaultHttpClient();
            HttpDelete httpDelete = new HttpDelete(updateURL);

            HttpResponse httpResponse = clientUpdate.execute(httpDelete);

            res.put("response", String.valueOf(httpResponse));
            if (httpResponse == null || httpResponse.getStatusLine() == null || httpResponse.getEntity() == null) {
                return false;
            }

            int status = Integer.parseInt(String.valueOf(httpResponse.getStatusLine().getStatusCode()));

            if (status >= 200 && status < 300) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            handleExceptions(e);
            return false;
        }

    }

    public static StringBuilder fetchResponseString(HttpResponse response) {
        BufferedReader rd = null;
        StringBuilder result = new StringBuilder();
        if (response == null) {
            return result;
        }
        try {
            rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (IOException e) {
            handleExceptions(e);
        }
        return result;
    }

    public static Account getAccounts(String accountID) {

        try {
            Map<String, Object> res = new HashMap<>();

            String updateURL =  ACCOUNT_URL + "/" + accountID;

            HttpClient clientUpdate = new DefaultHttpClient();
            HttpGet post = new HttpGet(updateURL);

            HttpResponse httpResponse = clientUpdate.execute(post);

            res.put("response", String.valueOf(httpResponse));
            if (httpResponse == null || httpResponse.getStatusLine() == null || httpResponse.getEntity() == null) {
                return null;
            }

            int status = Integer.parseInt(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
            StringBuilder buffer1 = fetchResponseString(httpResponse);

            if (status >= 200 && status < 300) {
                JSONObject buffer = new JSONObject(buffer1.toString());
                return jsonToAccount(buffer.getJSONObject("Account"));
            } else {
                return null;
            }
        }catch (Exception e){
            handleExceptions(e);
            return null;
        }

    }

    public static boolean updateAccount(String accountID, Account account) {

        try {
            Map<String, Object> res = new HashMap<>();

            String updateURL = ACCOUNT_URL;

            HttpClient clientUpdate = new DefaultHttpClient();
            HttpPut post = new HttpPut(updateURL);

            JSONObject payload = new JSONObject();
            payload.put("accountID", account.getAccountID());

            payload = account.getAccountType() == null ? payload : payload.put("accountType", account.getAccountType());
            payload = account.getBranch() == null ? payload : payload.put("branch", account.getBranch());
            payload = account.getCustomerID() == null ? payload : payload.put("customerID", account.getCustomerID());
            payload = account.getFlagAge() == null ? payload : payload.put("flagAge", account.getFlagAge());

            StringEntity requestEntity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);
            post.setEntity(requestEntity);

            HttpResponse httpResponse = clientUpdate.execute(post);

            res.put("response", String.valueOf(httpResponse));
            if (httpResponse == null || httpResponse.getStatusLine() == null || httpResponse.getEntity() == null) {
                return false;
            }

            int status = Integer.parseInt(String.valueOf(httpResponse.getStatusLine().getStatusCode()));

            if (status >= 200 && status < 300) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            handleExceptions(e);
            return false;
        }

    }

    public static boolean handleExceptions(Exception e){

        // Do something with exceptions
        return true;

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
