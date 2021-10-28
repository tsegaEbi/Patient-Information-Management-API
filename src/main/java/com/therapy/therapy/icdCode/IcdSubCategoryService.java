package com.therapy.therapy.icdCode;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface IcdSubCategoryService extends CRUDService<IcdSubCategory> {

    List<IcdSubCategory> getAllByName();
    List<IcdSubCategory>getAllByCode();

    List<IcdSubCategory>getByCategory(ICD_CATEGORY category);
}
