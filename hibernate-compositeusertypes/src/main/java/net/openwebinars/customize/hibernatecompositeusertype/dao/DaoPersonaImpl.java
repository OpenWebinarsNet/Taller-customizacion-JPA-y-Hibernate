package net.openwebinars.customize.hibernatecompositeusertype.dao;

import lombok.RequiredArgsConstructor;
import net.openwebinars.customize.hibernatecompositeusertype.model.Direccion;
import net.openwebinars.customize.hibernatecompositeusertype.model.Persona;
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
    public List<Persona> getByDireccion(Direccion direccion) {
        Session s = sessionFactory.getCurrentSession();
        String sqlQuery = "from Persona p where p.direccion.linea1 = ?1 and p.direccion.codigoPostal = ?2" +
        " and p.direccion.poblacion = ?3 and p.direccion.provincia = ?4";
        if (direccion.getLinea2() != null)
            sqlQuery += " and p.direccion.linea2 = ?5";
        Query q = s.createQuery(sqlQuery);
        q.setParameter(1, direccion.getLinea1());
        q.setParameter(2, direccion.getCodigoPostal());
        q.setParameter(3, direccion.getPoblacion());
        q.setParameter(4, direccion.getProvincia());
        if (direccion.getLinea2() != null)
            q.setParameter(5, direccion.getLinea2());

        return q.getResultList();
    }


    @Override
    public List<Persona> getByPoblacion(String poblacion) {
        Session s = sessionFactory.getCurrentSession();
        Query q = s.createQuery("from Persona p where p.direccion.poblacion = ?1");
        q.setParameter(1, poblacion);
        return q.getResultList();

    }

}
