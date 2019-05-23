package com.example.standup;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;
    private int NOTIFICATION_ID = 0;
    private static final String ACTION_NOTIFY = "com.example.android.standup.ACTION_NOTIFY";
    //private PendingIntent notifyPendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);

        Intent notifyIntent = new Intent(ACTION_NOTIFY);

        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIFICATION_ID,
                notifyIntent, PendingIntent.FLAG_NO_CREATE) != null);

        alarmToggle.setChecked(alarmUp);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        String toastMessage;

                        long triggerTime = SystemClock.elapsedRealtime();// + AlarmManager.INTERVAL_FIFTEEN_MINUTES;
                        long repeatInterval = (1*60*1000);

                        //If the Toggle is turned on, set the repeating alarm with a 15 minute interval

                        if(isChecked){
                            //deliverNotification(MainActivity.this);
                            alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval, notifyPendingIntent);
                            //Set the toast message for the "on" case
                            toastMessage = getString(R.string.alarm_on_toast);
                        } else {
                            alarmManager.cancel(notifyPendingIntent);
                            //Cancel notification if the alarm is turned off
                            mNotificationManager.cancelAll();
                            //Set the toast message for the "off" case
                            toastMessage = getString(R.string.alarm_off_toast);
                        }

                        //Show a toast to say the alarm is turned on or off
                        Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT)
                                .show();

                    }
                }
        );
    }

}
