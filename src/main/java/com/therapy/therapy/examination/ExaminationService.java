package com.therapy.therapy.examination;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.laboratory.Laboratory;
import com.therapy.therapy.patient.checkup.PatientVisit;

import java.util.List;

public interface ExaminationService extends CRUDService<Examination> {


     ActionResponse<Examination> create(Examination examination,List<Laboratory> labOrders);

     List<Examination> getAllByPatient(Long patientId);

     Examination getByPatientVisit(PatientVisit visit);

     void activate(Long id);
}
