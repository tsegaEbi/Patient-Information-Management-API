package com.therapy.therapy.appointment;

import com.therapy.therapy.patient.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AppointmentCreateDTO {

    private Long patientId;
    private Long examinerId;
    private Long departmentId;
    private Long referenceId;
    private String note;
    private Date date;
    private Boolean active;
    private APPOINTMENT_PURPOSE purpose;


    public static Appointment toAppointment(AppointmentCreateDTO dto,Patient patient){

        Appointment appointment = new Appointment();
        appointment.setActive(dto.getActive());
        appointment.setDate(dto.getDate());
        appointment.setExaminerId(dto.getExaminerId());
        appointment.setDepartmentId(dto.getDepartmentId());
        appointment.setPatient(patient);
        appointment.setReferenceId(dto.getReferenceId());
        appointment.setNote(dto.getNote());
        appointment.setPurpose(dto.getPurpose());

        return appointment;
    }

}
