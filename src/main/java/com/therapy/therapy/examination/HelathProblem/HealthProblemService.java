package com.therapy.therapy.examination.HelathProblem;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.icdCode.ICD_CATEGORY;
import com.therapy.therapy.icdCode.Icd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HealthProblemService extends CRUDService<HealthProblem> {
    List<HealthProblem> getByExaminationAndIcd(Examination examination, Icd icd);

    ActionResponse<HealthProblem> create(HealthProblem problem);


    List<HealthProblem> getByExamination(Examination examination);

    List<HealthProblem>getByPatient(Long patientId);

    Page<HealthProblem> searchByQuery(HealthProblemSearchQuery query, Pageable pageable);

    Page<HealthProblem> getByIcdCode(Long icdId, HEALTH_PROBLEM_STATUS status, Pageable pageable);

    Page<HealthProblem> getByIcdSubCategory(Long icdSubCatId, HEALTH_PROBLEM_STATUS status, Pageable pageable);

    Page<HealthProblem> getByIcdCategory(ICD_CATEGORY category, HEALTH_PROBLEM_STATUS status, Pageable pageable);
}
