package com.example.appmovilrecuperacion.ui.model;

import java.util.ArrayList;

public class Game {
    public int idGame;
    public String nombreJuego;
    public String fechaSalida;

    public String valoracionPersonal;
    public String caratula;

    public ArrayList<Game> games;

    public Game(String nombreJuego, String fechaSalida, String valoracionPersonal, String caratula) {
        this.nombreJuego = nombreJuego;
        this.fechaSalida = fechaSalida;
        this.valoracionPersonal = valoracionPersonal;
        this.caratula = caratula;
    }
}
