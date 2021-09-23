package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;

public class ControladorRegistrarmeTest {

    private ControladorRegistrarme controladorRegistrarme=new ControladorRegistrarme();
    private static final DatosRegistro USUARIO = new DatosRegistro("usuario@correo.com","12345","12345");
    private static final DatosRegistro USUARIO_CON_MAIL_INCORRECTO = new DatosRegistro("correo.com","12345","12345");

    @Test
    public void puedoRegistrarmeConUsuarioNuevoYClaveCorrecta(){
        ModelAndView mav=whenRegistroElUsuario(USUARIO);
        thenElRegistroEsExitoso(mav);
    }

    @Test
    public void noPuedoRegistrarmeConMailDeUsuarioIncorrecto(){
        ModelAndView mav=whenRegistroElUsuario(USUARIO_CON_MAIL_INCORRECTO);
        thenElRegistroFalla(mav);
    }

    private ModelAndView whenRegistroElUsuario(DatosRegistro datos) {
        return controladorRegistrarme.registrarUsuario(datos);
    }

    private void thenElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("login");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro exitoso");
    }

    private void thenElRegistroFalla(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registrarme");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro fallido");
    }


}
