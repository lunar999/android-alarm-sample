package com.boostcamp.example.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    private String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i(TAG, "alarm alarm!!");
        Toast.makeText(context, R.string.alarm_msg, Toast.LENGTH_SHORT).show();
    }
}