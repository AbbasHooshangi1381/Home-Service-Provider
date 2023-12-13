package entity;

import base.domin.BaseEntity;
import entity.enumuration.NameOfBank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity<Integer> {

    @Enumerated
    NameOfBank nameOfBank;

    Integer numberOfCard;

    Integer amountOfCard;

    LocalDate expiredDate;

    Integer cvv2;

    @ManyToOne
    @JoinColumn(name = "student_id")

    Student student;

   public Card(Integer integer, NameOfBank nameOfBank, Integer amountOfCard, LocalDate expiredDate,
                Integer cvv2,Integer numberOfCard) {
        super(integer);
        this.nameOfBank = nameOfBank;
        this.amountOfCard = 9000000;
        this.expiredDate = expiredDate;
        this.cvv2 = cvv2;
        this.numberOfCard=numberOfCard;
    }

    public Card(Integer integer, NameOfBank nameOfBank, Integer numberOfCard, LocalDate expiredDate, Integer cvv2) {
        super(integer);
        this.nameOfBank = nameOfBank;
        this.numberOfCard = numberOfCard;
        this.expiredDate = expiredDate;
        this.cvv2 = cvv2;
    }


}
