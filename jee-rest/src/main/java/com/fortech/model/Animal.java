package com.fortech.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "animal")
@NamedQueries({
        @NamedQuery(name = "Animal.findAll", query = "SELECT t FROM Animal t")
})
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

}
