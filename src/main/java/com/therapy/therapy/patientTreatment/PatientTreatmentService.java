package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.treatment.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientTreatmentService extends CRUDService<PatientTreatment> {

    ActionResponse<PatientTreatment> create(PatientTreatment treatment);

    List<PatientTreatment> getByExaminationAndTreatment(Examination exam, Treatment treatment);

    List<PatientTreatment> getByExamination(Examination examination);


    Page<PatientTreatment> searchByQuery(PatientTreatmentSearchQuery query, Pageable pageable);

    PatientTreatment updateStatus(Long id );

    PatientTreatment updateStart(Long id );

    PatientTreatment updateResult(Long id, PATIENT_TREATMENT_RESULT result);

    Page<PatientTreatment> getAllByExaminerAndStatus(Long staffId,PATIENT_TREATMENT_RESULT result);

    ActionResponse<PatientTreatment> createMultiple(List<PatientTreatment> treatments);
}
