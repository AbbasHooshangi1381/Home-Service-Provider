package model;

import base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "employer")
@Entity
public class Employer extends BaseEntity<Integer> {

    private String firstname;
    private String lastName;
 //   private String userName;
  //  private String password;
    private Integer salary;


}
