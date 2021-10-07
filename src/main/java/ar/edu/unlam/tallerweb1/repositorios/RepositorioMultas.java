package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Multa;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import java.util.List;

public interface RepositorioMultas {
    List<Multa> buscarPorUsuario(Usuario usuario);

    List<Multa> buscarPorMail(String mail);
}
