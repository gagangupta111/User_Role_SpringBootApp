package com.omnirio.util;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public enum Errors {

    BRANCH_MISMATCH(401, "BRANCH_MANAGER_AND_ENTITY_NOT_IN_SAME_BRANCH", ""),
    NOT_BRANCH_MANAGER(403, "ID_DOES_NOT_BELONG_TO_BRANCH_MANAGER", ""),
    INVAID_BRANCH_MANAGER_ID(402, "INVAID_BRANCH_MANAGER_ID", ""),
    INVALID_USER_ID(404, "INVALID_USER_ID", ""),
    INVALID_ACCOUNT_ID(405, "INVALID_ACCOUNT_ID", ""),
    INVALID_DATE_FORMAT(406, "INVALID_DATE_FORMAT", "Date format must be dd/MM/yyyy. Example: 25/12/2020 for Dec 25, 2020");

    private int code;
    private String message;
    private String details;

    Errors(int code, java.lang.String message, java.lang.String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public java.lang.String getMessage() {
        return message;
    }

    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    public java.lang.String getDetails() {
        return details;
    }

    public void setDetails(java.lang.String details) {
        this.details = details;
    }

    public JSONObject AsJson(){
        JSONObject object = new JSONObject();
        try {
            object.put("code", code);
            object.put("message", message);
            object.put("details", details);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public Map<String, Object> AsMap(){
        Map<String, Object> object = new HashMap<>();
        object.put("code", code);
        object.put("message", message);
        object.put("details", details);
        return object;
    }

}
