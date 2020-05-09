package com.dz.student.repository.impl;

import com.dz.student.model.Student;
import com.dz.student.repository.StudentDao;
import javassist.tools.web.BadHttpRequest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Student addStudent(Student student) {
        try {
            Session session = getSession();
            session.save(student);
            return student;
        } catch (HibernateException e) {
            throw new RuntimeException("Not saved");
        }
    }

    @Override
    public Student getStudentById(int userId) {
        try {
            Session session = getSession();
            Query query = session.createQuery("from Student s where s.userId = :userId");
            query.setParameter("userId", userId);
            if (query.list().size() > 0)
                return (Student) query.list().get(0);
            else
                return null;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
//        try {
//            Session session = DBConfig.getSessionFactory().openSession();
//            {
//                transaction = session.beginTransaction();
//                session.get(getClass(), Student.class);
//                transaction.commit();
//            }
//        } catch (HibernateException e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        }
    }

    @Override
    public List<Student> getStudents() {
        return getSession().createQuery("from Student").list();
    }

    @Override
    public Student updateStudent(Student student) {
        return null;
    }

    @Override
    public String deleteStudentById(String userId) {
        return null;
    }
}
