package com.therapy.therapy.icdCode;


import com.therapy.therapy.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcdRepository extends JpaRepository<Icd,Long> {

    @Query("FROM Icd i WHERE i.subCategory.category=:category")
   Page<Icd> findByCategory(ICD_CATEGORY category,Pageable pageable);

    List<Icd>findBySubCategory(IcdSubCategory sub_category);

    List<Icd> findByCode(String code);

    @Query("FROM Icd p where lower(p.code) like lower(concat('%',:key,'%'))   or lower(p.name) like lower(concat('%',:key,'%'))")
    Page<Icd> searchByKey(String key, Pageable pageable);
}
