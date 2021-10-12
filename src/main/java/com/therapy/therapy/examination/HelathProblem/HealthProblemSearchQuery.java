package com.therapy.therapy.examination.HelathProblem;

import com.therapy.therapy.icdCode.ICD_CATEGORY;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HealthProblemSearchQuery {
    private int pageNumber;

    private Long icdId;

    private ICD_CATEGORY category;

     private HEALTH_PROBLEM_STATUS status;

    private Long icdSubCategory;

}
