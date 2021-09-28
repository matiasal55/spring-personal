package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("calendarioServicio")
@Transactional
public class CalendarioServicioImpl implements  CalendarioServicio{

    @Override
    public ArrayList<Calendario> obtenerCalendarios() {
        return null;
    }

    @Override
    public Calendario obtenerUnCalendarioEspecifico(String PROFESION) {
        return null;
    }
}
