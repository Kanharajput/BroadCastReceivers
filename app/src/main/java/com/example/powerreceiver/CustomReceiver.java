package com.example.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
* A BroadcastReceiver is either a static receiver or a dynamic receiver, depending on how you register it:
* To register a receiver statically, use the <receiver> element in your AndroidManifest.xml file.
  Static receivers are also called manifest-declared receivers.
* To register a receiver dynamically, use the app context or activity context. The receiver receives broadcasts
  as long as the registering context is valid, meaning as long as the corresponding app or activity is running.
  Dynamic receivers are also called context-registered receivers.
*/

public class CustomReceiver extends BroadcastReceiver {
    // both should have the same action string to get the Broadcast
    private static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
        // store the Intent Action
        String intentAction = intent.getAction();

        if(intentAction != null) {
            String action;
            switch (intentAction) {
                // member varible of Intent class -> public static final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
                case Intent.ACTION_POWER_CONNECTED:
                    action = "Charging...";
                    break;

                case Intent.ACTION_POWER_DISCONNECTED :
                    action = "Plug Out";
                    break;

                case ACTION_CUSTOM_BROADCAST:
                    action = "Custom Broadcast received";
                    break;

                default:
                    action = "unknown action";
            }

            Toast toast = new Toast(context);
            toast.setText(action);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
