package net.openwebinars.customize.hibernatebasictype.dao;

import net.openwebinars.customize.hibernatebasictype.model.CuerpoCeleste;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class DaoCuerpoCelesteImpl implements DaoCuerpoCeleste {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CuerpoCeleste> getAll() {
        Session s = sessionFactory.getCurrentSession();
        return s.createQuery("from CuerpoCeleste").list();
    }

    @Override
    public Optional<CuerpoCeleste> getById(long id) {
        Session s = sessionFactory.getCurrentSession();
        CuerpoCeleste p = s.find(CuerpoCeleste.class, id);
        return p == null ? Optional.empty() : Optional.of(p);
    }

    @Override
    public CuerpoCeleste save(CuerpoCeleste p) {
        Session s = sessionFactory.getCurrentSession();
        s.save(p);
        return p;
    }

    @Override
    public void edit(CuerpoCeleste p) {
        Session s = sessionFactory.getCurrentSession();
        s.saveOrUpdate(p);
    }

    @Override
    public void delete(long id) {
        Session s = sessionFactory.getCurrentSession();
        getById(id).ifPresent(s::delete);
    }


}
