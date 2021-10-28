package com.therapy.therapy.finance.itemCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/itemCategory")
public class ItemCategoryController {

   @Autowired
    private ItemCategoryService itemCategoryService;

    @PostMapping("/add")
    public ItemCategoryDTO create(@RequestBody ItemCategoryDTO dto) throws Exception {
        if(dto!=null && dto.getName()!=null){
            return ItemCategoryDTO.toDTO(itemCategoryService.add(ItemCategoryDTO.toCategoryForCreate(dto)));
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
