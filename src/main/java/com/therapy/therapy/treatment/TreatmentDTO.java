package com.therapy.therapy.treatment;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreatmentDTO {
    private Long id;
    private String name;
    private String description;
    private TREATMENT_CATEGORY category;

    public static Treatment toTreatment(TreatmentDTO treatment){
        Treatment dto = new Treatment();
        dto.setDescription(treatment.getDescription());
        dto.setName(treatment.getName().toUpperCase());
        dto.setCategory(treatment.getCategory());
        dto.setId(treatment.getId());
        return dto;
    }
    public static TreatmentDTO toDTO(Treatment treatment){
        TreatmentDTO dto = new TreatmentDTO();
        dto.setDescription(treatment.getDescription());
        dto.setName(treatment.getName());
        dto.setCategory(treatment.getCategory());
        dto.setId(treatment.getId());
        return dto;
    }

}
