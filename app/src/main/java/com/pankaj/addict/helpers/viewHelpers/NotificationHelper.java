package com.pankaj.addict.helpers.viewHelpers;

import android.app.Notification;
import android.content.Context;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.pankaj.addict.R;

import static com.pankaj.addict.constants.AppConstant.CHANNEL_1_ID;

public class NotificationHelper {
    private Context context;

    public NotificationHelper(Context context) {
        this.context = context;
    }

    public void showWarningNotification() {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        String title = "Target Limit Warning";
        String message = "You are exceeding your limit";
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1, notification);
    }

}
