package com.therapy.therapy.laboratory;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface LaboratoryService extends CRUDService<Laboratory> {

    List<Laboratory> create(String name,String description, LABORATORY_CATEGORY category);
    List<Laboratory> listAll();
}

