package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("calendarioRepositorio")
public class CalendarioRepositorioImpl implements CalendarioRepositorio{
    private SessionFactory sessionFactory;

    @Autowired
    public CalendarioRepositorioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<Calendario> todosLosCalendarios() {
        final Session session=sessionFactory.getCurrentSession();
        return (ArrayList<Calendario>) session.createCriteria(Calendario.class).list();
    }

    @Override
    public Calendario unCalendarioEspecifico(String profesion) {
        final Session session=sessionFactory.getCurrentSession();
        return (Calendario) session.createCriteria(Calendario.class).add(Restrictions.eq("profesion",profesion)).uniqueResult();
    }
}
