package com.therapy.therapy.appointment;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.department.Department;
import com.therapy.therapy.department.DepartmentService;
import com.therapy.therapy.examination.ExaminationService;
import com.therapy.therapy.examination.labOrder.LabOrderService;
import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.patient.PatientService;
import com.therapy.therapy.patient.checkup.PatientVisitService;
import com.therapy.therapy.security.SecurityService;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffService;
import com.therapy.therapy.treatment.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private PatientVisitService patientVisitService;

    @Autowired
    private LabOrderService labOrderService;

    @Autowired
    private AppointmentHelperService appointmentHelperService;
    @PostMapping("/byQuery")
    public Page<AppointmentDTO>byQuery(@RequestBody AppointmentSearchQueryDTO query){
        Sort sort =Sort.by(Sort.Direction.DESC,"date");
        Pageable pageable= PageRequest.of(query.getPageNumber(), Constants.PAGE_SIZE,sort);
        return appointmentService.getByQuery(query,pageable).map(a->appointmentHelperService.appointmentDTO(a));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER')")
    @PostMapping("/create")
    public ActionResponse<AppointmentDTO> create(@RequestBody AppointmentCreateDTO dto){
        ActionResponse<AppointmentDTO> response =new ActionResponse<>();
        response.setMessage("invalid");
        response.setResult(false);

        if(dto==null) return response;

        if(dto.getPurpose()==null){
            response.setMessage("Invalid purpose");
            return response;
        }

        if(dto.getPatientId()==null){
            response.setMessage("Invalid Patient Id");
            return response;
        }

        Patient patient =patientService.get(dto.getPatientId());
        Staff examiner=null;
        Department department=null;

        if(patient==null){
            response.setMessage("Unknown patient");
            return response;
        }

        if(dto.getExaminerId()!=null){
           examiner =staffService.get(dto.getExaminerId());
            if(examiner==null){
                response.setMessage("Unknown Examiner ");
                return response;
            }
        }

        if(dto.getDepartmentId()!=null){
            department =departmentService.get(dto.getDepartmentId());
            if(department==null){
                response.setMessage("Unknown Department ");
                return response;
            }
        }

        Appointment appointment =AppointmentCreateDTO.toAppointment(dto,patient);

        ActionResponse<Appointment> result =appointmentService.create(appointment);

            response.setMessage(result.getMessage());
            response.setResult(result.getResult());
            if(result.getT()!=null){
                Appointment newAppointment =(Appointment) result.getT();

              if(newAppointment!=null && newAppointment.getId()!=null){
                         response.setT(appointmentHelperService.appointmentDTO(newAppointment));
                    }
            }
        return response;
    }

    @GetMapping("/list/{pageNumber}")
    public Page<AppointmentDTO> list(@PathVariable("pageNumber")int pageNumber){
        Sort sort =Sort.by(Sort.Direction.DESC,"date");
        Pageable pageable= PageRequest.of(pageNumber, Constants.PAGE_SIZE,sort);
        return appointmentService.list(pageable)
                .map(p->appointmentHelperService.appointmentDTO(p));
    }

    @GetMapping("/byExaminer/{id}/{active}/{pageNumber}")
    public Page<AppointmentDTO> byExaminer(@PathVariable("id")Long id, @PathVariable("active")int pageNumber, @PathVariable("active")Boolean active){
        Pageable pageable= PageRequest.of(pageNumber, Constants.PAGE_SIZE);
        return appointmentService.getByExaminer(id,active,pageable)
                 .map(a->appointmentHelperService.appointmentDTO(a));
    }
    @GetMapping("/byPatient/{id}/{active}")
    public List<AppointmentDTO> byPatient(@PathVariable("id")Long id, @PathVariable("active")Boolean active){
        return appointmentService.getByPatient(id,active)
                .stream()
                .map(a->appointmentHelperService.appointmentDTO(a)).collect(Collectors.toList());
    }
    @GetMapping("/byPurpose/{id}/{purpose}")
    public List<AppointmentDTO> byPurpose(@PathVariable("id")Long id, @PathVariable("purpose")APPOINTMENT_PURPOSE purpose){

        return appointmentService.getByReferenceIdAndPurpose(id,purpose)
                .stream()
                .map(a->appointmentHelperService.appointmentDTO(a)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public  AppointmentDTO  get(@PathVariable("id")Long id){

        return  appointmentHelperService.appointmentDTO(appointmentService.get(id));
    }

    @PostMapping("/delete/{id}")
    public Boolean delete(@PathVariable("id")Long id) throws Exception {

        Staff staff =staffService.getByStaffId(securityService.getStaffId());
        Appointment appointment =appointmentService.get(id);
        if(appointment==null){

            return false;
        }
        if(securityService.isAdmin()){
            appointmentService.delete(appointment);
            return true;
        }

        if(appointment.getExaminerId()!=null && staff.getId() == appointment.getExaminerId() ){
            appointmentService.delete(appointment);
            return true;
        }
        return false;
    }
}
