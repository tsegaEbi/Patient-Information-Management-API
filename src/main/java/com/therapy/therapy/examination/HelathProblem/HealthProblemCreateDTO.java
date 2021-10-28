package com.therapy.therapy.examination.HelathProblem;

import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.icdCode.Icd;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Setter
@Getter

public class HealthProblemCreateDTO {

    private Long icdId;

    private Long examinationId;

    private String examinerNote;

    @Enumerated(EnumType.STRING)
    private HEALTH_PROBLEM_STATUS status;

    private Date startedDate;
    private Date solvedDate;

    public static HealthProblem toHealthProblem(HealthProblemCreateDTO dto, Examination examination, Icd icd){

           if(examination==null || icd==null){
               return null;
           }

           HealthProblem problem = new HealthProblem();
           problem.setExamination(examination);
           problem.setExaminerNote(dto.getExaminerNote());
           problem.setIcd(icd);
           problem.setStartedDate(dto.getStartedDate());
           problem.setStatus(HEALTH_PROBLEM_STATUS.NOT_SOLVED);
           return problem;
    }
}
