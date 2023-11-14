package Q2;

import Q2.apllication.ApplicationContext;
import Q2.entity.Person;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Person person=new Person(null,"samyar","jahroodi",new Date(2000));
        Person person2=new Person(null,"hosein","rezaee",new Date(1995));

       // ApplicationContext.getPersonService().save(person);
        //ApplicationContext.getPersonService().save(person2);
    //  ApplicationContext.getPersonService().update(person2,1);
        //ApplicationContext.getPersonService().delete(1);
       // System.out.println(ApplicationContext.getPersonService().loadAll());
        System.out.println(ApplicationContext.getPersonService().find(5));
    }

}
