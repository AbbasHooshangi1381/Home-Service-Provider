package menu;

import enumuration.LessonStatus;
import enumuration.RateOfTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Employer;
import model.Lesson;
import model.Student;
import model.Teacher;
import service.LessonService;
import service.impl.LessonServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.TeacherServiceImpl;

import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Menu {
    static Teacher teacher = new Teacher();
    static RateOfTeacher rateOfTeacher;
    TeacherServiceImpl teacherService;
    LessonStatus lessonStatus;
    StudentServiceImpl studentService;
    LessonServiceImpl lessonService;
    Scanner scanner = new Scanner(System.in);

    public void publicMenu() {

        System.out.println("Who are you?");
        System.out.println("employer");
        System.out.println("student");
        System.out.println("teacher");
        int selectOption1 = scanner.nextInt();
        scanner.nextLine();
        switch (selectOption1) {
            case 1:
                System.out.println("save or update teacher-->1");
                System.out.println("save or update student-->2");
                System.out.println("save or update employer-->3");
                System.out.println("save or update or delete lesson-->4");
                System.out.println("show salary-->5");
                System.out.println("exit-->6");
                int selectOption2 = scanner.nextInt();
                scanner.nextLine();
                switch (selectOption2) {
                    case 1:

                        System.out.println("Firstname of teacher : ");
                        String firstname = scanner.nextLine();

                        System.out.println("Lastname of teacher: ");
                        String lastname = scanner.next();

                        System.out.println("userName of teacher : ");
                        String userName = scanner.next();

                        System.out.println("password of teacher : ");
                        String password = scanner.next();

                        System.out.println("phoneNumber of teacher : ");
                        String phoneNumber = scanner.next();

                        System.out.println("countOfUnit of teacher : ");
                        Integer countOfUnit = scanner.nextInt();

                        System.out.println("rateOfTeacher of teacher : ");
                        rateOfTeacher.iterateRateOfTeacherEnum();
                        String teacherRate = scanner.next().toUpperCase();
                        teacher.setRateOfTeacher(RateOfTeacher.valueOf(teacherRate));

                        Teacher teacher = new Teacher(firstname, lastname, userName, password, phoneNumber, countOfUnit, null, null);

                    case 2:
                        System.out.println("Firstname of Student : ");
                        String StudentFirstname = scanner.nextLine();

                        System.out.println("Lastname of Student: ");
                        String Studentlastname = scanner.next();

                        System.out.println("userName of Student : ");
                        String StudentuserName = scanner.next();

                        System.out.println("password of Student : ");
                        String Studentpassword = scanner.next();


                        Student student = new Student(StudentFirstname, Studentlastname, StudentuserName, Studentpassword, null);

                    case 3:
                        System.out.println("Firstname of Employer : ");
                        String EmployerFirstname = scanner.nextLine();

                        System.out.println("Lastname of Employer: ");
                        String Employerlastname = scanner.next();

                        System.out.println("userName of Employer : ");
                        String EmployeruserName = scanner.next();

                        System.out.println("password of Employer : ");
                        String Employerpassword = scanner.next();

                        System.out.println("salary of Employer : ");
                        Integer EmployerSalary = scanner.nextInt();

                        Employer employer = new Employer(EmployerFirstname, Employerlastname, EmployeruserName, Employerpassword, EmployerSalary);


                    case 4:
                        System.out.println("LessonName : ");
                        String LessonName = scanner.nextLine();

                        System.out.println("UnitCount: ");
                        Integer UnitCount = scanner.nextInt();

                        System.out.println("Field : ");
                        String Field = scanner.next();

                        System.out.println("grade : ");
                        Integer grade = scanner.nextInt();

                        System.out.println("lesson status : ");
                        lessonStatus.LessonStatusLoop();
                        Object LessonStatusSelect = scanner.next().toUpperCase();


                        Lesson lesson = new Lesson(LessonName, UnitCount, Field, grade, (LessonStatus) LessonStatusSelect, null, null);

                    case 5:
                        System.out.println(" your salary is 5.000.000");

                    case 6:
                        System.out.println("finished");
                        break;
                    default:
                        System.out.println("Wrong option selected!");
                        break;
                }

            case 2:

                System.out.println("enter your id to find student : -->1");
                System.out.println("show all lessons :-->2");
                System.out.println("select unit : -->3");
                System.out.println("selected lesson with grade of it : -->4");
                System.out.println("Exit : -->5");

                int selectOption3 = scanner.nextInt();
                scanner.nextLine();
                switch (selectOption3) {
                    case 1:
                        System.out.println(" enter your id to show");
                        Integer inputId = scanner.nextInt();
                        studentService.findById(inputId);

                    case 2:
                        System.out.println(" All lessons are : ");
                        lessonService.loadAllLessons();

                    case 3:


                    case 4:


                    case 5:
                        System.out.println("finished");
                        break;
                    default:
                        System.out.println("Wrong option selected!");
                        break;
                }

            case 3:
                System.out.println("enter your id to find teacher : -->1");
                System.out.println("show all lessons :-->2");
                System.out.println("select unit : -->3");
                System.out.println("selected lesson with grade of it : -->4");
                System.out.println("Exit : -->5");
                int selectOption4 = scanner.nextInt();
                scanner.nextLine();
                switch (selectOption4){
                    case 1:
                    System.out.println("please enter id of teacher");
                    Integer select_id = scanner.nextInt();
                    teacherService.findById(select_id);

                    case 2:





                }

        }




        System.out.println(" please choose ");
        System.out.println("1.save or update");
        System.out.println("2.delete");
        System.out.println("3.load all");


        int select = scanner.nextInt();
        scanner.nextLine();

        System.out.println(" load all");
        teacherService.findAll();
    }
}
