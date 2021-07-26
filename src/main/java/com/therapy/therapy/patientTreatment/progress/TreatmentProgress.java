package com.therapy.therapy.patientTreatment.progress;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.patientTreatment.PatientTreatment;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="treatment_progress_note")
public class TreatmentProgress extends Model {

    @ManyToOne
    @JoinColumn(name="patient_treatment_id")
    private PatientTreatment patientTreatment;

    @ManyToOne
    @JoinColumn(name="reporter_id")
    private Staff reporter;

    private String note;

    private TREATMENT_PROGRESS_POINT point;
}
