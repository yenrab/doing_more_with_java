package com.doing.more.java.example;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConnectManyServer {
    private Executor theExecutor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        ConnectManyServer theServer = new ConnectManyServer();
        theServer.start();
    }
    private void start(){
        try {
            //a socket opened on the specified port
            ServerSocket aListeningSocket =
                    new ServerSocket(9393);
            while(true){
                //wait for a connection
                System.out.println("Waiting for client connection request.");
               final  Socket clientSocket = aListeningSocket.accept();
               this.theExecutor.execute(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           JSONInputStream inFromClient = new JSONInputStream(clientSocket.getInputStream());
                           JSONOutputStream outToClient = new JSONOutputStream(clientSocket.getOutputStream());

                           System.out.println("Waiting for a message from the client.");
                           HashMap aRequest = (HashMap) inFromClient.readObject();
                           System.out.println("Just got:" + aRequest + " from client");
                           aRequest.put("command", "Done");
                           outToClient.writeObject(aRequest);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               });
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
