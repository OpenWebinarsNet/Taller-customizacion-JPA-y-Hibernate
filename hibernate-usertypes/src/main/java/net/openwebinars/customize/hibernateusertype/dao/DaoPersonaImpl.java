package net.openwebinars.customize.hibernateusertype.dao;

import lombok.RequiredArgsConstructor;
import net.openwebinars.customize.hibernateusertype.model.Color;
import net.openwebinars.customize.hibernateusertype.model.Persona;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class DaoPersonaImpl implements DaoPersona {

    private final SessionFactory sessionFactory;

    @Override
    public List<Persona> getAll() {
        Session s = sessionFactory.getCurrentSession();
        return s.createQuery("from Persona").list();
    }

    @Override
    public Optional<Persona> getById(long id) {
        Session s = sessionFactory.getCurrentSession();
        Persona p = s.find(Persona.class, id);
        return p == null ? Optional.empty() : Optional.of(p);
    }

    @Override
    public Persona save(Persona p) {
        Session s = sessionFactory.getCurrentSession();
        s.save(p);
        return p;
    }

    @Override
    public void edit(Persona p) {
        Session s = sessionFactory.getCurrentSession();
        s.saveOrUpdate(p);
    }

    @Override
    public void delete(long id) {
        Session s = sessionFactory.getCurrentSession();
        getById(id).ifPresent(s::delete);
    }

    @Override
    public List<Persona> getByColor(Color color) {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.createQuery("from Persona where colorAsignado = ?1");
        q.setParameter(1, color);
        return q.list();
    }
}
