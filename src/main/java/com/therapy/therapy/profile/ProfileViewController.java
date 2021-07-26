package com.therapy.therapy.profile;

import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.department.DepartmentService;
import com.therapy.therapy.examination.labOrder.LabOrderDTO;
import com.therapy.therapy.patient.PatientService;
import com.therapy.therapy.patient.checkup.PatientVisitDTO;
import com.therapy.therapy.patient.checkup.PatientVisitService;
import com.therapy.therapy.security.SecurityService;
import com.therapy.therapy.staff.StaffDTO;
import com.therapy.therapy.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile")
public class ProfileViewController {
    @Autowired
    private StaffService staffService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PatientVisitService patientVisitService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ProfileViewService profileService;

    @PostMapping("/visit/notExamined")
    public Page<PatientVisitDTO> getPendingVisits(@RequestBody int pageNumber){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE, sort);
        return patientVisitService.getByExaminerId(staffService.getByStaffId(securityService.getStaffId()).getId(),false,pageable)
                .map(v->PatientVisitDTO.toPatientVisitDTO(v,departmentService.get(v.getDepartment()),staffService.get(v.getExaminer()),null));
    }


      @GetMapping("/get")
      public ProfileViewDTO getProfile(){
        ProfileViewDTO profile =profileService.getProfile(staffService.getByStaffId(securityService.getStaffId()).getId());
        if(profile==null) throw new IllegalArgumentException("Unknown Staff");

        profile.setStaff(StaffDTO.toDTO(staffService.getByStaffId(securityService.getStaffId())));
        return profile;
      }

    @GetMapping("/getVisits/{examined}")
    public Page<PatientVisitDTO> getVisits(@PathVariable("examined")Boolean examined){
             return profileService.getProfileVisits(staffService.getByStaffId(securityService.getStaffId()).getId(),examined)
                .map(t->PatientVisitDTO.toProfileDTO(t));
    }
    @GetMapping("/getLabOrders/{status}")
    public Page<LabOrderDTO> getLabOrders(@PathVariable("status") ORDER_STATUS status){
        return profileService.getProfileLabOrders(staffService.getByStaffId(securityService.getStaffId()).getId(),status)
                .map(t-> LabOrderDTO.toDTO(t,staffService.getByStaffId(securityService.getStaffId())));
    }




}
