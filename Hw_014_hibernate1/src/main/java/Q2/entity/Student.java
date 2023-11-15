package Q2.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Student extends Person {
    @NotNull
    private Integer studentCode;
    @NotNull
    private String field;
    @NotNull
    private Integer InputYear;


}
