package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Calendario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

public class CalendarioRepositorioTest extends SpringTest {
    @Autowired
    private CalendarioRepositorio repositorioCalendario;
    private Calendario cardiologia=new Calendario();
    private Calendario odontologia=new Calendario();

    @Rollback
    @Transactional
    @Test
    public void solicitoTodosLosCalendarios(){
        giverListaDeCalendarios();
        List<Calendario> calendariosTest=whenSolicitoLosCalendarios();
        thenDeberiaTenerTodosLosCalendarios(calendariosTest);
    }

    private void giverListaDeCalendarios() {
        cardiologia.setProfesion("Cardiologia");
        odontologia.setProfesion("Odontologia");
        session().save(cardiologia);
        session().save(odontologia);
    }

    private List<Calendario> whenSolicitoLosCalendarios() {
        return repositorioCalendario.todosLosCalendarios();
    }

    private void thenDeberiaTenerTodosLosCalendarios(List<Calendario> calendariosTest) {
        assertThat(calendariosTest).contains(cardiologia);
        assertThat(calendariosTest).contains(odontologia);
    }
}
