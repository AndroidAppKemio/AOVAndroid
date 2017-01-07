package com.kemio.aov;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class ListAdapterActividades extends ArrayAdapter<ItemActividad> {
    ListAdapterActividades(Context context, ArrayList<ItemActividad> actividades) {
        super(context, 0, actividades);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ItemActividad actividad = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_actividad, parent, false);
        }
        TextView txtName = (TextView) convertView.findViewById(R.id.txtNombre);
        TextView txtDescripcion = (TextView) convertView.findViewById(R.id.txtDescripcion);
        TextView txtFecha = (TextView) convertView.findViewById(R.id.txtFecha);
        ImageView imgIcono = (ImageView) convertView.findViewById(R.id.imgIcono);
        if (actividad != null) {
            txtName.setText(actividad.getTitulo());
            txtDescripcion.setText(actividad.getDescripcion());
            txtFecha.setText(actividad.getFecha());
            // TODO: Check activity type and set image properly.
            imgIcono.setImageResource(R.drawable.cruz_roja);
        }
        return convertView;
    }
}
