package com.pankaj.addict.base;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import static com.pankaj.addict.constants.AppConstant.CHANNEL_1_ID;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Show Warnings",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This channel will help in showing the notification if the target limit exceeds");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
         }
    }

}
