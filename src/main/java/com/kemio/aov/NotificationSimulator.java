package com.kemio.aov;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NotificationSimulator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_simulator);

        final EditText edtTitle = (EditText) findViewById(R.id.edtTitulo);
        final EditText edtContent = (EditText) findViewById(R.id.edtContent);
        final Spinner dropdown = (Spinner) findViewById(R.id.spnActivityType);
        Button btnSend = (Button) findViewById(R.id.btnSend);

        String[] items = new String[]{"Actividad", "Cobertura", "Capacitación", "Difusión"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemActividad.ActivityType selectedType = ItemActividad.ActivityType.ACTIVIDAD;
                int selectedTypeIndex = dropdown.getSelectedItemPosition();
                if (selectedTypeIndex == 1)
                    selectedType = ItemActividad.ActivityType.COBERTURA;
                else if (selectedTypeIndex == 2)
                    selectedType = ItemActividad.ActivityType.CAPACITACION;
                else if (selectedTypeIndex == 3)
                    selectedType = ItemActividad.ActivityType.DIFUSION;
                makeNotification(edtTitle.getText().toString(), edtContent.getText().toString(), selectedType);
            }
        });

        System.out.println(dropdown.getSelectedItem());
    }

    private void makeNotification(String title, String content, ItemActividad.ActivityType selectedType) {
        // TODO: Switch selectedType to check which icon to use. For I don't actually have the icons, I am not doing it now.
        int iconId = R.drawable.cruz_roja;

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);


        Notification n = new Notification.Builder(this)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(iconId)
                .setAutoCancel(true)
                .setContentIntent(pIntent).build();


        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
