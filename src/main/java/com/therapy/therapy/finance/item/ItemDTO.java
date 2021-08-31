package com.therapy.therapy.finance.item;

import com.therapy.therapy.finance.itemCategory.ItemCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ItemDTO {
    private Long id;
    private String name;
    private String itemCode;   //cash register
    private String description;
    private String category;
    private Long categoryId;

    private String subCategory;

    private Double price;

    public static ItemDTO toDTO(Item item){
        ItemDTO dto= new ItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setCategory(item.getCategory().getName());
        dto.setCategoryId(item.getCategory().getId());
        dto.setSubCategory(item.getSubCategory());
        dto.setPrice(item.getPrice());
        dto.setDescription(item.getDescription());
        dto.setItemCode(item.getItemCode());

        return dto;
    }
}
