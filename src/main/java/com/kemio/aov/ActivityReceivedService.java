package com.kemio.aov;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class ActivityReceivedService extends FirebaseMessagingService {
    final String TAG = "AOV";
    final static String GROUP_KEY_ACTIVITIES = "group_key_activities";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO: Handle the messages.
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        Map<String, String> data = remoteMessage.getData();

        makeNotification(Integer.parseInt(data.get("id")), data.get("title"), data.get("content"), Integer.parseInt(data.get("activityType")));
    }

    private void makeNotification(int id, String title, String content, int selectedTypeIndex) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        int iconId;
        switch (selectedTypeIndex) {
            case 0:
                iconId = R.drawable.icono_actividad;
                break;
            case 1:
                iconId = R.drawable.icono_cobertura;
                break;
            case 2:
                iconId = R.drawable.icono_capacitacion;
                break;
            case 3:
                iconId = R.drawable.icono_difusion;
                break;
            default:
                iconId = R.drawable.icono_actividad;
                break;
        }

        Notification n = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(iconId)
                .setAutoCancel(true)
                .setGroup(GROUP_KEY_ACTIVITIES)
                .setContentIntent(pIntent).build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(id, n);
    }
}