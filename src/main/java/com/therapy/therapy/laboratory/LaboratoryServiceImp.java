package com.therapy.therapy.laboratory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LaboratoryServiceImp implements LaboratoryService {
    private final LaboratoryRepository repository;

    public LaboratoryServiceImp(LaboratoryRepository repository){
        this.repository =repository;
    }
    @Override
    public Laboratory get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Laboratory> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Laboratory add(Laboratory laboratory) throws Exception {
        return null;
    }
     private boolean validateNew(Laboratory lab){
        if(repository.findByName(lab.getName().toUpperCase())==null){
            return true;
        }

        return false;
     }
    @Override
    public Laboratory update(Laboratory laboratory) {
        return null;
    }

    @Override
    public void delete(Laboratory laboratory) throws Exception {

    }

    @Override
    public List<Laboratory> create(String name,String description, LABORATORY_CATEGORY category) {
        if(repository.findByName(name)!=null){
            Laboratory lab = new Laboratory();
            lab.setName(name.toUpperCase());
            lab.setDescription(description);
            lab.setCategory(category);

            repository.save(lab);
         }
        return repository.findAll();
    }
    @Override
    public List<Laboratory> listAll( ) {

        return repository.findAll();
    }
}
