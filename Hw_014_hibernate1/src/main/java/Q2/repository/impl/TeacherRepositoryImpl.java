package Q2.repository.impl;

import Q2.entity.Person;
import Q2.entity.Student;
import Q2.entity.Teacher;
import Q2.service.PersonService;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TeacherRepositoryImpl implements PersonService<Teacher> {
    Session session;

    public TeacherRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
    public void save(Teacher teacher) {
        try {
            session.beginTransaction();
            session.save(teacher);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }

    @Override
    public void update(Teacher teacher, Integer id) {

        String hql = "update Teacher t set t.firstName=:firstName where t.id=:id";

        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("firstName", teacher.getFirstName());
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
    public Teacher delete(Integer id) {
        String hql = "delete from Teacher where id=:id";

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
    public List<Teacher> loadAll() {
        String hql = "select t from  Teacher  t ";
        Query<Teacher> query = session.createQuery(hql, Teacher.class);
        return query.list();
    }

    @Override
    public Teacher find(Integer id) {
        String hql = "select t from Teacher t where id=:id";

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
            Query<Teacher> query = session.createQuery("FROM Teacher WHERE  id=:id", Teacher.class);
            query.setParameter("id", id);
            Person retrievedTeacher = query.uniqueResult();
            session.getTransaction().commit();
            return retrievedTeacher != null;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }
}
