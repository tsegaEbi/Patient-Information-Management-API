package com.therapy.therapy.examination.patientHealthProblem;

import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.icd.Icd;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class HealthProblemCreateDTO {

    private Long examinationId;

    private Date startedDate;

    private Long icdId;

    private String examinerNote;

    private String solvedNote;

   public static  HealthProblem toHealthProblem(HealthProblemCreateDTO dto, Icd icd, Staff examiner, Examination examination){
       if(examination==null || examiner==null || dto==null){
           return null;
       }
       HealthProblem problem = new HealthProblem();

       problem.setExamination(examination);
       problem.setIcd(icd);
       problem.setStartedDate(dto.getStartedDate());
       problem.setExaminerNote(dto.getExaminerNote());

       return problem;
   }
}
