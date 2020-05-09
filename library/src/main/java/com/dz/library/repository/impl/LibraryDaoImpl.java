package com.dz.library.repository.impl;

import com.dz.library.model.Book;
import com.dz.library.repository.LibraryDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryDaoImpl implements LibraryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Book addBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.save(book);
        return book;
    }

    public List<Book> getById(String bookId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Book b where b.bookId = :bookId");
        query.setParameter("bookId", bookId);
        return query.list();
    }

    @Override
    public Object getBookQuantity(String bookId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select quantity FROM Book b where b.bookId = :bookId");
        query.setParameter("bookId", bookId);
        return query.list().get(0);
    }

    @Override
    public Integer updateBook(Book book, String quantity) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Book b set b.quantity = :quantity where b.bookId = :bookId");
        query.setParameter("quantity", quantity);
        query.setParameter("bookId", book.getBookId());
        return (Integer) query.executeUpdate();
//        Query query = session.createQuery("from Book b where b.bookId = :bookId");
//        query.setParameter("bookId", book.getBookId());
//        if (query.list().size() > 0) {
//            Book book1 = (Book) query.list().get(0);
//            book1.setQuantity("");
//            session.update(book);
//            return book;
//        } else
//            return null;
//        Book book = session.get(Book.class, book.getBookId());
    }

    @Override
    public Integer updateBookQuantity(String bookId, Integer quantity) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("UPDATE Book b set b.quantity = :quantity where b.bookId = :bookId");
        query.setParameter("quantity", quantity);
        query.setParameter("bookId", bookId);
        return (Integer) query.executeUpdate();
    }
}
