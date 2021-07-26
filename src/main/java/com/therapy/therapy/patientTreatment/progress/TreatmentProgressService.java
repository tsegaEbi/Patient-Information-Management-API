package com.therapy.therapy.patientTreatment.progress;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.patientTreatment.PatientTreatment;

import java.util.List;

public interface TreatmentProgressService extends CRUDService<TreatmentProgress> {

    List<TreatmentProgress> getByExamination(Long examinationId);

    List<TreatmentProgress> getByPatientTreatment(PatientTreatment patientTreatment);

    ActionResponse<TreatmentProgress> create(TreatmentProgress progress);
}
