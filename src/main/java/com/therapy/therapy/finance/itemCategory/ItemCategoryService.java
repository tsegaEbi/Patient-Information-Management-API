package com.therapy.therapy.finance.itemCategory;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface ItemCategoryService extends CRUDService<ItemCategory> {

    List<ItemCategory> findByName(String name);
    List<ItemCategory> getAll( );
}
