package com.therapy.therapy.examination.patientHealthProblem;

import com.therapy.therapy.icd.ICD_CATEGORY;
import com.therapy.therapy.icd.ICD_SUB_CATEGORY;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class HealthProblemSearchQuery {

    private int pageNumber;

    private HEALTH_PROBLEM_STATUS status;

    private Long patientId;

    private Long icdId;

    private Long examinerId;

    private ICD_SUB_CATEGORY subCategory;

    private ICD_CATEGORY category;

    private String icdCode;
}
