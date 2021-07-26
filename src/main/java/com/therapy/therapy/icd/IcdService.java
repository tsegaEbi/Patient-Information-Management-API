package com.therapy.therapy.icd;

import com.therapy.therapy.common.CRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IcdService extends CRUDService<Icd>{

    List<Icd> getByCategory(ICD_CATEGORY category);

    List<Icd>getBySubCategory(ICD_SUB_CATEGORY sub_category);


    Page<Icd> geByKey(String key, Pageable pageable);

    Icd getByCode(String code);



}