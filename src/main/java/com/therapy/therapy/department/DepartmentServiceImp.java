package com.therapy.therapy.department;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentRepository repository;

    public DepartmentServiceImp(DepartmentRepository repository){
        this.repository =repository;
    }

    @Override
    public List<Department> getAll(){
        return repository.findAll();
    }
    @Override
    public Department get(Long id) {
        if(id==null)
            return null;

        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Department> list(Pageable pageable) {
        return null;
    }

    @Override
    public Department add(Department department) {
        return repository.save(department);
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public void delete(Department department) {

    }
}
