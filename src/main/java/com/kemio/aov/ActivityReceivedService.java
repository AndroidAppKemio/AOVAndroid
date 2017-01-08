package com.kemio.aov;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActivityReceivedService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new ActivitiesController().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm.set(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + (3 * 1000 * 60),
                PendingIntent.getService(this, 0, new Intent(this, ActivityReceivedService.class), 0)
        );
    }
}

class ActivitiesController extends AsyncTask<Void, Void, ArrayList<ItemActividad>> {

    private String getJSON(String url) throws IOException {
        HttpURLConnection c;
        URL u = new URL(url);
        c = (HttpURLConnection) u.openConnection();
        c.setRequestMethod("GET");
        c.setRequestProperty("Content-length", "0");
        c.setUseCaches(false);
        c.setAllowUserInteraction(false);
        c.connect();
        int status = c.getResponseCode();

        switch (status) {
            case 200:
            case 201:
                StringWriter writer = new StringWriter();
                IOUtils.copy(c.getInputStream(), writer, "UTF-8");
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
                return writer.toString();
        }
        try {
            c.disconnect();
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected ArrayList<ItemActividad> doInBackground(Void... params) {

        return new ArrayList<>();
    }

    @Override
    protected void onPostExecute(ArrayList<ItemActividad> result) {
        super.onPostExecute(result);
        // TODO: Iterate trough the ArrayList, add the new activities to the local database and send notifications.
    }
}