package com.kemio.aov;

class ItemActividad {
    private String titulo;
    private String descripcion;
    private String fecha;

    public ItemActividad(String titulo, String descripcion, String fecha, ActivityType type) {
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

    String getTitulo() {
        return titulo;
    }

    String getDescripcion() {
        return descripcion;
    }

    String getFecha() {
        return fecha;
    }

    enum ActivityType {
        ACTIVIDAD,
        COBERTURA,
        CAPACITACION,
        DIFUSION
    }
}
