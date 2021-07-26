package com.therapy.therapy.icd;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class IcdDTO {

    private Long id;

    private String code;

    private String name;

    private ICD_SUB_CATEGORY subCategory;

    private ICD_CATEGORY category;



    public static IcdDTO toDTO(Icd icd){
        IcdDTO dto =new IcdDTO();
        dto.setCategory(icd.getCategory());
        dto.setSubCategory(icd.getSubCategory());
        dto.setId(icd.getId());

        return dto;

    }

}
