package com.therapy.therapy.appointment;

import com.therapy.therapy.department.Department;
import com.therapy.therapy.department.DepartmentDTO;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.examination.labOrder.LabOrder;
import com.therapy.therapy.examination.labOrder.LabOrderDTO;
import com.therapy.therapy.patient.PatientDTO;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.patient.checkup.PatientVisitDTO;
import com.therapy.therapy.patientTreatment.PatientTreatment;
import com.therapy.therapy.patientTreatment.PatientTreatmentDTO;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AppointmentDTO {
    private Long id;
    private Date dateCreated;
    private PatientDTO patient;
    private StaffDTO examiner;
    private DepartmentDTO department;
    private Long referenceId;
    private String note;
    private Date date;
    private Boolean active;
    private APPOINTMENT_PURPOSE purpose;
    //
    private ExaminationDTO examination;
    private PatientTreatmentDTO treatment;
    private PatientVisitDTO visit;
    private LabOrderDTO labOrder;

    //medication......

    public static AppointmentDTO toDTO(Appointment appointment, Staff examiner,
                                       Department department, PatientVisit visit, Examination examination,
                                       PatientTreatment treatment, LabOrder labOrder){
        AppointmentDTO dto = new AppointmentDTO();

        dto.setId(appointment.getId());
        dto.setDateCreated(appointment.getDateCreated());
        dto.setNote(appointment.getNote());
        dto.setActive(appointment.getActive());
        dto.setDate(appointment.getDate());

        dto.setPurpose(appointment.getPurpose());

        dto.setPatient(PatientDTO.patientDTO(appointment.getPatient()));
        if(appointment.getReferenceId()!=null){
            dto.setReferenceId(appointment.getReferenceId());

            if(examination!=null){
                dto.setExamination(ExaminationDTO.toDTO(examination,null));
            }
            if(treatment!=null){
                dto.setTreatment(PatientTreatmentDTO.toDTO(treatment));
            }
        }

        if(appointment.getExaminerId()!=null){
            dto.setExaminer(StaffDTO.toDTO(examiner));
        }

        if(department!=null){
            dto.setDepartment(DepartmentDTO.toDTO(department));
        }

        if(visit!=null){
            dto.setVisit(PatientVisitDTO.toPatientVisitDTO(visit,null,null,null));
        }

        if(labOrder!=null){
            dto.setLabOrder(LabOrderDTO.toDTO(labOrder,null));
        }
        return dto;
    }
}
