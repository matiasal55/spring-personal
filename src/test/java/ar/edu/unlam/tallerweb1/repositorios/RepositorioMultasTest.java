package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Multa;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

public class RepositorioMultasTest extends SpringTest {

    private static final Usuario USUARIO = new Usuario("usuario@correo.com", "12345");
    @Autowired
    private RepositorioMultas repositorioMultas;

    @Test
    @Transactional @Rollback
    public void obtengoLasMultasDeUnUsuarioConInfracciones(){
        List<Multa> multasDelUsuario=new LinkedList<Multa>();
        multasDelUsuario.add(new Multa());
        multasDelUsuario.add(new Multa());

        givenUnUsuarioConMultas(USUARIO, multasDelUsuario);
        List<Multa> multas=whenBuscoLasMultasDelUsuario(USUARIO);
        thenEncuentroLasMultas(multasDelUsuario.size(), multas);
    }

    private void thenEncuentroLasMultas(int cantidadEsperada, List<Multa> multas) {
        assertThat(multas).hasSize(cantidadEsperada);
    }

    private void givenUnUsuarioConMultas(Usuario usuario, List<Multa> multasDelUsuario) {
        session().save(usuario);
        for (Multa multa : multasDelUsuario){
            multa.setInfractor(usuario);
            session().save(multa);
        }
    }

    private List<Multa> whenBuscoLasMultasDelUsuario(Usuario usuario) {
        return repositorioMultas.buscarPorUsuario(usuario);
    }

    @Test
    @Transactional @Rollback
    public void buscarLasMultasDeUsuariosConGmail(){
        List<Multa> multasDelUsuario=new LinkedList<Multa>();
        multasDelUsuario.add(new Multa());
        multasDelUsuario.add(new Multa());

        Usuario USUARIO2=new Usuario("usuario@gmail.com","123");
        givenUnUsuarioConMultas(USUARIO2, multasDelUsuario);
        List<Multa> multas=whenBuscoLasMultasDeUsuariosConMail("gmail");
        thenEncuentroLasMultas(2, multas);
    }

    private List<Multa> whenBuscoLasMultasDeUsuariosConMail(String mail) {
        return repositorioMultas.buscarPorMail(mail);

    }
}
