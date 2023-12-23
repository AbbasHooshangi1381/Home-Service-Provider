package entity;

import base.domin.BaseEntity;
import entity.enumuration.NameOfBank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@SequenceGenerator(name = "Card", schema = "hw15")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card extends BaseEntity<Integer> {

    @Enumerated(value = EnumType.STRING)
    NameOfBank nameOfBank;

    String numberOfCard;

    @Range(min = 0, max = 990000000, message = "you dont have amount !!")
    Double amountOfCard;

    LocalDate expiredDate;

    Integer cvv2;

    @ManyToOne
    Student student;

   public Card(Integer integer, NameOfBank nameOfBank, Double amountOfCard, LocalDate expiredDate,
                Integer cvv2,String numberOfCard) {
        super(integer);
        this.nameOfBank = nameOfBank;
        this.amountOfCard = 9000000.00;
        this.expiredDate = expiredDate;
        this.cvv2 = cvv2;
        this.numberOfCard=numberOfCard;
    }

    public Card( NameOfBank nameOfBank, String numberOfCard, LocalDate expiredDate, Integer cvv2) {

        this.nameOfBank = nameOfBank;
        this.numberOfCard = numberOfCard;
        this.expiredDate = expiredDate;
        this.cvv2 = cvv2;
    }


}
