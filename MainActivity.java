package com.doing.more.java.example.sillyclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends Activity {
    private int requestCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View v) {
        this.requestCount++;
        EditText textInput = (EditText)this.findViewById(R.id.message);
        String theMessage = textInput.getText().toString();
        Handler uiThreadStack = new Handler();
        TextView responseView = (TextView)this.findViewById(R.id.response);

        WeakReference responseViewReference = new WeakReference(responseView);
        ServerCommunicationRunnable communicationRunnable = new ServerCommunicationRunnable(this.requestCount,theMessage, responseViewReference,uiThreadStack);
        Thread serverCommunicationThread = new Thread(communicationRunnable);
        serverCommunicationThread.start();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
