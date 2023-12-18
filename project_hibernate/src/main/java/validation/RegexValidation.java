package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidation {

    public static boolean validatonFirstname(String firstName){
        Pattern pattern =Pattern.compile("^[a-zA-Z]{1,20}$");
        return firstName.matches(pattern.pattern());
    }
    public static boolean validatonLastname(String lastName){
        Pattern pattern =Pattern.compile("^[a-zA-Z]{1,20}$");
        return lastName.matches(pattern.pattern());
    }
    public static boolean validatonMothername(String motherName){
        Pattern pattern =Pattern.compile("^[a-zA-Z]{1,20}$");
        return motherName.matches(pattern.pattern());
    }
    public static boolean validationShenasnameNumber(Integer shenasnameNumber) {
        String shenasnameString = "0" + shenasnameNumber.toString();
        Pattern pattern = Pattern.compile("^0\\d{9}$");
        Matcher matcher = pattern.matcher(shenasnameString);
        return matcher.matches();
    }


    public static boolean validationNameOfUniversity(String nameOfUniversity){
        Pattern pattern =Pattern.compile("^[a-zA-Z]{1,20}$");
        return nameOfUniversity.matches(pattern.pattern());
    }
    public static boolean validationNumberOfTerm(Integer NumberOfTerm) {
        Pattern pattern = Pattern.compile("^\\d{1}$");
        Matcher matcher = pattern.matcher(NumberOfTerm.toString());
        return matcher.matches();
    }

    public static boolean validationEnterYear(Integer enterYear) {
        Pattern pattern = Pattern.compile("^\\d{4}$");
        Matcher matcher = pattern.matcher(enterYear.toString());
        return matcher.matches();
    }



}
