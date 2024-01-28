package com.example.springbootfinal.baseDomain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity<ID extends Serializable> implements Serializable{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    ID id;

}
