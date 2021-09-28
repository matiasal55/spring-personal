package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("calendarioRepositorio")
public class CalendarioRepositorioImpl implements CalendarioRepositorio{
    @Override
    public ArrayList<Calendario> todosLosCalendarios() {
        return null;
    }

    @Override
    public Calendario unCalendarioEspecifico(String profesion) {
        return null;
    }
}
