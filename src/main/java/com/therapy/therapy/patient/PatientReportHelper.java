package com.therapy.therapy.patient;

import com.therapy.therapy.appointment.AppointmentService;
import com.therapy.therapy.department.DepartmentService;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.examination.ExaminationService;
import com.therapy.therapy.examination.labOrder.LabOrder;
import com.therapy.therapy.examination.labOrder.LabOrderDTO;
import com.therapy.therapy.examination.labOrder.LabOrderService;
import com.therapy.therapy.laboratory.LaboratoryService;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.patient.checkup.PatientVisitDTO;
import com.therapy.therapy.patient.checkup.PatientVisitService;
import com.therapy.therapy.patientTreatment.PatientTreatment;
import com.therapy.therapy.patientTreatment.PatientTreatmentDTO;
import com.therapy.therapy.patientTreatment.PatientTreatmentService;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgress;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgressDTO;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgressService;
import com.therapy.therapy.report.patientReport.PatientReportService;
import com.therapy.therapy.staff.StaffService;
import com.therapy.therapy.treatment.TreatmentDTO;
import com.therapy.therapy.treatment.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientReportHelper implements PatientReportHelperService {



    @Autowired
    private PatientVisitService patientVIsitService;
    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private LabOrderService labOrderService;

    @Autowired
    private PatientTreatmentService patientTreatmentService;

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private TreatmentProgressService progressService;

    @Override
    public PatientDetailDTO getDetail(Patient patient){
        PatientDetailDTO patientDetail = new PatientDetailDTO();
        if(patient ==null) return null;
        patientDetail.setPatient(PatientDTO.patientDTO(patient));
        List<Examination> exams =examinationService.getAllByPatient(patient.getId());
        List<ExaminationDTO> examDTOs =new ArrayList<>();

        if(exams!=null){
            for(Examination exam: exams){
                List<LabOrderDTO> labDTOs = new ArrayList<>();
                List<PatientTreatmentDTO>treatmentsDTOS =new ArrayList<>();

                List<LabOrder>labOrders =labOrderService.getByExamination(exam);
                List<PatientTreatment>treatments =patientTreatmentService.getByExamination(exam);

                if(labOrders!=null){
                    for(LabOrder order:labOrders){
                        if(order.getTechnician()!=null && staffService.get(order.getTechnician())!=null){
                            labDTOs.add(LabOrderDTO.toDetailDTO(order,staffService.get(order.getTechnician())));
                         } else{
                            labDTOs.add(LabOrderDTO.toDetailDTO(order,null));
                        }
                    }
               }

                if(treatments!=null){
                    for(PatientTreatment treatment:treatments){
                        List<TreatmentProgress> progresses =progressService.getByPatientTreatment(treatment);
                        if(progresses==null) {
                            treatmentsDTOS.add(PatientTreatmentDTO.toDetailReport(treatment,null));
                        } else{
                            treatmentsDTOS.add(PatientTreatmentDTO.toDetailReport(treatment,
                                    progresses.stream().map(p->TreatmentProgressDTO.toDTO(p)).collect(Collectors.toList())));
                        }
                    }
                }

                // add to examination dto
                examDTOs.add(ExaminationDTO.toDetailReport(exam,labDTOs,treatmentsDTOS));
            }
            patientDetail.setExaminations(examDTOs);
        }

        return patientDetail;
    }
}
