package com.therapy.therapy.icdCode;
import com.therapy.therapy.common.CRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IcdService extends CRUDService<Icd> {
    Page<Icd> getByCategory(ICD_CATEGORY category, Pageable pageable);

    List<Icd>getBySubCategory(IcdSubCategory subCategory);

    Page<Icd> getByKey(String key, Pageable pageable);

    Icd getByCode(String code);

}
