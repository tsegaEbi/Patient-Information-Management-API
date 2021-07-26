package com.therapy.therapy.patient.checkup;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientVisitService extends CRUDService<PatientVisit> {

        ActionResponse<PatientVisit> create(PatientVisit visit);

        List<PatientVisit> getByPatientId(Long patientId);

        Page<PatientVisit> getByDepartmentId(Long departmentId, Boolean examined,Pageable pageable);

        Page<PatientVisit> getByExaminerId(Long examinerId, Boolean examined, Pageable pageable);

        Page<PatientVisit> getByQuery(PatientVisitSearchQuery query, Pageable pageable);

        Page<PatientVisit> getByExamined(Boolean examined,Pageable pageable);

        PatientVisit assignDepartment(Long id,Long departmentId);

        PatientVisit assignExaminer(Long id,Long examinerId);

        Page<PatientVisit> getByExaminerAndExamined(Long examinerId,Boolean examined);

        void Examined(PatientVisit visit);

}
