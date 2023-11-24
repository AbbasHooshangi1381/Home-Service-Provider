package model;

import base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "teacher_id")

public class Teacher extends BaseEntity<Integer> {

    private String firstname;
    private String lastName;
    private String userName;
    private String password;
    private String phoneNumber;
    @OneToMany(mappedBy = "teacher")
    List<TermOfTeacher>list;



}
