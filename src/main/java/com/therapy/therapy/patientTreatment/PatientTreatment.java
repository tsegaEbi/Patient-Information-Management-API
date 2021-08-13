package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.treatment.Treatment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="patient_treatment")
public class PatientTreatment extends Model {


    @ManyToOne
    @JoinColumn(name="examination_id")
    private Examination examination;

    @ManyToOne
    @JoinColumn(name="treatment_id")
    private Treatment treatment;

    @Enumerated(EnumType.STRING)
    private TREATMENT_TYPE type;  // addmission or opd

    @Enumerated(EnumType.STRING)
    private PATIENT_TREATMENT_RESULT result;

    private String goals;// separated by comma

    private Boolean started;

    private Date completedDate;

    private Date startedDate;

    private String note;

    private Boolean active;
}
