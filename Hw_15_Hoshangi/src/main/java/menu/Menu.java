package menu;

import application.ApplicationContext;
import enumuration.LessonStatus;
import enumuration.RateOfTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.*;
import repository.TeacherRepository;
import service.LessonService;
import service.SelectedLessonService;
import service.StudentService;
import service.impl.EmployerServiceImpl;
import service.impl.LessonServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.TeacherServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Menu {
    /*Teacher teacher;
    RateOfTeacher rate;
    TeacherServiceImpl teacherService;
    LessonStatus lessonStatus;
    StudentServiceImpl studentService;
    StudentService studentServicee;
    LessonServiceImpl lessonService;
    SelectedLessonService selectedLessonService;
    TermOfTeacher termOfTeacher;
    EmployerServiceImpl emp;
    Student student;*/
    Scanner scanner = new Scanner(System.in);

    public void publicMenu() {

                /*System.out.println("please enter your userName :");
                String inputUserName = scanner.nextLine();

                System.out.println("please enter your password :");
                String inputPassword = scanner.nextLine();

                boolean loggedIn = false;

                if (checkStudentLogin(inputUserName, inputPassword)) {
                    System.out.println("Student logged in successfully!");
                    loggedIn = true;
                } else if (checkTeacherLogin(inputUserName, inputPassword)) {
                    System.out.println("Teacher logged in successfully!");
                    loggedIn = true;
                } else if (checkEmployerLogin(inputUserName, inputPassword)) {
                    System.out.println("Employee logged in successfully!");
                    loggedIn = true;
                } else {
                    System.out.println("Invalid username or password!");
                }
                if (loggedIn) {*/
        System.out.println("Who are you again ?");
        System.out.println("employer-->1");
        System.out.println("student-->2");
        System.out.println("teacher-->3");
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
                        System.out.println("Teacher Firstname: ");
                        String teacherFirstName = scanner.nextLine();

                        System.out.println("Teacher Lastname: ");
                        String teacherLastName = scanner.nextLine();

                        System.out.println("Teacher PhoneNumber: ");
                        String teacherPhoneNumber = scanner.nextLine();

  /*                      RateOfTeacher selectedRate = selectRateOfTeacher();
                        System.out.println("Selected Rate: " + selectedRate);*/

                        Teacher teacher = new Teacher();
                        teacher.setFirstname(teacherFirstName);
                        teacher.setLastName(teacherLastName);
                        teacher.setPhoneNumber(teacherPhoneNumber);
                      //  teacher.setRateOfTeacher(selectedRate);
                        ApplicationContext.getTeacherService().saveOrUpdate(teacher);


                        Teacher teacher1 = new Teacher();
                        teacher1.setId(1);

                        Lesson lesson = new Lesson();
                        lesson.setId(1);

                        TermOfTeacher termOfTeacher = new TermOfTeacher();
                        termOfTeacher.setId(1);
                        break;

                                    /*Lesson lesson = new Lesson(5, "daefe", "fefe", "efefew", 5, LessonStatus.PASS, teacher1);
                                    lessonService.saveOrUpdate(lesson);*/


                                   /* SelectedLesson selectedLesson = new SelectedLesson();
                                    selectedLesson.setId(1);*/


  /*                  case 2:
                        System.out.println("Student Firstname: ");
                        String studentFirstname = scanner.nextLine();

                        System.out.println("Student Lastname: ");
                        String studentLastname = scanner.nextLine();

                        System.out.println("Student Code: ");
                        String studentCode = scanner.nextLine();

                        Student student = new Student();
                        student.setFirstname(studentFirstname);
                        student.setLastName(studentLastname);
                        student.setStudentCode(studentCode);
                        studentServicee.saveOrUpdate(student);
                        break;


                    case 3:
                        Employer employer = new Employer();

                        System.out.println("Firstname of Employer : ");
                        String EmployerFirstname = scanner.nextLine();
                        employer.setFirstname(EmployerFirstname);

                        System.out.println("Lastname of Employer: ");
                        String Employerlastname = scanner.next();
                        employer.setLastName(Employerlastname);
                        emp.saveOrUpdate(employer);
                        break;

                    case 4:
                        System.out.println("LessonName: ");
                        String lessonName = scanner.nextLine();

                        System.out.println("UnitCountOfLesson: ");
                        int unitCountOfLesson = scanner.nextInt();

                        System.out.println("lesson status: ");
                        lessonStatus.LessonStatusLoop();
                        LessonStatus status = LessonStatus.valueOf(scanner.next().toUpperCase());

              *//*          Teacher teacher1=new Teacher();
                        teacher1.setId(1);*//*


                        break;


                    case 5:
                        System.out.println(" your salary is 5.000.000");
                        break;
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
                        break;
                    case 2:
                        System.out.println(" All lessons are : ");
                        lessonService.loadAllLessons();
                        break;
                    case 3:


                    case 4:
                        System.out.println(" selected lesson with grade of it :");
                        String lessonName = scanner.nextLine();
                        selectedLessonService.lessonAlreadyChosen(lessonName);
                        break;
                    case 5:
                        System.out.println("finished");
                        break;
                    default:
                        System.out.println("Wrong option selected!");
                        break;
                }

            case 3:
                System.out.println("enter your id to find teacher : -->1");
                System.out.println("import grade of student : -->2");
                System.out.println("salary pay : -->3");
                System.out.println("Exit : -->4");
                int selectOption4 = scanner.nextInt();
                scanner.nextLine();
                switch (selectOption4) {
                    case 1:
                        System.out.println("please enter id of teacher");
                        Integer select_id = scanner.nextInt();
                        teacherService.findById(select_id);

                        break;
                    case 2:

                        System.out.println(" enter id of student");
                        Integer enter_id = scanner.nextInt();

                        System.out.println(" enter grade of student");
                        Integer enter_grade = scanner.nextInt();

                        selectedLessonService.addGrade(enter_id, enter_grade);

                        break;
                    case 3:
                        System.out.println("Enter the number of terms: ");
                        Integer term = scanner.nextInt();

                        System.out.println("Enter the teacher's role (doctor / coTeacher): ");
                        String role = scanner.next();

                        System.out.println("Enter your ID ");
                        Integer ID = scanner.nextInt();

                        if (role.equalsIgnoreCase("doctor")) {
                            rate = RateOfTeacher.doctor;
                        } else if (role.equalsIgnoreCase("coTeacher")) {
                            rate = RateOfTeacher.coTeacher;
                        } else {
                            System.out.println("Invalid role!");
                            break;
                        }

                        Optional<Teacher> byId = teacherService.findById(ID);

                        double salary = termOfTeacher.calculate(term, rate, ID);
                        System.out.println("Salary: " + byId + "-----" + salary);

                        break;

                    case 4:
                        System.out.println("finished");
                        break;
                    default:
                        System.out.println("Wrong option selected!");
                        break;


                }

        }


    }
    //}


    public boolean checkStudentLogin(String username, String password) {
        Student login = studentService.login(username, password);
        return login != null;
    }

    public boolean checkTeacherLogin(String username, String password) {
        Teacher login = teacherService.login(username, password);
        return login != null;
    }

    public boolean checkEmployerLogin(String username, String password) {
        Employer login = emp.login(username, password);
        return login != null;
    }


    public static RateOfTeacher selectRateOfTeacher() {
        Scanner scanner = new Scanner(System.in);
        RateOfTeacher[] rates = RateOfTeacher.values();

        System.out.println("Select Rate of Teacher:");

        for (int i = 0; i < rates.length; i++) {
            System.out.println((i + 1) + ". " + rates[i]);
        }

        int selectedOption = 0;

        while (selectedOption <= 0 || selectedOption > rates.length) {
            System.out.print("Enter the number of the selected rate: ");
            selectedOption = scanner.nextInt();
            scanner.nextLine(); // skip newline
        }

        return rates[selectedOption - 1];
    }*/
}}}}