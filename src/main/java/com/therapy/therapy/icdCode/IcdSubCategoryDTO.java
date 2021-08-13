package com.therapy.therapy.icdCode;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IcdSubCategoryDTO  {

    private Long id;

    private String code;

    private String name;

    private ICD_CATEGORY category;

    public static IcdSubCategoryDTO toDTO(IcdSubCategory sub){
        IcdSubCategoryDTO dto = new IcdSubCategoryDTO();
        dto.setCategory(sub.getCategory());
        dto.setCode(sub.getCode());
        dto.setId(sub.getId());
        dto.setName(sub.getName());

        return dto;
    }
}
