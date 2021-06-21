package by.tms.dao;

import by.tms.entity.Address;
import by.tms.entity.Tag;
import by.tms.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoImpl {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Address address) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(address);
    }

    public Address findByStreetAndHome(String street, String home) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from Address where street like :street and home like :home", Address.class)
                .setParameter("street", street)
                .setParameter("home", home)
                .getSingleResult();
    }

    public boolean contains(String street, String home) {
        Session currentSession = sessionFactory.getCurrentSession();
        Long isExistIndicator = (Long) currentSession
                .createQuery("select count(*) from Address where street like :street and home like :home")
                .setParameter("street", street)
                .setParameter("home", home)
                .uniqueResult();
        return isExistIndicator > 0L;
    }

    public void delete(Address address) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(address);
    }
}
