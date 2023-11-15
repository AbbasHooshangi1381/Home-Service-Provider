package Q2.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Teacher extends Person {
    @NotNull
    private Integer teacherCode;
    @NotNull
    private String educationCode;
    @Enumerated(EnumType.STRING)
    private RateOfTeacher rateOfTeacher;

}
