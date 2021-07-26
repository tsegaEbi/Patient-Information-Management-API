package com.therapy.therapy.examination;

import com.therapy.therapy.common.DISEASE;
import com.therapy.therapy.common.Model;
import com.therapy.therapy.diagnosis.DIAGNOSIS_PROCESS;
import com.therapy.therapy.diagnosis.DIAGNOSTIC_CATEGORY;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="examination")
public class Examination extends Model {

    @OneToOne
    @JoinColumn(name="patient_visit_id",unique = true)
    private PatientVisit patientVisit;

    @ManyToOne
    @JoinColumn(name="examiner_id")
    private Staff examiner;

     private String chiefComplaint;
     private String historyOfPresentIllness;
     private String physicalExamination;
     private String assessmentAndPlan;
     private String ordersAndPrescriptions;
     private String progressNote;
    /*private byte[] statement;
    @Column(length = 16000000) // This should generate a medium blob
    @Basic(fetch = FetchType.LAZY) // I've read this is default, but anyway...
    public byte[] getStatement() {
        return statement;
    }*/
    private String findings;

    @Enumerated(EnumType.STRING)
    private DIAGNOSIS_PROCESS diagnosisProcess;

    @Enumerated(EnumType.STRING)
    private DIAGNOSTIC_CATEGORY diagnosticCategory;

    @Enumerated(EnumType.STRING)
    private DISEASE disease;

    private String diseaseStatement;

    private Boolean active;
}
