package com.therapy.therapy.treatment;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface TreatmentService extends CRUDService<Treatment> {

    List<Treatment> listAll();
}
