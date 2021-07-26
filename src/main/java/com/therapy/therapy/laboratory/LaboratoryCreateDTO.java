package com.therapy.therapy.laboratory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LaboratoryCreateDTO {
    private String name;
    private String description;
    private LABORATORY_CATEGORY category;
    private Double price;

    public Laboratory toLab(LaboratoryCreateDTO dto){
        Laboratory lab = new Laboratory();

        lab.setDescription(dto.getDescription());
        lab.setName(dto.getName());
        lab.setCategory(dto.getCategory());
        if(dto.getPrice()==null){
            lab.setPrice(0.0);
        }
        else
        {
            lab.setPrice(dto.getPrice());
        }
        return lab;
    }
}
