package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Test;

public class ControladorRegistrarmeTest {

    private ControladorRegistrarme controladorRegistrarme=new ControladorRegistrarme();
    private static final DatosRegistro USUARIO = new DatosRegistro("usuario@correo.com","12345","12345");

    @Test
    public void puedoRegistrarmeConUsuarioNuevoYClaveCorrecta(){
        givenQueElUsuarioNoExiste(USUARIO);
        whenRegistroElUsuario(USUARIO);
        thenElRegistroEsExitoso();
    }

    private void givenQueElUsuarioNoExiste(DatosRegistro usuario) {
    }

    private void whenRegistroElUsuario(DatosRegistro datos) {
        controladorRegistrarme.registrarUsuario(datos);
    }

    private void thenElRegistroEsExitoso() {
    }
}
