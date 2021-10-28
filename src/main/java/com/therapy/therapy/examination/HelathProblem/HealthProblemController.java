package com.therapy.therapy.examination.HelathProblem;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.examination.ExaminationService;
import com.therapy.therapy.icdCode.Icd;
import com.therapy.therapy.icdCode.IcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/healthProblem")
public class HealthProblemController {

    @Autowired
    private HealthProblemService healthProblemService;

    @Autowired
    private ExaminationService examinationService;

    @Autowired
    private IcdService icdService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public  HealthProblemDTO  get(@PathVariable("id")Long id){
        HealthProblem problem =healthProblemService.get(id);
        if(problem==null)
            return null;

         return HealthProblemDTO.toDTO(problem) ;
    }
    @GetMapping("/solve/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public  HealthProblemDTO  get(@PathVariable("id")Long id,@RequestParam("solvedNote")String solvedNote) throws Exception {
        HealthProblem problem =healthProblemService.get(id);
        if(problem==null)
            return null;
        problem.setStatus(HEALTH_PROBLEM_STATUS.SOLVED);
        problem.setSolvedDate(new Date());
        problem.setSolvedNote(solvedNote);
        return HealthProblemDTO.toDTO(healthProblemService.add(problem)) ;
    }
    @GetMapping("/examination/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public List<HealthProblemDTO> listByExamination(@PathVariable("id")Long id){
        Examination examination= examinationService.get(id);
        if(examination==null) return null;

        return healthProblemService.getByExamination(examination).stream().map(e->HealthProblemDTO.toDTO(e)).collect(Collectors.toList());
    }
    @GetMapping("/patient/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public List<HealthProblemDTO> listByPatient(@PathVariable("id")Long id){

        return healthProblemService.getByPatient(id).stream().map(e->HealthProblemDTO.toDTO(e)).collect(Collectors.toList());
    }
    @GetMapping("/list/{pageNumber}")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public Page<HealthProblemDTO> list(@PathVariable("pageNumber")int pageNumber){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE, sort);
        return healthProblemService.list(pageable).map(e->HealthProblemDTO.toDTO(e));
    }

    @PostMapping("/byQuery")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public Page<HealthProblemDTO> searchByQuery(@RequestBody HealthProblemSearchQuery query){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(query.getPageNumber(), Constants.PAGE_SIZE, sort);

        // BY_ICD, BY_SUB, BY_CAT
        if( query.getIcdId()!=null){
            return healthProblemService.getByIcdCode(query.getIcdId(),query.getStatus(),pageable).map(t->HealthProblemDTO.toDTO(t));
        }
        else if(query.getIcdSubCategory()!=null){
            return healthProblemService.getByIcdSubCategory(query.getIcdSubCategory(),query.getStatus(),pageable).map(t->HealthProblemDTO.toDTO(t));
        }
        else if (query.getCategory()!=null){
            return healthProblemService.getByIcdCategory(query.getCategory(),query.getStatus(),pageable).map(t->HealthProblemDTO.toDTO(t));
        }
        return healthProblemService.searchByQuery(query,pageable).map(t->HealthProblemDTO.toDTO(t));
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','MEDICAL','NURSE')")
    public ActionResponse<HealthProblemDTO> create(@RequestBody HealthProblemCreateDTO dto){
        ActionResponse<HealthProblemDTO> response = new ActionResponse();
        Examination examination =examinationService.get(dto.getExaminationId());
        if(examination==null){
            response.setResult(false);
            response.setMessage("Unknown examination");

        }
        Icd icd = icdService.get(dto.getIcdId());
        if(icd==null){
            response.setResult(false);
            response.setMessage("Unknown ICD code");

        }
        ActionResponse<HealthProblem> result =healthProblemService.create(HealthProblemCreateDTO.toHealthProblem(dto,examination,icd));

        response.setMessage(result.getMessage());
        response.setResult(result.getResult());
        response.setT(HealthProblemDTO.toDTO((HealthProblem)result.getT()));

        return response;
    }
}
