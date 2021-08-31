package com.therapy.therapy.finance.itemCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemCategoryServiceImp implements ItemCategoryService {
    private final ItemCategoryRepository repository;

    public ItemCategoryServiceImp(ItemCategoryRepository repository){
        this.repository =repository;
    }
    @Override
    public List<ItemCategory> findByName(String name) {
        return repository.findByName(name.trim().toUpperCase());
    }

    @Override
    public List<ItemCategory> getAll() {
        return repository.findAll();
    }

    @Override
    public ItemCategory get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<ItemCategory> list(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public ItemCategory add(ItemCategory itemCategory) throws Exception {
        return  repository.save(itemCategory);
    }

    @Override
    public ItemCategory update(ItemCategory itemCategory) throws Exception {
        return null;
    }

    @Override
    public void delete(ItemCategory itemCategory) throws Exception {
            repository.delete(itemCategory);
    }
}
