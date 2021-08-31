package com.therapy.therapy.finance.item;

import com.therapy.therapy.finance.itemCategory.ItemCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ItemCreateDTO {
    private Long categoryId;
    private String name;
    private String itemCode;   //cash register
    private String description;
    private String subCategory;

    private Double price;

    public static Item toItem(ItemCreateDTO dto, ItemCategory category){
        Item item= new Item();
        item.setCategory(category);
        item.setDescription(dto.getDescription());
        item.setItemCode(dto.getItemCode());
        item.setName(dto.getName());
        item.setPrice(dto.getPrice());
        item.setSubCategory(dto.getSubCategory());
        return item;
    }
}
