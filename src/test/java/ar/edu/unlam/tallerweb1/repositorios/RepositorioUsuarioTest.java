package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Cuenta;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class RepositorioUsuarioTest extends SpringTest {

    private static final String ADMIN = "ADMIN";
    private static final String INVITADO = "INVITADO";

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Test
    @Rollback @Transactional
    public void buscarPorRolDeberiaDevolverSoloUsuarioConEseRol(){
        givenExisteUsuarioConRol(ADMIN, 2);
        givenExisteUsuarioConRol(INVITADO, 3);
        List<Usuario> usuarios=whenBuscoUsuarioConRol(ADMIN);
        thenEncuentro(usuarios,2);
    }

    private void thenEncuentro(List<Usuario> usuarios, int usuariosEncontrados) {
        assertThat(usuarios).hasSize(usuariosEncontrados);
    }

    private List<Usuario> whenBuscoUsuarioConRol(String rol) {
        return repositorioUsuario.buscarUsuarioPorRol(rol);
    }

    private void givenExisteUsuarioConRol(String rol, int cantidadDeUsuarios) {
        for(int i=0;i<cantidadDeUsuarios;i++){
            Usuario nuevo=new Usuario();
            nuevo.setEmail("usuario-"+i+"-"+rol+"@correo.com");
            nuevo.setPassword("12345");
            nuevo.setRol(rol);
            Cuenta cuenta=new Cuenta();
            cuenta.setCreada(new Date());
            nuevo.setCuenta(cuenta);
//            session().save(cuenta);
            session().save(nuevo);
        }
    }
}
