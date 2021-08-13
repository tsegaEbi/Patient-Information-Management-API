package com.therapy.therapy.examination.HelathProblem;

import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.icdCode.IcdDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class HealthProblemDTO {

    private Long id;
    private IcdDTO icd;
    private ExaminationDTO examination;

    private String examinerNote;
      private HEALTH_PROBLEM_STATUS status;

    private Date startedDate;
    private Date solvedDate;
    private Date dateCreated;
    private String solvedNote;



    public static HealthProblemDTO toDTO(HealthProblem problem){
        HealthProblemDTO dto = new HealthProblemDTO();
        dto.setExamination(ExaminationDTO.toDTO(problem.getExamination(),null));
        dto.setExaminerNote(problem.getExaminerNote());
        dto.setIcd(IcdDTO.toDTO(problem.getIcd()));
        dto.setId(problem.getId());
        dto.setSolvedDate(problem.getSolvedDate());
        dto.setStartedDate(problem.getStartedDate());
        dto.setDateCreated(problem.getDateCreated());
        dto.setStatus(problem.getStatus());
        dto.setSolvedNote(problem.getSolvedNote());

        return dto;
    }
}
