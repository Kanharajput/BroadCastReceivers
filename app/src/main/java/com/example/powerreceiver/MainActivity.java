package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // create the object of CustomeReceiver
    private CustomReceiver mReceiver = new CustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // it is use to filter the intent based on their actions and category
        IntentFilter intentFilter = new IntentFilter();
        // this are the two filter for which we receive a broadcast
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        // registering the receiver
        this.registerReceiver(mReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        //unregister the receiver to prevent from memory leaks
        this.unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}