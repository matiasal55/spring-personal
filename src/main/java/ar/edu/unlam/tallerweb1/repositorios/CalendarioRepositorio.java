package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;

import java.util.ArrayList;
import java.util.List;

public interface CalendarioRepositorio {
    ArrayList<Calendario> todosLosCalendarios();

    Calendario unCalendarioEspecifico(String profesion);
}
