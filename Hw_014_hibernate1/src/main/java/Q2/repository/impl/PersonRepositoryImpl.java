package Q2.repository.impl;

import Q2.entity.Person;
import org.hibernate.Session;
import Q2.repository.PersonRepository;

import org.hibernate.query.Query;

import java.util.List;

public class PersonRepositoryImpl implements PersonRepository<Person> {

    Session session;

    public PersonRepositoryImpl(Session session) {
        this.session = session;
    }

    @Override
        public void save(Person person) {
        try {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(Person person, Integer id) {

        String hql = "update Person p set p.firstName=:firstName where p.id=:id";

        try {
            session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("firstName", person.getFirstName());
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
    public Person delete(Integer id) {
        String hql = "delete from Person where id=:id";

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
    public List<Person> loadAll() {
        String hql = "select p from  Person  p ";
        Query<Person> query = session.createQuery(hql, Person.class);
        return query.list();
    }

    @Override
    public Person find(Integer id) {
        String hql = "select p from Person p where id=:id";

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
            Query<Person> query = session.createQuery("FROM Person WHERE  id=:id", Person.class);
            query.setParameter("id", id);
            Person retrievedPerson = query.uniqueResult();
            session.getTransaction().commit();
            return retrievedPerson != null;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }
    }
}