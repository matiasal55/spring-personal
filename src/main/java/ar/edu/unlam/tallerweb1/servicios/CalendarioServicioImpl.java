package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import ar.edu.unlam.tallerweb1.repositorios.CalendarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("calendarioServicio")
@Transactional
public class CalendarioServicioImpl implements  CalendarioServicio{
    private CalendarioRepositorio repositorioCalendario;

    @Autowired
    public CalendarioServicioImpl(CalendarioRepositorio repositorioCalendario){
        this.repositorioCalendario=repositorioCalendario;
    }

    @Override
    public ArrayList<Calendario> obtenerCalendarios() {
        return repositorioCalendario.todosLosCalendarios();
    }

    @Override
    public Calendario obtenerUnCalendarioEspecifico(String profesion) {
        return repositorioCalendario.unCalendarioEspecifico(profesion);
    }
}
