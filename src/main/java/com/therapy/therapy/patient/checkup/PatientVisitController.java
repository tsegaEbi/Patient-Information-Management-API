package com.therapy.therapy.patient.checkup;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.department.Department;
import com.therapy.therapy.department.DepartmentService;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.examination.ExaminationCreateDTO;
import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.examination.ExaminationService;
import com.therapy.therapy.examination.labOrder.LabOrderDTO;
import com.therapy.therapy.examination.labOrder.LabOrderService;
import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.patient.PatientService;
import com.therapy.therapy.security.SecurityService;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
@RequestMapping("/patientVisit")

public class PatientVisitController {
    @Autowired
    private PatientVisitService visitService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
     private ExaminationService examinationService;

    @Autowired
    private LabOrderService labService;

    Logger logger = LoggerFactory.getLogger(PatientVisitController.class);

    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    @GetMapping("/list/{pageNumber}")
    public Page<PatientVisitDTO> getList(@PathVariable("pageNumber")int pageNumber){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE, sort);

        return  visitService.list(pageable)
                .map(v->PatientVisitDTO.toPatientVisitDTO(v,departmentService.get(v.getDepartment()),staffService.get(v.getExaminer()),null));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    @GetMapping("/byPatient/{id}")
    public List<PatientVisitDTO> getByPatientId(@PathVariable("id")Long id){


        return  visitService.getByPatientId(id)
                .stream()
                .map(v->PatientVisitDTO.toPatientVisitDTO(v,departmentService.get(v.getDepartment()),staffService.get(v.getExaminer()),null))
   .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public PatientVisitDTO get(@PathVariable("id")Long id){
         PatientVisit visit =visitService.get(id);
         Examination exam= null;

         if(visit!=null){
             exam =examinationService.getByPatientVisit(visit);
         }

        return PatientVisitDTO.toPatientVisitDTO(visit,
                departmentService.get(visit.getDepartment()),staffService.get(visit.getExaminer()),
                ExaminationDTO.toDTO(exam,null));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public ActionResponse<PatientVisitDTO> add(@RequestBody PatientVisitCreateDTO dto) throws IllegalAccessException {
        Staff staff =staffService.getByStaffId(securityService.getStaffId());

        if(staff==null)
            throw new IllegalAccessException("Staff not found");

        ActionResponse<PatientVisitDTO> response = new ActionResponse<>();
        ActionResponse<PatientVisit> result = new ActionResponse<>();



          if(dto.getPatientId()!=null) {
              Patient patient = patientService.get(dto.getPatientId());

              result = visitService.create(PatientVisitCreateDTO.toPatientVisit(dto, patient, staff));

              response.setMessage(result.getMessage());
              response.setResult(result.getResult());

              if (result.getT() != null) {
                  PatientVisit visit = (PatientVisit) result.getT();
                  response.setT(PatientVisitDTO.toPatientVisitDTO(visit,departmentService.get(visit.getDepartment()),staff,null));
              }
              return response;
          }
          else
          {
              response.setMessage("Invalid patient Id value");
              response.setResult(false);
          }


        return  response;
    }

    // due to unexpected and unsolved securitservice null problem, removed to this end point
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER')")
    @PostMapping("/addExamination")
    private ActionResponse<ExaminationDTO> addExamination(@RequestBody ExaminationCreateDTO dto) throws IllegalAccessException{
        String staffId =securityService.getStaffId();

        Staff staff =staffService.getByStaffId(staffId);

        ActionResponse<ExaminationDTO> response= new ActionResponse<>();
        response.setResult(false);

        if(staff==null){
            throw new IllegalArgumentException("Unknown Staff");

        }

        PatientVisit visit =visitService.get(dto.getPatientVisitId());

        if(visit==null){
            response.setMessage("Unknown Checkups");
        }
        else {
            Examination examination =ExaminationCreateDTO.toExamination(dto,visit,staff);
            ActionResponse<Examination> result =examinationService.create(examination,dto.getLaboratoryCreates());

            response.setMessage(result.getMessage());
            response.setResult(result.getResult());

            if(result.getResult()) {
                List<LabOrderDTO> labOrders =labService.getByExamination((Examination)response.getT())
                        .stream()
                        .map(t->LabOrderDTO.toDTO(t,null)).collect(Collectors.toList());
                if (result.getT() != null) {
                    response.setT(ExaminationDTO.toDetail((Examination) result.getT(), labOrders));
                }
            }
        }

        return response;

    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/delete/{id}")
     public ActionResponse<PatientVisitDTO> remove(@PathVariable("id") Long id) throws IllegalAccessException {
        Staff staff =staffService.getByStaffId(securityService.getStaffId());

        if(staff==null)
            throw new IllegalAccessException("Staff not found");

        ActionResponse<PatientVisitDTO> response = new ActionResponse<>();
        ActionResponse<PatientVisit> result = new ActionResponse<>();

        PatientVisit visit =visitService.get(id);
        if(visit==null){
            response.setResult(false);
            response.setMessage("Unknown Checkup information");
        }
        if(visit.getExamined()==true){
            response.setResult(false);
            response.setMessage("You can't remove an examined Checkup");
        }
        try{
            visitService.delete(visit);
            response.setResult(true);
            response.setMessage("removed successfully");
        }catch(Exception e){
            response.setResult(false);
            response.setMessage("Unable to remove the Checkup");
        }

        return  response;
    }
    @PostMapping("/byQuery")
    public Page<PatientVisitDTO> byQuery(@RequestBody PatientVisitSearchQuery query) throws IllegalAccessException {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(query.getPageNumber(), Constants.PAGE_SIZE, sort);

        return  visitService.getByQuery(query,pageable)
                .map(v->PatientVisitDTO.toPatientVisitDTO(v,departmentService.get(v.getDepartment()),staffService.get(v.getExaminer()),null));

    }
    @PostMapping("/assign")
    public  PatientVisitDTO  assign(@RequestBody PatientVisitAssignmentCreate dto) throws IllegalAccessException {
        Department dept =new Department();
        Staff staff = new Staff();
         if(dto.getPatientVisitId()==null){
            return null;
         }
         PatientVisit visit =visitService.get(dto.getPatientVisitId());

         if(visit==null)
             return null;

         if(dto.getCode().equals(VISIT_ASSIGNMENT_CODE.DEPARTMENT))
         {
             // update assign department
             dept= departmentService.get(dto.getAssigneeId());
             if(dept==null)
                 return null;
             visit=visitService.assignDepartment(visit.getId(),dto.getAssigneeId());
         }
         if(dto.getCode().equals(VISIT_ASSIGNMENT_CODE.EXAMINER)){
             //update
             staff =staffService.get(dto.getAssigneeId());

             if(staff==null)
                 return null;

             visit=visitService.assignExaminer(visit.getId(),dto.getAssigneeId());
         }

        return PatientVisitDTO.toPatientVisitDTO(visit,dept,staff,
                ExaminationDTO.toDTO(examinationService.getByPatientVisit(visit),null));
    }

}
