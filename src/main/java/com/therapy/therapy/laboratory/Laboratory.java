package com.therapy.therapy.laboratory;

import com.therapy.therapy.common.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="laboratory")
public class Laboratory extends Model {

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private LABORATORY_CATEGORY category;


    private Double price;

}
