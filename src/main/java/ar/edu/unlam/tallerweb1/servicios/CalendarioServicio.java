package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;

import java.util.ArrayList;

public interface CalendarioServicio {
    ArrayList<Calendario> obtenerCalendarios();

    Calendario obtenerUnCalendarioEspecifico(String PROFESION);
}
