package menu;

import domain.enumurations.ExpertStatus;
import domain.userEntity.Expert;
import image.ImageInput;
import util.ApplicationContext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static menu.BaseMenu.firstMenu;
import static menu.BaseMenu.scanner;
import static menu.CustomerMenu.validationEmails;
import static menu.CustomerMenu.validationNames;
import static validation.Validation.generateRandomPassword;

public class ExpertsMenu {
    public static Optional<Expert> expertOptional;

    public static void signInOrSignUpExpert() {
        System.out.println("1.sign In");
        System.out.println("2.sign up");


        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> signInExpert();

                case 2 -> signUpExpert();

                default -> System.out.println("---Error404---");
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }

    }

    public static void signInExpert() throws SQLException {

        boolean isTrue = true;
        while (isTrue) {

            String username = "htrhrth23";


            String password = "3uvx$Y5j";

            expertOptional = ApplicationContext.getExpertService().login(username, password);


            if (expertOptional.isPresent()) {
                System.out.println("you can use system : ");
                confirmedExpert();
                isTrue = false;
            } else {
                System.out.println("username and password UnCorrect !!!!\n");
                System.out.println("Please Again ... ");
                firstMenu();

            }

        }
    }


    public static void signUpExpert() throws IOException {

        String FirstName = "ali";
        String validatedFirstName = validationNames(FirstName);

        String LastName = "joe";
        String validatedLastName = validationNames(LastName);

        String newEmailOFExpert = "";

        String emailOfExpert = "asadf@gmail.com";
        String validatedEmail = validationEmails(emailOfExpert);

        List<String> list = ApplicationContext.getExpertService().showEmail();
        if (list.contains(validatedEmail)) {
            System.out.println("i have this email in data base ");
        } else {
            newEmailOFExpert = validatedEmail;
        }

        String userName = "htrhrth23";

        String password = generateRandomPassword();

        LocalDate timeOfSignIn = LocalDate.now();

        String filePath = "D:\\file of intelli j\\dcdsfwef\\src\\main\\java\\image\\CamScanner 02-14-2022 12.36_2.jpg";

        byte[] imageData = ImageInput.uploadProfilePicture(filePath);

        ExpertStatus expertStatus = ExpertStatus.NEW;

        Integer star = 0;

        Expert expert = new Expert(validatedFirstName, validatedLastName, newEmailOFExpert,
                userName, password, timeOfSignIn, imageData, expertStatus, star);

        ApplicationContext.getExpertService().save(expert);
        System.out.println("added to data base !");

        signInOrSignUpExpert();
    }

    public static void confirmedExpert() {
        System.out.println("1. change password :");
        System.out.println("2. save photo to file in system :");
        System.out.println("enter your select:");

        try {
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select) {
                case 1 -> changePasswordOfExpert();

                case 2 -> savePhotoToSystem();

                default -> System.out.println("---Error404---");
            }
        } catch (Exception e) {
            //  e.printStackTrace();
            System.out.println("!!!WRONG!!!");

        }
    }

    public static void changePasswordOfExpert() throws SQLException {
        Integer expertId =expertOptional.get().getId();

        ApplicationContext.getExpertService().changePassword(expertId, "Fdf@er52");
        System.out.println("password is change !");

        confirmedExpert();
    }

    public static void savePhotoToSystem() throws SQLException {

         Integer expertId =expertOptional.get().getId();

        byte[] bytes = ApplicationContext.getExpertService().saveImageByIdToSystem(expertId);
        System.out.println("photo saved !");
        try (FileOutputStream fos = new FileOutputStream("D:\\منابع مکتب شریف\\final-project\\images\\New folder\\New folder"
                             + expertId + ".jpg")) {
            fos.write(bytes);
        } catch (IOException e) {
            System.err.println(" i can not create image !");
            e.printStackTrace();
        }


/*        String destinationPath = "D:\\منابع مکتب شریف\\final-project\\images\\New folder";

        File destinationDir = new File(destinationPath);

        if (destinationDir.canWrite()) {
            System.out.println("شما دسترسی لازم برای نوشتن در مسیر مقصد را دارید.");
        } else {
            System.out.println("شما دسترسی لازم برای نوشتن در مسیر مقصد را ندارید.");
        }*/


        firstMenu();
    }

}
