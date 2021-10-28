package com.therapy.therapy.icdCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IcdSubCategoryRepository extends JpaRepository<IcdSubCategory,Long> {

    List<IcdSubCategory>  findByOrderByNameAsc();

    List<IcdSubCategory>  findByOrderByCodeAsc();

    List<IcdSubCategory> findByCategory(ICD_CATEGORY category);
}
