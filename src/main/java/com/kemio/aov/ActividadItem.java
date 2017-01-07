package com.kemio.aov;

/**
 * Created by Facundo Mendoza on 7/1/2017.
 */

public class ActividadItem {
    private String titulo;
    private String descripcion;
    private String fecha;

    public ActividadItem(String titulo, String descripcion, String fecha, ActivityType type) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        switch (type) {
            case ACTIVIDAD:

                break;
            case COBERTURA:

                break;
            case CAPACITACION:

                break;
            case DIFUSION:

                break;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public enum ActivityType {
        ACTIVIDAD,
        COBERTURA,
        CAPACITACION,
        DIFUSION
    }
    /*public void showNotification(String titulo, String descripcion){
        Notification notif  = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(titulo)
                .setContentText(descripcion).build();
        NotificationManager notMan = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notMan.notify(0, notif);
    }(*/
}
