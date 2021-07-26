package com.therapy.therapy.therapy;

import com.therapy.therapy.common.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name="therapy")
public class Therapy extends Model {

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private THERAPY_CATEGORY category;

}
