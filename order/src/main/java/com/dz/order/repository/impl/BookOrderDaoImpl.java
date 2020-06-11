package com.dz.order.repository.impl;

import com.dz.order.model.BookOrder;
import com.dz.order.repository.BookOrderDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookOrderDaoImpl implements BookOrderDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BookOrder saveBookOrder(BookOrder bookOrder) {
        Session session = sessionFactory.getCurrentSession();
        session.save(bookOrder);
        return bookOrder;
    }

    @Override
    public void updateBookQuantity(String bookID, Integer updatedQuantity) {

    }
}
