package com.therapy.therapy.appointment;

import com.therapy.therapy.department.DepartmentDTO;
import com.therapy.therapy.department.DepartmentService;
import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.examination.ExaminationService;
import com.therapy.therapy.examination.labOrder.LabOrderDTO;
import com.therapy.therapy.examination.labOrder.LabOrderService;
import com.therapy.therapy.patient.PatientDTO;
import com.therapy.therapy.patient.PatientService;
import com.therapy.therapy.patient.checkup.PatientVisitDTO;
import com.therapy.therapy.patient.checkup.PatientVisitService;
import com.therapy.therapy.patientTreatment.PatientTreatmentDTO;
import com.therapy.therapy.patientTreatment.PatientTreatmentService;
import com.therapy.therapy.staff.StaffDTO;
import com.therapy.therapy.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentHelper implements AppointmentHelperService{
    @Autowired
    private PatientService patientService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private ExaminationService examinationService;
    @Autowired
    private PatientTreatmentService treatmentService;
    @Autowired
    private PatientVisitService visitService;
    @Autowired
    private LabOrderService labOrderService;
    @Autowired
    private DepartmentService departmentService;

    public AppointmentDTO appointmentDTO(Appointment appointment){
        AppointmentDTO dto= new AppointmentDTO();

        dto.setId(appointment.getId());
        dto.setDate(appointment.getDate());
        dto.setReferenceId(appointment.getReferenceId());
        dto.setDateCreated(appointment.getDateCreated());
        dto.setPurpose(appointment.getPurpose());
        dto.setActive(appointment.getActive());
        dto.setNote(appointment.getNote());
        dto.setPatient(PatientDTO.patientDTO(appointment.getPatient()));

        //

        if(appointment.getReferenceId()!=null){
            dto.setReferenceId(appointment.getReferenceId());
            if(appointment.getPurpose().equals(APPOINTMENT_PURPOSE.CHECKUP)) {
             dto.setVisit(PatientVisitDTO.toPatientVisitDTO(visitService.get(appointment.getReferenceId()),null,null,null));
            }
           else if(appointment.getPurpose().equals(APPOINTMENT_PURPOSE.EXAMINATION)) {
                dto.setExamination(ExaminationDTO.toDTO(examinationService.get(appointment.getReferenceId()),null));
            }

           else if(appointment.getPurpose().equals(APPOINTMENT_PURPOSE.TREATMENT)){
               dto.setTreatment(PatientTreatmentDTO.toDTO(treatmentService.get(appointment.getReferenceId())));
            }
            else if(appointment.getPurpose().equals(APPOINTMENT_PURPOSE.LABORATORY)){
                dto.setLabOrder(LabOrderDTO.toDTO(labOrderService.get(appointment.getReferenceId()),null));
            }
        }


        if(appointment.getExaminerId()!=null){
            dto.setExaminer(StaffDTO.toDTO(staffService.get(appointment.getExaminerId())));
        }
        if(appointment.getDepartmentId()!=null){
            dto.setDepartment(DepartmentDTO.toDTO(departmentService.get(appointment.getDepartmentId())));
        }
        return dto;
    }


}
