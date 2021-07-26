package com.therapy.therapy.icd;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IcdServiceImp implements IcdService {

    private final IcdRepository repository;

    public IcdServiceImp(IcdRepository repository){

        this.repository=repository;

    }

    @Override
    public List<Icd> getByCategory(ICD_CATEGORY category) {
        return null;
    }

    @Override
    public List<Icd> getBySubCategory(ICD_SUB_CATEGORY sub_category) {
        return null;
    }

    @Override
    public Page<Icd> geByKey(String key, Pageable pageable) {
        return null;
    }

    @Override
    public Icd getByCode(String code) {
        return null;
    }

    @Override
    public Icd get(Long id) {
        return null;
    }

    @Override
    public Page list(Pageable pageable) {
        return null;
    }

    @Override
    public Icd add(Icd icd) throws Exception {
        return null;
    }

    @Override
    public Icd update(Icd icd) throws Exception {
        return null;
    }

    @Override
    public void delete(Icd icd) throws Exception {

    }
}
