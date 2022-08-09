package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // create the object of CustomeReceiver
    private CustomReceiver mReceiver = new CustomReceiver();
    // we are gonna use this variable as Broadcast Intent Action
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // it is use to filter the intent based on their actions and category
        IntentFilter intentFilter = new IntentFilter();
        // this are the two filter for which we receive a broadcast
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        // filters for headphone
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);

        // registering the receiver
        this.registerReceiver(mReceiver,intentFilter);

        // register the localBroadcast
        // I think LocalBroadcast is like sending a signal to other classes that a particular work is done
        // or request to start the work that is written on another class
        LocalBroadcastManager.getInstance(this)
                             .registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onDestroy() {
        //unregister the receiver to prevent from memory leaks
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    public void sendCustomBroadCast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);         // Initialising the intent with our define action
        customBroadcastIntent.putExtra("rand_btw_1to20",random_value());       // passing the extra
        // it is used for sending the broadcast within the app
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }

    private int random_value() {
        Random randValueGenerator  = new Random();
        return randValueGenerator.nextInt(21);       // returning random value between 0 to 20 inclusively
    }
}