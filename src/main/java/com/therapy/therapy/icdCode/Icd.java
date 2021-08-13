package com.therapy.therapy.icdCode;

import com.therapy.therapy.common.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="icd")
public class Icd extends Model {

    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name="subcategory_id")
    private IcdSubCategory subCategory;
}
