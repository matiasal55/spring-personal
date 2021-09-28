package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class CalendarioControllerTest {

    ModelAndView mav;
    private CalendarioController controladorCalendario=new CalendarioController();
    private String PROFESION ="Cardiologia";

    @Test
    public void verPaginaDeInicio(){
        mav= whenIngresoAlInicio();
        thenVeoLaPaginaHome(mav);
    }

    @Test
    public void verTodosLosCalendarios(){
        mav=whenIngresoACalendarios();
        thenVeoTodosLosCalendarios(mav);
    }

    @Test
    public void recibirUnaProfesion(){
        mav=whenSeleccionoUnaProfesion(PROFESION);
        thenVerificarQueSeRecibe(mav);
    }

    @Test
    public void verUnCalendarioDeUnaProfesionEspecifica(){
        mav= whenRecibeUnaProfesion(PROFESION);
        thenSeMuestraElCalendarioEspecifico(mav);
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
        ArrayList<String> profesiones=new ArrayList<>();
        profesiones= (ArrayList<String>) mav.getModel().get("profesiones");
        assertThat(profesiones).hasSize(2);
    }

    private ModelAndView whenSeleccionoUnaProfesion(String profesion) {
        return controladorCalendario.recibirUnaProfesion(profesion);
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
    }
}
