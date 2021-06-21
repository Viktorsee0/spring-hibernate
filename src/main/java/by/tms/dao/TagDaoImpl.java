package by.tms.dao;

import java.util.*;
import by.tms.entity.Tag;
import by.tms.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TagDaoImpl {

    @Autowired
    SessionFactory sessionFactory;

    public void save(Tag tag) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(tag);
    }

    public List<Tag> findAll(){
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from Tag ", Tag.class)
                .getResultList();
    }

    public Tag findByTagName(String tagName) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession
                .createQuery("from Tag where tagName = :tagName", Tag.class)
                .setParameter("tagName", tagName)
                .getSingleResult();
    }

    public boolean contains(String tag) {
        Session currentSession = sessionFactory.getCurrentSession();
        Long isExistIndicator = currentSession
                .createQuery("select count(*) from Tag where tagName like :tag", Long.class)
                .setParameter("tag", tag)
                .uniqueResult();
        return isExistIndicator > 0L;
    }
}
