package com.therapy.therapy.finance.itemCategory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ItemCategoryDTO {
    private Long id;
    private String name;

    public static ItemCategoryDTO toDTO(ItemCategory item){
        ItemCategoryDTO dto = new ItemCategoryDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        return dto;
    }
    public static ItemCategory toCategoryForCreate(ItemCategoryDTO dto){
        ItemCategory item= new ItemCategory();
       item.setName(dto.getName().trim().toUpperCase());
       return item;
    }
}
