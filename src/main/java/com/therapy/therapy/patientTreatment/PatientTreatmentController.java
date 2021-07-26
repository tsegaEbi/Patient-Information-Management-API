package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.examination.ExaminationService;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgress;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgressCreateDTO;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgressDTO;
import com.therapy.therapy.patientTreatment.progress.TreatmentProgressService;
import com.therapy.therapy.security.SecurityService;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffService;
import com.therapy.therapy.treatment.Treatment;
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
@RequestMapping("/patientTreatment")
public class PatientTreatmentController {

    @Autowired
    private PatientTreatmentService patientTreatmentService;
    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TreatmentProgressService progressService;

    @Autowired
    private StaffService staffService;

    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER')")
    @PostMapping("/create")
    public ActionResponse<PatientTreatmentDTO> create(@RequestBody PatientTreatmentCreateDTO dto) {
        ActionResponse<PatientTreatmentDTO> response = new ActionResponse<>();
        response.setResult(false);
        response.setMessage("Invalid inputs");

        if (dto.getDose() == null || dto.getDuration() == null || dto.getExaminationId() == null || dto.getTreatmentId() == null
                || dto.getFrequency() == null)
            return response;

        Examination exam = examinationService.get(dto.getExaminationId());
        if (exam == null) {
            response.setMessage("Unknown Examination");
            return response;
        }
        Treatment treatment = treatmentService.get(dto.getTreatmentId());
        if (treatment == null) {
            response.setMessage("Unknown Treatment");
            return response;
        }

        PatientTreatment pt = PatientTreatmentCreateDTO.toPT(dto, exam, treatment);
        ActionResponse<PatientTreatment> result = patientTreatmentService.create(pt);
        response.setResult(result.getResult());
        response.setMessage(result.getMessage());
        if (result.getT() != null) {
            response.setT(PatientTreatmentDTO.toDTO((PatientTreatment) result.getT()));
        }
        return response;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER')")
    @PostMapping("/update")
    public  ActionResponse<PatientTreatmentDTO> update(@RequestBody PatientTreatmentUpdateDTO dto) throws Exception {
        ActionResponse<PatientTreatmentDTO> response =new ActionResponse<>();
        response.setMessage("Unknown Treatment");
        response.setResult(false);

        PatientTreatment pt =patientTreatmentService.get(dto.getId());
        Treatment treatment =treatmentService.get(dto.getTreatmentId());

        if(pt==null)
             return response;
        if(treatment==null)
        {
            response.setMessage("Unknown treatment");
            return response;
        }
        if(pt.getActive()){
            response.setMessage("You can't change active treatment");
            return response;
        }
        if(!pt.getResult().equals(PATIENT_TREATMENT_RESULT.PENDING))
        {
            response.setMessage("You can't change active or in progress treatment");
            return response;
        }
        Staff staff =staffService.getByStaffId(securityService.getStaffId());

        if(pt.getExamination().getExaminer()!=null)
        if(staff.getId()!=pt.getExamination().getExaminer().getId())
          if(!securityService.isAdmin())
             {
                 response.setMessage("You must be an Admin or the examiner to change the treatment");
                 return response;
             }

       PatientTreatment newTreatment =patientTreatmentService.update(PatientTreatmentUpdateDTO.toDTO(dto,pt,treatment));
       response.setT(PatientTreatmentDTO.toDTO(newTreatment));
       response.setMessage("Successful");
       response.setResult(true);


        return response;
    }

    @GetMapping("/list/{pageNumber}")
    public Page<PatientTreatmentDTO> list(@PathVariable("pageNumber") int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE, sort);
        return patientTreatmentService.list(pageable).map(t -> PatientTreatmentDTO.toDTO(t));
    }
    @PostMapping("/byQuery")
    public Page<PatientTreatmentDTO> byQuery(@RequestBody PatientTreatmentSearchQuery query) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(query.getPageNUmber(), Constants.PAGE_SIZE, sort);
        return patientTreatmentService.searchByQuery(query,pageable).map(t -> PatientTreatmentDTO.toDTO(t));
    }

    @GetMapping("/examination/{id}")
    public List<PatientTreatmentDTO> list(@PathVariable("id") Long id) {
        return patientTreatmentService.getByExamination(examinationService.get(id)).stream().map(t -> PatientTreatmentDTO.toDTO(t)).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public PatientTreatmentDTO get(@PathVariable("id") Long id) {
        return PatientTreatmentDTO.toDTO(patientTreatmentService.get(id));
    }
    @PostMapping("/updateStatus/{id}")
    public   PatientTreatmentDTO  updateStatus(@PathVariable("id") Long id) throws Exception {
            PatientTreatment treatment =patientTreatmentService.get(id);
            if(treatment==null){
                return null;
            }
            Staff staff =staffService.getByStaffId(securityService.getStaffId());
            if(securityService.isAdmin() || staff.getId()==treatment.getExamination().getExaminer().getId())
                {
                    return PatientTreatmentDTO.toDTO(patientTreatmentService.updateStatus(id));
                }

        return PatientTreatmentDTO.toDTO(patientTreatmentService.get(id));
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER')")
    @PostMapping("/updateStart/{id}")
    public   PatientTreatmentDTO  updateStart(@PathVariable("id") Long id) throws Exception {

        Staff staff =staffService.getByStaffId(securityService.getStaffId());
        if(securityService.isAdmin())
        {
            return PatientTreatmentDTO.toDTO(patientTreatmentService.updateStart(id));
        }

        return PatientTreatmentDTO.toDTO(patientTreatmentService.get(id));
    }
    @PostMapping("/updateResult")
    public  ActionResponse<PatientTreatmentDTO>  updateResult(@RequestBody PatientTreatmentResultUpdateDTO dto) throws Exception {

        ActionResponse<PatientTreatmentDTO> response = new ActionResponse<>();
        response.setMessage("valid");
        response.setResult(true);

        Staff staff =staffService.getByStaffId(securityService.getStaffId());
        PatientTreatment treatment =patientTreatmentService.get(dto.getId());
        if(treatment==null)
        {
            response.setResult(false);
            response.setMessage("Unknown Treatment");
            return response;
        }

        if(!securityService.isAdmin() && treatment.getExamination().getExaminer().getId()==staff.getId()){
            response.setMessage("No privileges to change treatment result");
            response.setResult(false);
            return response;
        }

        if(treatment.getResult().equals(dto.getResult())){
            response.setMessage("No Change");
            response.setResult(false);
            return response;
        }

        if(treatment.getResult().equals(PATIENT_TREATMENT_RESULT.COMPLETED)){
            response.setMessage("The Treatment is already completed");
            response.setResult(false);
            return response;
        }
        if(dto.getResult()==null){
            response.setMessage("Unknown Treatment result");
            response.setResult(false);
            return response;
        }
         PatientTreatment newTreatment=patientTreatmentService.updateResult(dto.getId(),dto.getResult());
         PatientTreatmentDTO newDto =PatientTreatmentDTO.toDTO(newTreatment);
         response.setT(newDto);

        return response;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','NURSE','MEDICAL')")
    @PostMapping("/progress/create")
    public   ActionResponse<TreatmentProgressDTO>  updateResult(@RequestBody TreatmentProgressCreateDTO dto) throws Exception {

        PatientTreatment pt =patientTreatmentService.get((dto.getPatientTreatmentId()));

        ActionResponse<TreatmentProgressDTO> response =new ActionResponse<>();
        response.setResult(false);
        response.setMessage("invalid");

        Staff reporter =staffService.getByStaffId(securityService.getStaffId());

        if(dto==null || dto.getPatientTreatmentId()==null ||pt==null)
            return response;


         if(pt.getResult().equals(PATIENT_TREATMENT_RESULT.ABORTED)){
             response.setMessage("The treatment is aborted. You can't make progress on an ABORTED treatment");
             return response;
         }
        if(pt.getResult().equals(PATIENT_TREATMENT_RESULT.COMPLETED)){
            response.setMessage("The treatment is aborted. You can't make progress on a TERMINATED treatment");
            return response;
        }
        if(!pt.getActive()){
            response.setMessage("The treatment is NOT_ACTIVE. You can't make progress on an INACTIVE treatment");
            return response;
        }

        TreatmentProgress progress= progressService.add(TreatmentProgressCreateDTO.toProgress(dto,pt,reporter));
        response.setMessage("success");
        response.setResult(true);
        response.setT(TreatmentProgressDTO.toDTO(progress));
        return response;
    }

    @GetMapping("/progress/list/{pageNumber}")
    public Page<TreatmentProgressDTO> progressList(@PathVariable("pageNumber") int pageNumber) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE, sort);
        return progressService.list(pageable).map(t -> TreatmentProgressDTO.toDTO(t));
    }
    @GetMapping("/progress/byPatientTreatment/{id}")
    public List<TreatmentProgressDTO> progressByTreatment(@PathVariable("id") Long id) {

        PatientTreatment tr =patientTreatmentService.get(id);
        if(tr==null)
            return null;

        return progressService.getByPatientTreatment(tr).stream().map(t -> TreatmentProgressDTO.toDTO(t)).collect(Collectors.toList());
    }
    @GetMapping("/progress/byExamination/{id}")
    public List<TreatmentProgressDTO> progressByExamination(@PathVariable("id") Long id) {

        Examination exam =examinationService.get(id);
        if(exam==null)
            return null;

        return progressService.getByExamination(exam.getId()).stream().map(t -> TreatmentProgressDTO.toDTO(t)).collect(Collectors.toList());
    }
}
