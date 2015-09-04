package com.doing.more.java.example;

import com.doing.more.java.example.appcontrol.ApplicationController;
import com.doing.more.java.example.handlers.LoginHandler;
import com.doing.more.java.example.handlers.LogoutHandler;
import com.doing.more.java.example.handlers.RegistrationHandler;
import json.JSONInputStream;
import json.JSONOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@WebServlet(name = "StoreServlet")
public class StoreServlet extends HttpServlet {
    private ApplicationController theAppController = new ApplicationController();
    private StoreModel theModel = new StoreModel();

    public void init(){
        theAppController.mapCommand("register", new RegistrationHandler());
        theAppController.mapCommand("login", new LoginHandler());
        theAppController.mapCommand("logout", new LogoutHandler());
        //theAppController.mapCommand("addMyNumber", new AddPhoneHandler());
        //theAppController.mapCommand("myNumbers", new GetPhoneNumbersHandler());
        //theAppController.mapCommand("upgrade", new ManagementChangeHandler());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            JSONInputStream inFromClient = new JSONInputStream(request.getInputStream());
            JSONOutputStream outToClient = new JSONOutputStream(response.getOutputStream());

            HashMap<String, Object> dataMap = (HashMap) inFromClient.readObject();
            dataMap.put("model",this.theModel);
            dataMap.put("toClient", outToClient);

            String aCommand = (String) dataMap.get("command");
            theAppController.handleRequest(aCommand, dataMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
