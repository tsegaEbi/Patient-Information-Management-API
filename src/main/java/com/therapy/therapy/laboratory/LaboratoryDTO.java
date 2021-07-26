package com.therapy.therapy.laboratory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class LaboratoryDTO {
    private Long id;

    private String name;
    private String description;
    private LABORATORY_CATEGORY category;

    public static LaboratoryDTO toDto(Laboratory lab){
        LaboratoryDTO dto = new LaboratoryDTO();
        dto.setDescription(lab.getDescription());
        dto.setName(lab.getName());
        dto.setId(lab.getId());
        dto.setCategory(lab.getCategory());
        return dto;
    }
}
