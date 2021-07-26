package com.therapy.therapy.examination.patientHealthProblem;

import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.icd.Icd;
import com.therapy.therapy.icd.IcdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/healthProblem")
public class HealthProblemController {
    @Autowired
    private IcdService phealthProblemService;

    @PreAuthorize("hasAnyAuthority('ADMIN','EXAMINER','NURSE','MEDICAL')")
    @GetMapping("/examination/{id}")
    private Page<Icd> listProblemByExamination(@PathVariable("id")Long id) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(0, Constants.PAGE_SIZE, sort);
        return phealthProblemService.list(pageable);
    }
}
