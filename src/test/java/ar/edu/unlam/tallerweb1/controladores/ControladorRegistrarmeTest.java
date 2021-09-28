package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ControladorRegistrarmeTest {

    private final ServicioLogin servicioLogin=mock(ServicioLogin.class);
    private final ControladorRegistrarme controladorRegistrarme=new ControladorRegistrarme(servicioLogin);
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
