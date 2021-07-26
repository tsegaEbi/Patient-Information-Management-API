package com.therapy.therapy.examination.patientHealthProblem;

import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.icd.IcdDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class HealthProblemDTO {

    private Long id;

    private Long icdId;

    private IcdDTO icd;

    private ExaminationDTO examination;

    private String examinerNote;

    private HEALTH_PROBLEM_STATUS status;

    private Date startedDate;

    private Date solvedDate;

    private Date dateCreated;

    private String solvedNote;

    public static HealthProblemDTO toDTO(HealthProblem healthProblem){
        HealthProblemDTO dto = new HealthProblemDTO();

        dto.setId(healthProblem.getId());
        dto.setDateCreated(healthProblem.getDateCreated());
        dto.setSolvedDate(healthProblem.getSolvedDate());
        dto.setStartedDate(healthProblem.getStartedDate());

        dto.setExaminerNote(healthProblem.getExaminerNote());
        dto.setIcd(IcdDTO.toDTO(healthProblem.getIcd()));

        dto.setSolvedDate(healthProblem.getSolvedDate());

        dto.setExamination(ExaminationDTO.toDTO(healthProblem.getExamination(),null));

        return dto;

    }

}
