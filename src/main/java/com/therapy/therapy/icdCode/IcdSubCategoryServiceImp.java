package com.therapy.therapy.icdCode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IcdSubCategoryServiceImp implements IcdSubCategoryService{

    private final IcdSubCategoryRepository repository;
    public IcdSubCategoryServiceImp(IcdSubCategoryRepository repository){
        this.repository =repository;
    }
    @Override
    public IcdSubCategory get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<IcdSubCategory> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public IcdSubCategory add(IcdSubCategory subCategory) throws Exception {
        return null;
    }

    @Override
    public IcdSubCategory update(IcdSubCategory subCategory) throws Exception {
        return null;
    }

    @Override
    public void delete(IcdSubCategory subCategory) throws Exception {

    }

    @Override
    public List<IcdSubCategory> getAllByName() {
        return repository.findByOrderByNameAsc();
    }

    @Override
    public List<IcdSubCategory> getAllByCode() {
        return repository.findByOrderByCodeAsc();
    }

    @Override
    public List<IcdSubCategory> getByCategory(ICD_CATEGORY category) {
        return repository.findByCategory(category);
    }
}
