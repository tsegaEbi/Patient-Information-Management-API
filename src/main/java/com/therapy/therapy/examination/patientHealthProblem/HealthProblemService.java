package com.therapy.therapy.examination.patientHealthProblem;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface HealthProblemService extends CRUDService<HealthProblem> {

    List<HealthProblem> getByExamination(Long examinationId);
}
