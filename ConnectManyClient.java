package com.doing.more.java.example;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class ConnectManyClient {

    public static void main(String[] args) {
        ConnectManyClient theClient = new ConnectManyClient();
        theClient.go();
    }
    private void go() {
        try {
            while(true) {
                Scanner systemInScanner = new Scanner(System.in);
                System.out.printf("Enter the message to send to the server.\n");
                String messageForServer = systemInScanner.nextLine();
                //connect to the server
                Socket toServer = new Socket("localhost", 9393);
                //setup the JSON streams
                JSONInputStream inFromServer = new JSONInputStream(toServer.getInputStream());
                JSONOutputStream outToServer = new JSONOutputStream(toServer.getOutputStream());

                HashMap<String, Object> request = new HashMap<>();
                request.put("command", "Speak");
                request.put("message", messageForServer);

                outToServer.writeObject(request);
                System.out.println("waiting for response");
                HashMap<String, Object> response = (HashMap<String, Object>) inFromServer.readObject();
                if (response.get("command").equals("Done")) {
                    System.out.println("Sent request: " + request + "and  got response  " + response);
                } else {
                    System.out.println("Oops. got " + response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
