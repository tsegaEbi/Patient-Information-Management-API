package com.therapy.therapy.appointment;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.patient.Patient;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="appointment")
@Setter
@Getter
public class Appointment extends Model {
    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    private APPOINTMENT_PURPOSE purpose;

    private String note;

    private Long examinerId;

    private Long departmentId;

    private Long referenceId;

    private Boolean active;

    private Date date;
}
