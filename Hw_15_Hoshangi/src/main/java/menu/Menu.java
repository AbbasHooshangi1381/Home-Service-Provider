package menu;

import enumuration.LessonStatus;
import enumuration.RateOfTeacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.*;
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
    static Teacher teacher;
    static RateOfTeacher rate;
    TeacherServiceImpl teacherService;
    LessonStatus lessonStatus;
    StudentServiceImpl studentService;
    StudentService studentServicee;
    LessonServiceImpl lessonService;
    SelectedLessonService selectedLessonService;
    TermOfTeacher termOfTeacher;
    EmployerServiceImpl emp;
    Student student;
    Scanner scanner = new Scanner(System.in);

    public void publicMenu() {

        System.out.println("Who are you?");
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

                        RateOfTeacher selectedRate = selectRateOfTeacher();
                        System.out.println("Selected Rate: " + selectedRate);

                        Teacher teacher = new Teacher();
                        teacher.setFirstname(teacherFirstName);
                        teacher.setLastName(teacherLastName);
                        teacher.setPhoneNumber(teacherPhoneNumber);
                        teacher.setRateOfTeacher(selectedRate);
                        teacherService.saveOrUpdate(teacher);


/*                        List<TermOfTeacher> termOfTeachers = new ArrayList<>();

                        boolean addTermOfTeacher = true;
                        while (addTermOfTeacher) {
                            System.out.print("Term: ");
                            int term = scanner.nextInt();
                            scanner.nextLine(); // خط خالی را تخطی کنید

                            System.out.print("Unit: ");
                            int unit = scanner.nextInt();
                            scanner.nextLine(); // خط خالی را تخطی کنید

                            System.out.print("Salary: ");
                            double salary = scanner.nextDouble();
                            scanner.nextLine(); // خط خالی را تخطی کنید

                            System.out.print("Rate of Teacher: ");
                            RateOfTeacher termOfTeacherRate = RateOfTeacher.valueOf(scanner.nextLine().toUpperCase());

                            TermOfTeacher termOfTeacher = new TermOfTeacher();
                            termOfTeacher.setTerm(term);
                            termOfTeacher.setUnit(unit);
                            termOfTeacher.setSalary(salary);
                            termOfTeacher.setRateOfTeacher(termOfTeacherRate);
                            termOfTeacher.setTeacher(teacher);

                            termOfTeachers.add(termOfTeacher);

                            System.out.print("Add another TermOfTeacher? (yes/no): ");
                            String addAnother = scanner.nextLine();
                            addTermOfTeacher = addAnother.equalsIgnoreCase("yes");
                        }*/

/*                        teacher.set(termOfTeachers);



                        System.out.println("Lesson Name: ");
                        String lessonName = scanner.nextLine();

                        System.out.println("Unit Count of Lesson: ");
                        int unitCountOfLesson = scanner.nextInt();
                        scanner.nextLine(); // خط خالی را تخطی کنید

                        System.out.println("Lesson Status: ");
                        LessonStatus lessonStatus = LessonStatus.valueOf(scanner.nextLine().toUpperCase());

                        Lesson lesson = new Lesson();
                        lesson.setLessonName(lessonName);
                        lesson.setUnitCountOfLesson(unitCountOfLesson);
                        lesson.setLessonStatus(lessonStatus);
                        lesson.setTeacher(teacher);



                        List<SelectedLesson> selectedLessons = new ArrayList<>();

                        boolean addSelectedLesson = true;
                        while (addSelectedLesson) {
                            System.out.println("SelectedLesson Name: ");
                            String selectedLessonName = scanner.nextLine();

                            System.out.println("SelectedLesson Unit: ");
                            Integer selectedLessonUnit = scanner.nextInt();
                            scanner.nextLine();

                            System.out.println("SelectedLesson Status: ");
                            LessonStatus selectedLessonStatus = LessonStatus.valueOf(scanner.nextLine().toUpperCase());

                            SelectedLesson selectedLesson = new SelectedLesson();
                            selectedLesson.setSelectedLessonName(selectedLessonName);
                            selectedLesson.setSelectedLessonUnit(selectedLessonUnit);
                            selectedLesson.setSelectedLessonStatus(selectedLessonStatus);
                            selectedLesson.setLesson(lesson);

                            selectedLessons.add(selectedLesson);

                            System.out.println("Add another SelectedLesson? (yes/no): ");
                            String addAnother = scanner.nextLine();
                            addSelectedLesson = addAnother.equalsIgnoreCase("yes");
                        }

                        lesson.setSelectedLessonList(selectedLessons);


                        List<Lesson> lessons = new ArrayList<>();
                        lessons.add(lesson);
                        teacher.setLesson(lessons);

                        break;*/
                        break;

                    case 2:
                        // ورود اطلاعات دانش‌آموز
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
                    // ورود اطلاعات SelectedLesson
                    //    List<SelectedLesson> selectedLessons1 = new ArrayList<>();

/*                        boolean addSelectedLesson1 = true;
                        while (addSelectedLesson1) {
                            System.out.println("SelectedLesson Name: ");
                            String selectedLessonName = scanner.nextLine();

                            System.out.println("SelectedLesson Unit: ");
                            int selectedLessonUnit = scanner.nextInt();
                            scanner.nextLine(); // خط خالی را تخطی کنید

                            System.out.println("SelectedLesson Status: ");
                            LessonStatus selectedLessonStatus = LessonStatus.valueOf(scanner.nextLine().toUpperCase());

                            System.out.println("Term: ");
                            int term = scanner.nextInt();
                            scanner.nextLine(); // خط خالی را تخطی کنید

                            System.out.println("Grade: ");
                            int grade = scanner.nextInt();
                            scanner.nextLine(); // خط خالی را تخطی کنید

                            SelectedLesson selectedLesson = new SelectedLesson();
                            selectedLesson.setSelectedLessonName(selectedLessonName);
                            selectedLesson.setSelectedLessonUnit(selectedLessonUnit);
                            selectedLesson.setSelectedLessonStatus(selectedLessonStatus);
                            selectedLesson.setTerm(term);
                            selectedLesson.setGrade(grade);
                            selectedLesson.setStudent(student);

                            selectedLessons1.add(selectedLesson);

                            System.out.println("Add another SelectedLesson? (yes/no): ");
                            String addAnother = scanner.nextLine();
                            addSelectedLesson1 = addAnother.equalsIgnoreCase("yes");
                        }

                        // ورود اطلاعات درس
                        System.out.println("Lesson Name: ");
                        String lessonName1 = scanner.nextLine();

                        System.out.println("Unit Count of Lesson: ");
                        int unitCountOfLesson1 = scanner.nextInt();
                        scanner.nextLine(); // خط خالی را تخطی کنید

                        System.out.println("Lesson Status: ");
                        LessonStatus lessonStatus1 = LessonStatus.valueOf(scanner.nextLine().toUpperCase());

                        Lesson lesson1 = new Lesson();
                        lesson1.setLessonName(lessonName1);
                        lesson1.setUnitCountOfLesson(unitCountOfLesson1);
                        lesson1.setLessonStatus(lessonStatus1);
                        // lesson1.setTeacher(teacher); // تنظیم معلم برای درس

                        // تنظیم SelectedLesson های وارد شده برای دانش‌آموز
                        student.setSelectedLessonList(selectedLessons1);

                        break;*/

                    case 3:
                        Employer employer = new Employer();

                        System.out.println("Firstname of Employer : ");
                        String EmployerFirstname = scanner.nextLine();
                        employer.setFirstname(EmployerFirstname);

                        System.out.println("Lastname of Employer: ");
                        String Employerlastname = scanner.next();
                        employer.setLastName(Employerlastname);
                        emp.saveOrUpdate(employer);
/*                        System.out.println("userName of Employer : ");
                        String EmployeruserName = scanner.next();

                        System.out.println("password of Employer : ");
                        String Employerpassword = scanner.next();*/
                        break;

                    case 4:
                        System.out.println("LessonName: ");
                        String lessonName = scanner.nextLine();

                        System.out.println("UnitCountOfLesson: ");
                        int unitCountOfLesson = scanner.nextInt();

                        System.out.println("lesson status: ");
                        lessonStatus.LessonStatusLoop();
                        LessonStatus status = LessonStatus.valueOf(scanner.next().toUpperCase());

                        Lesson lesson = new Lesson();
                        lesson.setLessonName(lessonName);
                        lesson.setUnitCountOfLesson(unitCountOfLesson);
                        lesson.setLessonStatus(status);
                        lesson.setTeacher(new Teacher());
                        lesson.setSelectedLessonList(new ArrayList<>());
                        lessonService.saveOrUpdate(lesson);


                        /*System.out.println("Add Selected Lesson? (Yes/No)");
                        String addSelectedLesson = scanner.next().toUpperCase();
                        while (addSelectedLesson.equals("Yes")) {
                            SelectedLesson selectedLesson = new SelectedLesson();

                            System.out.println("countOfUnit: ");
                            int countOfUnit = scanner.nextInt();
                            selectedLesson.setCountOfUnit(countOfUnit);

                            System.out.println("term: ");
                            int term = scanner.nextInt();
                            selectedLesson.setTerm(term);

                            System.out.println("grade: ");
                            int grade = scanner.nextInt();
                            selectedLesson.setGrade(grade);

                            selectedLesson.setStudent(new Student());
                            selectedLesson.setLesson(lesson);
                            lesson.getSelectedLessonList().add(selectedLesson);

                            System.out.println("Add another Selected Lesson? (Yes/No)");
                            addSelectedLesson = scanner.next().toUpperCase();


                            Teacher teacher = new Teacher();

                            System.out.println("Teacher Firstname: ");
                            String teacherFirstName = scanner.nextLine();
                            teacher.setFirstname(teacherFirstName);

                            System.out.println("Teacher Lastname: ");
                            String teacherLastName = scanner.nextLine();
                            teacher.setLastName(teacherLastName);

                            System.out.println("Teacher PhoneNumber: ");
                            String teacherPhoneNumber = scanner.nextLine();
                            teacher.setPhoneNumber(teacherPhoneNumber);

                            System.out.println("Teacher Rate: ");
                            RateOfTeacher teacherRate2 = RateOfTeacher.valueOf(scanner.next().toUpperCase());
                            teacher.setRateOfTeacher(teacherRate2);

                            teacher.getLesson().add(lesson);*/
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



/*
        System.out.println(" please choose ");
        System.out.println("1.save or update");
        System.out.println("2.delete");
        System.out.println("3.load all");


        int select = scanner.nextInt();
        scanner.nextLine();

        System.out.println(" load all");
        teacherService.findAll();*/
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
    }
}
