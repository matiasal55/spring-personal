package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Multa;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioMultasImpl implements RepositorioMultas{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Multa> buscarPorUsuario(Usuario usuario) {
        return sessionFactory.getCurrentSession().createCriteria(Multa.class).add(Restrictions.eq("infractor", usuario)).list();
    }

    @Override
    public List<Multa> buscarPorMail(String mail) {
        return sessionFactory.getCurrentSession().createCriteria(Multa.class).createAlias("infractor","i").add(Restrictions.like("i.email", "%"+mail+"%")).list();
    }
}
