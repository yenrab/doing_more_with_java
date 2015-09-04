package com.doing.more.java.example.handlers;

import com.doing.more.java.example.StoreModel;
import com.doing.more.java.example.User;
import com.doing.more.java.example.appcontrol.Handler;
import json.JSONOutputStream;

import java.util.HashMap;
import java.util.UUID;

public class LoginHandler implements Handler {
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        String userName = (String)dataMap.get("uname");
        String password = (String)dataMap.get("pword");
        StoreModel theModel = (StoreModel)dataMap.get("model");
        User foundUser = theModel.getUser(userName, password);
        HashMap<String,Object>responseMap = new HashMap<>();
        String sessionID = "";
        if(foundUser != null){
            UUID sessionUUID = UUID.randomUUID();
            sessionID = sessionUUID.toString();
            foundUser.setSession(sessionID);
            theModel.updateUser(foundUser);
            responseMap.put("id",sessionID);
        }
        responseMap.put("id",sessionID);
        JSONOutputStream outToClient = (JSONOutputStream)dataMap.get("toClient");
        try {
            outToClient.writeObject(responseMap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
