package com.doing.more.java.example.sillyclient;

import android.os.Handler;
import android.widget.TextView;

import org.quickconnectfamily.json.JSONInputStream;
import org.quickconnectfamily.json.JSONOutputStream;

import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;

public class ServerCommunicationRunnable implements Runnable {

    private int numMessagesSent;
    private String messageForServer;
    private WeakReference responseViewReference;

    public ServerCommunicationRunnable(int currentNumMessagesSent, String aMessageForServer, WeakReference aResponseViewReference) {
        this.numMessagesSent = currentNumMessagesSent;
        this.messageForServer = aMessageForServer;
        this.responseViewReference = aResponseViewReference;
    }
    public void run() {
        try {
            URL url = new URL("http://10.0.2.2:8080/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);//allows POST
            JSONOutputStream outToServer = new JSONOutputStream(connection.getOutputStream());

            HashMap<String, Object> request = new HashMap<>();
            request.put("command", "Speak");
            request.put("message", messageForServer);

            outToServer.writeObject(request);

            JSONInputStream inFromServer = new JSONInputStream(connection.getInputStream());
            HashMap<String, Object> response = (HashMap<String, Object>) inFromServer.readObject();
            final TextView responseView = (TextView) responseViewReference.get();
            responseView.post(new Runnable() {
                @Override
                public void run() {
                    if (responseView != null) {
                        responseView.setText("Sent " + ServerCommunicationRunnable.this.messageForServer
                                + ". Message number " + ServerCommunicationRunnable.this.numMessagesSent);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            final String theErrorMessage = e.getLocalizedMessage();
            final TextView responseView = (TextView) responseViewReference.get();
            responseView.post(new Runnable() {
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
