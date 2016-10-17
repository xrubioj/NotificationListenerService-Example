package com.xrubio.notificationlistenerservice_example;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.TextView;

import static android.app.NotificationManager.INTERRUPTION_FILTER_NONE;

public class MainActivity extends AppCompatActivity {

    private TextView txtView;
    private NotificationReceiver nReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView) findViewById(R.id.textView);
        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.xrubio.notificationlistenerservice_example.NOTIFICATION_LISTENER_EXAMPLE");
        registerReceiver(nReceiver,filter);

        // To be able to test this app you have to give access to it. Also, after restarting it you need to
        // disable and reenable the access. See:
        // https://stackoverflow.com/questions/17911883/cannot-get-the-notificationlistenerservice-class-to-work/37081128#37081128
        //startService(new Intent(this, NLService.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }



    public void buttonClicked(View v){

        if(v.getId() == R.id.btnCreateNotify){
            NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder ncomp = new NotificationCompat.Builder(this);
            ncomp.setContentTitle("My Regular Notification")
                    .setContentText("Notification Listener Service Example")
                    .setTicker("Notification Listener Service Example")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);
            nManager.notify((int)System.currentTimeMillis(), ncomp.build());
        }
        else if (v.getId() == R.id.btnCreateNotifyHUD) {
            NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder ncomp = new NotificationCompat.Builder(this);
            ncomp.setContentTitle("My HUD Notification")
                    .setContentText("Notification Listener Service Example")
                    .setTicker("Notification Listener Service Example")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setVibrate(new long[0]);
            nManager.notify((int)System.currentTimeMillis(), ncomp.build());
        }
        else if(v.getId() == R.id.btnClearNotify){
            Intent i = new Intent("com.xrubio.notificationlistenerservice_example.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
            i.putExtra("command","clearall");
            sendBroadcast(i);
        }
        else if(v.getId() == R.id.btnListNotify){
            Intent i = new Intent("com.xrubio.notificationlistenerservice_example.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
            i.putExtra("command","list");
            sendBroadcast(i);
        }
        else if (v.getId() == R.id.btnBlockNotificationsMarshmallow) {
            // This requires API 23: disable notifications.
            // Important: setInterruptionFilter() must be called *after* setNotificationPolicy()
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.setNotificationPolicy(
                    new NotificationManager.Policy(NotificationManager.Policy.PRIORITY_CATEGORY_REPEAT_CALLERS,
                            NotificationManager.Policy.PRIORITY_SENDERS_STARRED,
                            NotificationManager.Policy.PRIORITY_SENDERS_STARRED));
            notificationManager.setInterruptionFilter(INTERRUPTION_FILTER_NONE);
        }


    }

    class NotificationReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String temp = intent.getStringExtra("notification_event") + "\n" + txtView.getText();
            txtView.setText(temp);
        }
    }}
