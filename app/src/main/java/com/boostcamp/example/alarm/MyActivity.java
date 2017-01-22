package com.boostcamp.example.alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MyActivity extends Activity {

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent alarmIntent = new Intent(MyActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MyActivity.this, 0, alarmIntent, 0);

        findViewById(R.id.startAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        findViewById(R.id.stopAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        findViewById(R.id.stopAlarmAt10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAt10();
            }
        });
    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        long firstTime = SystemClock.elapsedRealtime();
        firstTime += 10*1000;

//        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
//        manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, pendingIntent);
        manager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 5*1000, pendingIntent);
        Toast.makeText(this, "Alarm Setting...", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled...", Toast.LENGTH_SHORT).show();
    }

    public void startAt10() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        //알람을 특정 시간에 세팅함 20시 5분 0초
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.MINUTE, 5);
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MINUTE, 50);

        //20분주기로 알람 세팅
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 1, pendingIntent);
    }

}
