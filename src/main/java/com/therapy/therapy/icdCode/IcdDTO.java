package com.therapy.therapy.icdCode;

import lombok.Getter;
import lombok.Setter;

@Getter
 @Setter

public class IcdDTO {
    private Long id;

    private String code;

    private String name;

    private Long subCategoryId;
    private String subCategory;

    private ICD_CATEGORY category;



    public static IcdDTO toDTO(Icd icd){
        IcdDTO dto =new IcdDTO();
        dto.setSubCategory(icd.getSubCategory().getName());
        dto.setSubCategory(icd.getSubCategory().getCode());
        dto.setSubCategoryId(icd.getSubCategory().getId());
        dto.setCategory(icd.getSubCategory().getCategory());
        dto.setId(icd.getId());
        dto.setCode(icd.getCode());
        dto.setName(icd.getName());

        return dto;

    }
}
