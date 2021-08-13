package com.therapy.therapy.icdCode;

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
@Table(name="icd_subcategory")
public class IcdSubCategory extends Model {

    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private ICD_CATEGORY category;
}
