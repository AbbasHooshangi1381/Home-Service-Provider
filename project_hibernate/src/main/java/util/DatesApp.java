package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatesApp {
    public static final LocalDate dateOfSystem=LocalDate.of(1402,10,10);

    public static LocalDate addDate(String time){
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy/mm/dd");
        return LocalDate.parse(time,dateTimeFormatter);
    }
}
