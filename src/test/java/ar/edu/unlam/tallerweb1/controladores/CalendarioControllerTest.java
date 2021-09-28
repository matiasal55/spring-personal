package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import ar.edu.unlam.tallerweb1.servicios.CalendarioServicio;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalendarioControllerTest {

    private ModelAndView mav;
    private final CalendarioServicio servicioCalendario=mock(CalendarioServicio.class);
    private final CalendarioController controladorCalendario=new CalendarioController(servicioCalendario);
    private final String PROFESION="Cardiologia";
    private Calendario calendario =new Calendario();

    @Test
    public void verPaginaDeInicio(){
        mav= whenIngresoAlInicio();
        thenVeoLaPaginaHome(mav);
    }

    @Test
    public void verTodosLosCalendarios(){
        givenSolicitoLosCalendarios();
        mav=whenIngresoACalendarios();
        thenVeoTodosLosCalendarios(mav);
    }

    private void givenSolicitoLosCalendarios() {
        calendario.setProfesion(PROFESION);
        ArrayList<Calendario> listaCalendarios=new ArrayList<>();
        listaCalendarios.add(calendario);
        when(servicioCalendario.obtenerCalendarios()).thenReturn(listaCalendarios);
    }

    @Test
    public void recibirUnaProfesion(){
        mav=whenRecibeUnaProfesion(PROFESION);
        thenVerificarQueSeRecibe(mav);
    }

    @Test
    public void verUnCalendarioDeUnaProfesionEspecifica(){
        givenSolicitoUnSoloCalendario();
        mav= whenRecibeUnaProfesion(PROFESION);
        thenSeMuestraElCalendarioEspecifico(mav);
    }

    private void givenSolicitoUnSoloCalendario() {
        calendario.setProfesion(PROFESION);
        when(servicioCalendario.obtenerUnCalendarioEspecifico(PROFESION)).thenReturn(calendario);
    }

    private ModelAndView whenIngresoAlInicio() {
        return controladorCalendario.irAHome();
    }

    private void thenVeoLaPaginaHome(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("home");
    }

    private ModelAndView whenIngresoACalendarios() {
        return controladorCalendario.verTodosLosCalendarios();
    }

    private void thenVeoTodosLosCalendarios(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("calendarios");
        calendario.setProfesion(PROFESION);
        ArrayList<Calendario> profesiones= (ArrayList<Calendario>) mav.getModel().get("calendarios");
        assertThat(profesiones).contains(calendario);
    }

    private void thenVerificarQueSeRecibe(ModelAndView mav) {
        assertThat(mav.getModel().get("titulo")).isEqualTo(PROFESION);
    }

    private ModelAndView whenRecibeUnaProfesion(String profesion) {
        return controladorCalendario.recibirUnaProfesion(profesion);
    }

    private void thenSeMuestraElCalendarioEspecifico(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("calendarios");
        assertThat(mav.getModel().get("titulo")).isEqualTo(PROFESION);
        assertThat(mav.getModel().get("calendario")).isEqualTo(calendario);
    }
}
