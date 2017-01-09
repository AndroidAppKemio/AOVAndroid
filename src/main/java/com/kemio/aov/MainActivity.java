package com.kemio.aov;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

import static com.kemio.aov.ItemActividad.ActivityType.ACTIVIDAD;
import static com.kemio.aov.ItemActividad.ActivityType.CAPACITACION;
import static com.kemio.aov.ItemActividad.ActivityType.DIFUSION;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("testTopic");

        ArrayList<ItemActividad> actividades = new ArrayList<>();
        // TODO: Llenar lista con actividades sacadas de la API. Usando actividades de prueba.
        actividades.add(new ItemActividad("Actividad de prueba", "Esta es una actividad de prueba.", "10/1/2017 15:00", ACTIVIDAD));
        actividades.add(new ItemActividad("Difusión de prueba", "Esta es una difusión de prueba.", "12/2/2017 14:00", DIFUSION));
        actividades.add(new ItemActividad("Capacitación de prueba", "Esta es una capacitación de prueba.", "8/1/2016 19:00", CAPACITACION));

        ListAdapterActividades adapter = new ListAdapterActividades(getApplicationContext(), actividades);

        ListView lstActividades = (ListView) findViewById(R.id.lstActividades);

        lstActividades.setAdapter(adapter);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setItemIconTintList(null);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case (R.id.desarrollo):
                Intent activityDesarrollo = new Intent(getApplicationContext(), Desarrollo.class);
                startActivity(activityDesarrollo);
                break;
            case (R.id.opciones):
                // TODO: Iniciar la actividad "opciones".
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
