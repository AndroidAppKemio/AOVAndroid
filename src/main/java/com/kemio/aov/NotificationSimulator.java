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
                int selectedTypeIndex = dropdown.getSelectedItemPosition();
                makeNotification(edtTitle.getText().toString(), edtContent.getText().toString(), selectedTypeIndex);
            }
        });

        System.out.println(dropdown.getSelectedItem());
    }

    private void makeNotification(String title, String content, int selectedTypeIndex) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        int iconId;
        switch (selectedTypeIndex){
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
        //comentario para que me deje pushear
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
