package com.therapy.therapy.icdCode;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class IcdCreateDTO {
    private String code;
    private  Long subCategoryId;

    private String name;

    public static Icd toIcd(IcdCreateDTO dto,IcdSubCategory subCategory){
        if(dto==null || subCategory==null) return null;
        Icd icd =new Icd();

        icd.setCode(dto.getCode());
        icd.setName(dto.getName());
        icd.setSubCategory(subCategory);
        return icd;
    }
}
