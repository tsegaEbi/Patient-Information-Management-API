package com.therapy.therapy.icd;

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

@Table(name="icd")
public class Icd extends Model {

    private String code;
    private String name;

    @Enumerated(EnumType.STRING)
    private ICD_CATEGORY category;

    @Enumerated(EnumType.STRING)
    private ICD_SUB_CATEGORY subCategory;
}
