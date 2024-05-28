package com.demo.consultas_ant;

public class Response {
    private String puntos;

    public Response() {}

    public Response(String resultado) {
        this.puntos = resultado;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}
