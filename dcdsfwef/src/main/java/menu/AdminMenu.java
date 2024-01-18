package menu;

import domain.other.ExpertSubService;
import domain.serviceEntity.Service;
import domain.serviceEntity.SubService;
import domain.userEntity.Admin;
import domain.userEntity.Expert;
import util.ApplicationContext;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static menu.BaseMenu.*;
import static menu.CustomerMenu.*;

import static validation.Validation.generateRandomPassword;

public class AdminMenu {
    public static Optional<Admin> adminOptional;


    public static void signInOrSignUpAdmin() {
        System.out.println("1- sign In");
        System.out.println("2- Sign Up");
        System.out.println("3- Exit\n");
        System.out.println("Enter your select:");

        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> signInAdmin();
                case 2 -> signUpAdmin();
                case 3 -> {
                    System.out.println("exit");
                }
                default -> System.out.println("---Eror404---");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("!!!WRONG!!!");
            signInOrSignUpAdmin();
        }
    }


    public static void signInAdmin() throws SQLException {


        String username = "pojguiu2";

        String password = "Adf^5wa4";

        adminOptional = ApplicationContext.getAdminService().login(username, password);


        if (adminOptional.isPresent()) {
            firstMenuOfAdmin();

        } else {
            System.out.println("username and password UnCorrect !!!!\n");
            System.out.println("Please Again ... ");
            firstMenu();

        }

    }

    public static void signUpAdmin() throws SQLException {

        String FirstName = "ali";
        String validatedFirstName = validationNames(FirstName);

        String LastName = "ahmadi";
        String validatedLastName = validationNames(LastName);

        String newEmailOFExpert = "";

        String emailOfExpert = "okjggk@gmail.com";
        String validatedEmail = validationEmails(emailOfExpert);

        List<String> list = ApplicationContext.getAdminService().showEmail();
        if (list.contains(validatedEmail)) {
            System.out.println("i have this email");
        } else {
            newEmailOFExpert = validatedEmail;
        }

        String userName = "pojguiu2";

        String password = generateRandomPassword();

        LocalDate timeOfSignIn = LocalDate.now();

        Admin admin = new Admin(validatedFirstName, validatedLastName, newEmailOFExpert,
                userName, password, timeOfSignIn);

        ApplicationContext.getAdminService().save(admin);
        System.out.println("admin added to data base !");
        signInAdmin();
    }

    public static void firstMenuOfAdmin() {
        System.out.println("1. register service or sub service :");
        System.out.println("2. see all sub service that we have !");
        System.out.println("3. edit description of subService :");
        System.out.println("4. edit price of subService :");
        System.out.println("5. register expert as one subService ");
        System.out.println("6. confirm the status of expert");
        System.out.println("7. change password :");
        System.out.println("8. delete expert from subService :");


        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> registerServiceOrSubService();

                case 2 -> allService();

                case 3 -> changeDescription();

                case 4 -> changePrice();

                case 5 -> registerExpertInOneService();

                case 6 -> confirmedStatus();

                case 7 -> changePasswordOfAdmin();

                case 8 -> deleteExpertFromSubService();


                default -> System.out.println("---Error404---");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }

    }

    public static void registerServiceOrSubService() {

        System.out.println("1. register service :");
        System.out.println("2. register sub service :");


        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> registerService();

                case 2 -> registerSubService();

                default -> System.out.println("---Error404---");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }


    }

    public static void registerService() {
        Service service = new Service("electric");
        ApplicationContext.getServiceService().save(service);
        System.out.println("service added to data base ! ");
        firstMenuOfAdmin();
    }

    public static void registerSubService() {
        SubService subService = new SubService();
        Integer id = 674;
        System.out.println("I'm using ID number "+id+" of service to check if it does not exist in the database:");

        Service service = ApplicationContext.getServiceService().findById(id).orElse(null);

        if (service == null) {
            System.out.println("You don't have this service.");
        } else {
            String subServiceName = "bargh";
            List<SubService> subServiceList = (List<SubService>) ApplicationContext.getSubServiceService().findAll();
            String subServiceValidated = "";
            boolean subServiceExists = false;

            for (SubService subServiceLists : subServiceList) {
                if (subServiceLists.getSubServiceName().equals(subServiceName)) {
                    System.out.println("This sub service already exists!");
                    subServiceExists = true;
                    break;
                }
            }

            if (subServiceExists) {
                subServiceValidated = subServiceName;
            } else {
                System.out.println("Sub service is validated!");
            }

            subService.setSubServiceName(subServiceValidated);
            subService.setDescription("frfffffffffffff");
            subService.setPrice(5256.00);
            subService.setService(service);

            ApplicationContext.getSubServiceService().save(subService);
            System.out.println("Sub service added to the database!");

        }

        firstMenuOfAdmin();
    }

    public static void allService() {
        System.out.println("all sub service is : ");
        ApplicationContext.getSubServiceService().findAll().forEach(System.out::println);

        firstMenuOfAdmin();

/*        System.out.println("all sub service is : ");
        ApplicationContext.getSubServiceService().findAll().forEach(System.out::println);*/
    }

    public static void changeDescription() {
        Integer id = 675;
        System.out.println("I USE ID"+id+" of subService 657 TO CHANGE DESCRIPTION  : ");


        System.out.println("I use new description ");
        String newDescription = "clean it completely";

        ApplicationContext.getSubServiceService().updateDescriptionField(id, newDescription);
        System.out.println("you change the description successfully !");
        firstMenuOfAdmin();
    }

    public static void changePrice() {
        System.out.println("I use id 660 to change price ");
        Integer id = 675;

        System.out.println("I use double 250.00 to change price ");
        Double newPrice = 250.00;

        ApplicationContext.getSubServiceService().updatePriceField(id, newPrice);
        System.out.println("you change the price successfully !");
        firstMenuOfAdmin();
    }

    public static void registerExpertInOneService() {
        Integer idOfExpert = 673;
        Integer idOfSubService = 675;

        SubService subService = ApplicationContext.getSubServiceService().findById(idOfSubService).orElse(null);
        Expert expert = ApplicationContext.getExpertService().findById(idOfExpert).orElse(null);
        ExpertSubService expertSubService=new ExpertSubService();
        expertSubService.setExpert(expert);
        expertSubService.setSubService(subService);

        ApplicationContext.getExpertSubServiceService().save(expertSubService);
        firstMenuOfAdmin();

    }

    public static void confirmedStatus() {
        Integer id = 673;
        System.out.println("I use id "+id+" of expert to change the status of it : ");

        ApplicationContext.getExpertService().changeStatus(id);
        firstMenuOfAdmin();

    }

    public static void changePasswordOfAdmin() {
        ApplicationContext.getAdminService().changePassword(671, "FSasd85@");
        System.out.println("password changed ! ");
        firstMenuOfAdmin();
    }

    public static void deleteExpertFromSubService() throws SQLException {
        Integer idOfExpertSubServiceId = 676;
        ApplicationContext.getExpertSubServiceService().deleteById(idOfExpertSubServiceId);
        System.out.println("expert deleted ! ");
        firstMenu();
    }


}