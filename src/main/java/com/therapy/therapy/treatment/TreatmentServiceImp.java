package com.therapy.therapy.treatment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentServiceImp implements TreatmentService {
    private final TreatmentRepository repository;

    public TreatmentServiceImp(TreatmentRepository repository){
        this.repository =repository;
    }
    @Override
    public Treatment get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Treatment> list(Pageable pageable) {
        return null;
    }

    @Override
    public Treatment add(Treatment treatment) throws Exception {
        if(treatment!=null && treatment.getName()!=null && treatment.getCategory()!=null)
        {
            Treatment getT = repository.findByName(treatment.getName().toUpperCase());
            if(getT!=null && getT.getCategory().equals(treatment.getCategory()))
            {
                return null;
            }

           return repository.save(treatment);
        }
        return null;
    }

    @Override
    public Treatment update(Treatment treatment) throws Exception {
        return null;
    }

    @Override
    public void delete(Treatment treatment) throws Exception {
            repository.deleteById(treatment.getId());
    }

    @Override
    public List<Treatment> listAll() {
        return repository.findAll();
    }
}
