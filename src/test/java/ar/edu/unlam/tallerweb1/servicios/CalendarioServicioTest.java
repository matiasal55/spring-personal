package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import ar.edu.unlam.tallerweb1.repositorios.CalendarioRepositorio;
import org.hibernate.JDBCException;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CalendarioServicioTest {
    private static final String PROFESION = "Cardiologia";
    private CalendarioRepositorio repositorioCalendario=mock(CalendarioRepositorio.class);
    private CalendarioServicio servicioCalendario=new CalendarioServicioImpl(repositorioCalendario);
    private Calendario calendario =new Calendario();

    @Test
    public void obtenerTodosLosCalendarios(){
        givenSolicitoTodosLosCalendarios();
        ArrayList<Calendario> calendarios= whenHagoLaSolicitud();
        thenDeberiaTenerTodosLosCalendarios(calendarios);
    }

    @Test
    public void obtenerUnCalendarioEspecifico(){
        givenSolicitoUnCalendarioEspecifico();
        Calendario calendarioTest=whenHagoLaSolicitudDeUnSoloCalendario();
        thenDeberiaCorresponderAlCalendarioElegido(calendarioTest);
    }

    @Test(expected = Exception.class)
    public void errorDeConexionALaBaseDeDatos(){
        givenOcurreUnProblemaDeConexionALaBD();
        Calendario calendarioTest=whenHagoLaSolicitudDeUnSoloCalendario();
        thenApareceriaLaExcepcion(calendarioTest);
    }

    private void thenApareceriaLaExcepcion(Calendario calendarioTest) {
        verify(repositorioCalendario, times(1)).unCalendarioEspecifico(PROFESION);
    }

    private void givenOcurreUnProblemaDeConexionALaBD() {
        when(repositorioCalendario.unCalendarioEspecifico(PROFESION)).thenThrow(Exception.class);
    }

    private void givenSolicitoUnCalendarioEspecifico() {
        calendario.setProfesion(PROFESION);
        when(repositorioCalendario.unCalendarioEspecifico(PROFESION)).thenReturn(calendario);
    }

    private Calendario whenHagoLaSolicitudDeUnSoloCalendario() {
        return repositorioCalendario.unCalendarioEspecifico(PROFESION);
    }

    private void thenDeberiaCorresponderAlCalendarioElegido(Calendario calendarioTest) {
        assertThat(calendarioTest).isEqualTo(calendario);
    }

    private void givenSolicitoTodosLosCalendarios() {
        calendario.setProfesion(PROFESION);
        when(repositorioCalendario.todosLosCalendarios()).thenReturn(new ArrayList<>());
    }

    private ArrayList<Calendario> whenHagoLaSolicitud() {
        return servicioCalendario.obtenerCalendarios();
    }

    private void thenDeberiaTenerTodosLosCalendarios(ArrayList<Calendario> calendarios) {
        assertThat(calendarios.contains(calendario));
    }
}
