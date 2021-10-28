package com.therapy.therapy.finance.item;

import com.therapy.therapy.finance.itemCategory.ItemCategory;
import com.therapy.therapy.finance.itemCategory.ItemCategoryDTO;
import com.therapy.therapy.finance.itemCategory.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @PostMapping("/add")
    public ItemDTO create(@RequestBody ItemCreateDTO dto) throws Exception {
        if(dto!=null && dto.getName()!=null){
            //return ItemDTO.toDTO(itemService.add(ItemDTO.toDTO(dto)));
        }
        return null;
    }
    @GetMapping("/list")
    public List<ItemCategoryDTO> list( ) throws Exception {
        List<ItemCategory>items =itemCategoryService.getAll();

        if(items!=null && items.size()>0)
            return  items.stream().map(i->ItemCategoryDTO.toDTO(i)).collect(Collectors.toList());
        return null;
    }
}
