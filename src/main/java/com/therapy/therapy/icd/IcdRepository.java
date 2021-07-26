package com.therapy.therapy.icd;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IcdRepository extends JpaRepository<Icd,Long> {

    List<Icd> findByCategory(ICD_CATEGORY category);

    List<Icd>findBySubCategory(ICD_SUB_CATEGORY sub_category);

  //  @Query("FROM Icd where code like=:key")
   // Page<Icd> findByKey(String key, Pageable pageable);

    Icd findByCode(String code);

}
