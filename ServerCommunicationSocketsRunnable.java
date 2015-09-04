package com.doing.more.java.example.sillyclient;

import android.os.Handler;
import android.widget.TextView;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.HashMap;

public class ServerCommunicationSocketsRunnable implements Runnable {

    private int numMessagesSent;
    private String messageForServer;
    private WeakReference responseViewReference;
    private Handler uiThreadStack;

    public ServerCommunicationSocketsRunnable(int currentNumMessagesSent, String aMessageForServer, WeakReference aResponseViewReference, Handler aUIThreadStack) {
        this.numMessagesSent = currentNumMessagesSent;
        this.messageForServer = aMessageForServer;
        this.responseViewReference = aResponseViewReference;
        this.uiThreadStack = aUIThreadStack;
    }
    public void run() {
        try {
            //connect to the server
            Socket toServer = new Socket("10.0.2.2", 9393);
            //setup the JSON streams to be used later.
            final JSONInputStream inFromServer =
                    new JSONInputStream(toServer.getInputStream());
            final JSONOutputStream outToServer =
                    new JSONOutputStream(toServer.getOutputStream());

            HashMap<String,Object> commandMap = new HashMap<>();
            commandMap.put("command", "Speak");
            commandMap.put("count",this.numMessagesSent);
            commandMap.put("message", this.messageForServer);

            outToServer.writeObject(commandMap);
            System.out.println("waiting for response");
            HashMap<String,Object> response = (HashMap<String, Object>) inFromServer.readObject();
            System.out.println("response: "+response);
            if(response.get("command").equals("Done")){
                final TextView responseView = (TextView) responseViewReference.get();
                uiThreadStack.post(new Runnable() {
                    @Override
                    public void run() {
                        if (responseView != null) {
                            responseView.setText("Sent " + ServerCommunicationSocketsRunnable.this.messageForServer
                                    + ". Message number " + ServerCommunicationSocketsRunnable.this.numMessagesSent);
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            final String theErrorMessage = e.getLocalizedMessage();
            final TextView responseView = (TextView) responseViewReference.get();
            uiThreadStack.post(new Runnable() {
                @Override
                public void run() {
                    if (responseView != null) {
                        responseView.setText("Error: Unable to communicate with server. " + theErrorMessage);
                    }
                }
            });
        }
    }

}
