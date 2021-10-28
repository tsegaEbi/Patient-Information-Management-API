package com.therapy.therapy.icdCode;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class IcdServiceImp implements IcdService{
    private final IcdRepository repository;

    public IcdServiceImp(IcdRepository repository){

        this.repository=repository;

    }

    @Override
    public Page<Icd> getByCategory(ICD_CATEGORY category, Pageable pageable) {
        return repository.findByCategory(category,pageable);
    }


    @Override
    public List<Icd> getBySubCategory(IcdSubCategory sub_category) {
        return repository.findBySubCategory(sub_category);
    }

    @Override
    public Page<Icd> getByKey(String key, Pageable pageable) {
        return repository.searchByKey(key,pageable);
    }

    @Override
    public Icd getByCode(String code) {
        return repository.findByCode(code).stream().findFirst().orElse(null);
    }

    @Override
    public Icd get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Icd add(Icd icd) throws Exception {
        return repository.save(icd);
    }

    @Override
    public Icd update(Icd icd) throws Exception {
        return null;
    }

    @Override
    public void delete(Icd icd) throws Exception {

    }
}
