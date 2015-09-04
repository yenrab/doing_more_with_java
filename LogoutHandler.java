package com.doing.more.java.example.handlers;

import com.doing.more.java.example.StoreModel;
import com.doing.more.java.example.User;
import com.doing.more.java.example.appcontrol.Handler;
import json.JSONOutputStream;

import java.util.HashMap;

public class LogoutHandler implements Handler {
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {

        String sessionID = (String)dataMap.get("id");
        StoreModel theModel = (StoreModel)dataMap.get("model");
        User foundUser = theModel.getUserBySessionID(sessionID);
        if(foundUser != null){
            foundUser.setSession("");
            theModel.updateUser(foundUser);
        }
        HashMap<String,Object> responseMap = new HashMap<>();
        responseMap.put("id","");
        JSONOutputStream outToClient = (JSONOutputStream)dataMap.get("toClient");
        try {
            outToClient.writeObject(responseMap);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
