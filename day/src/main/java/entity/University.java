package entity;

import base.domin.BaseEntity;
import entity.enumuration.UniversityType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class University extends BaseEntity<Integer> {

    String city;
    String universityName;

    @Enumerated(value = EnumType.STRING)
    UniversityType universityType;

    @OneToMany(mappedBy = "university")
    List<Student> studentList;

    @OneToMany(mappedBy = "university")
    List<Semester>semester;

    public University(Integer id) {
        super(id);
    }

}
