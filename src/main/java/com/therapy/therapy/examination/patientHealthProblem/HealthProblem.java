package com.therapy.therapy.examination.patientHealthProblem;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.icd.Icd;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Setter
@Getter
@Table(name="health_problem")

public class HealthProblem extends Model {
    @ManyToOne
    @JoinColumn(name = "icd_id")
    private Icd icd;

    @ManyToOne
    @JoinColumn(name = "examination_id")
    private Examination examination;

    private String examinerNote;
    private String solvedNote;

    @Enumerated(EnumType.STRING)
    private HEALTH_PROBLEM_STATUS status;

    private Date startedDate;
    private Date solvedDate;
}

