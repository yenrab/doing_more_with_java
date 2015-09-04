package com.doing.more.java;

import com.doing.more.java.appcontrol.Handler;
import ort.quickconnectfamily.json.JSONOutputStream;

import java.util.HashMap;


public class SpeakHandler implements Handler {
    @Override
    public void handleIt(HashMap<String, Object> dataMap) {
        try{
            JSONOutputStream outToClient = (JSONOutputStream)dataMap.remove("toClient");
            System.out.println("Just got:" + dataMap + " from client");
            dataMap.put("command", "Done");
            outToClient.writeObject(dataMap);
            System.out.println("just sent "+dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
