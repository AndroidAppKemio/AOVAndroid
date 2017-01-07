package com.kemio.aov;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Facundo Mendoza on 7/1/2017.
 */

public class ListAdapterActividades extends ArrayAdapter<ActividadItem> {
    public ListAdapterActividades(Context context, ArrayList<ActividadItem> actividades) {
        super(context, 0, actividades);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActividadItem actividad = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.TODO, parent, false);
        }
        // TODO: Llenar los items y agregarlos a la lista.
        return convertView;
    }
}
