package com.dz.student.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractBaseDao<T extends C, C> {

    private SessionFactory sessionFactory;

    public AbstractBaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    abstract Class<T> getModelClass();

    Session getSession() {
        return sessionFactory.openSession();
    }

    void create(T t) {
    getSession().save(t);
    }

    public T get(String id) {
        return getSession().get(getModelClass(), id);
    }

    public void update(T t) {
        getSession().saveOrUpdate(t);
    }

}
