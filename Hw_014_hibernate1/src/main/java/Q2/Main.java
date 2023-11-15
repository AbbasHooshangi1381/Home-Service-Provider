package Q2;

import Q2.apllication.ApplicationContext;
import Q2.entity.Person;
import Q2.entity.Student;
import Q2.entity.Teacher;

import java.util.Date;

import static Q2.entity.RateOfTeacher.proffoser;

public class Main {
    public static void main(String[] args) {

        Person person=new Person(null,"samyar","jahroodi",new Date(2000));
        Person person2=new Person(null,"hosein","rezaee",new Date(1995));

        Student student=new Student(65,"math",1385);
        Student student2=new Student(75,"science",1370);

        Teacher teacher=new Teacher(6589,"NUMBER",proffoser);

        //ApplicationContext.getPersonService().save(person);
        //ApplicationContext.getPersonService().save(person2);
     //  ApplicationContext.getPersonService().update(person2,1);
        //ApplicationContext.getPersonService().delete(1);
       // System.out.println(ApplicationContext.getPersonService().loadAll());
       // System.out.println(ApplicationContext.getPersonService().find(5));
       // ApplicationContext.getPersonService().contains(1);


        ApplicationContext.getTeacherServiceImpl().save(teacher);

    }

}
