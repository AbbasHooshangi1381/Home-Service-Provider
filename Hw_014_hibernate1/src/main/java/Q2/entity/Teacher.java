package Q2.entity;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Teacher {

    private Integer teacherCode;

    private String educationCode;

    private RateOfTeacher rateOfTeacher;
}
