package Q2.repository.impl;

import Q2.entity.Person;
import Q2.entity.Student;
import Q2.repository.PersonRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class StudentRepositoryImpl implements PersonRepository<Student> {
    Session session;

    public StudentRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void save(Student student) {
        try {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }

    @Override
    public void update(Student student, Integer id) {

        String hql = "update Student s set s.firstName=:firstName where s.id=:id";

        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("firstName", student.getFirstName());
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();


        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public Student delete(Integer id) {
        String hql = "delete from Student where id=:id";

        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return null;
    }

    @Override
    public List<Student> loadAll() {
        String hql = "select s from  Student  s ";
        Query<Student> query = session.createQuery(hql, Student.class);
        return query.list();
    }

    @Override
    public Student find(Integer id) {
        String hql = "select s from Student s where id=:id";

        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.getSingleResult();
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return null;
    }

    @Override
    public boolean contains(Integer id) {
        try {
            session.beginTransaction();
            Query<Student> query = session.createQuery("FROM Student WHERE  id=:id", Student.class);
            query.setParameter("id", id);
            Student retrievedStudent = query.uniqueResult();
            session.getTransaction().commit();
            return retrievedStudent != null;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }
}
