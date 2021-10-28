package com.therapy.therapy.finance.itemCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCategoryRepository extends JpaRepository<ItemCategory,Long> {
    List<ItemCategory> findByName(String name);

    List<ItemCategory> findAll( );
}
