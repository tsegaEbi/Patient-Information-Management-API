package com.therapy.therapy.icd;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IcdCreateDTO {

    private ICD_CATEGORY category;

    private ICD_SUB_CATEGORY subCategory;

    private String name;

}
