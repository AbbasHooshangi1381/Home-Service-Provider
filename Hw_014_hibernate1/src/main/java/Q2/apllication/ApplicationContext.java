package Q2.apllication;

import Q2.config.SessionFactoryProvider;
import org.hibernate.Session;
import Q2.repository.PersonRepository;
import Q2.repository.impl.PersonRepositoryImpl;
import Q2.service.PersonService;
import Q2.service.impl.PersonServiceImpl;

public class ApplicationContext {

    private static Session session;
    private static PersonRepository personRepository;
    private static PersonService personService;

    static {
        session = SessionFactoryProvider.getSessionFactory().openSession().getSession();
        personRepository = new PersonRepositoryImpl(session);
        personService = new PersonServiceImpl(personRepository);


    }

    public static PersonService getPersonService() {
        return personService;
    }


}
