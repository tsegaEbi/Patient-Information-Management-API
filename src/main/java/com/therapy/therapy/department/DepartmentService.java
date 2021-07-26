package com.therapy.therapy.department;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface DepartmentService extends CRUDService<Department> {

    List<Department> getAll();
}
