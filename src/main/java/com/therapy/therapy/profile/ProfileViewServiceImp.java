package com.therapy.therapy.profile;

import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.department.DepartmentService;
import com.therapy.therapy.examination.ExaminationService;
import com.therapy.therapy.examination.labOrder.LabOrder;
import com.therapy.therapy.examination.labOrder.LabOrderService;
import com.therapy.therapy.patient.PatientService;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.patient.checkup.PatientVisitService;
import com.therapy.therapy.patientTreatment.PATIENT_TREATMENT_RESULT;
import com.therapy.therapy.patientTreatment.PatientTreatmentService;
import com.therapy.therapy.security.SecurityService;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class ProfileViewServiceImp implements ProfileViewService {
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
    private LabOrderService labOrderService;

    @Autowired
    private PatientTreatmentService treatmentService;

    @Autowired
    private ExaminationService examinationService;


    @Override
    public Page<PatientVisit> getVisitsPending(Staff staff, Pageable pageable) {
        return patientVisitService.getByExaminerId(staff.getId(),false,pageable);
    }

    @Override
    public ProfileViewDTO getProfile(Long id) {
        Pageable pageable = PageRequest.of(0, Constants.PAGE_FOR_ALL);
         ProfileViewDTO profile = new ProfileViewDTO();

         profile.setId(id);
         profile.setPendingOrders(getPendingOrders(id));
         profile.setPendingVisits(getPendingVisits(id));
         profile.setPendingTreatments(pendingTreatments(id));
        return profile;
    }

    @Override
    public int getPendingVisits(Long id) {
        return patientVisitService.getByExaminerAndExamined(id,Boolean.FALSE)==null?0:
                patientVisitService.getByExaminerAndExamined(id,Boolean.FALSE).getTotalPages();
    }

    @Override
    public int getPendingOrders(Long id) {
        Page<LabOrder>orders =getProfileLabOrders(id,ORDER_STATUS.WAITING);
      if(orders==null){
          return 0;
      }
      else
          return (int) orders.getTotalElements();
    }

    @Override
    public int pendingTreatments(Long id) {
        return   treatmentService.getAllByExaminerAndStatus(id, PATIENT_TREATMENT_RESULT.IN_PROGRESS)==null?0:
                (int) treatmentService.getAllByExaminerAndStatus(id, PATIENT_TREATMENT_RESULT.IN_PROGRESS).getTotalElements();
    }

    @Override
    public int pendingStatusChanges(Long id) {
        return labOrderService.getAllByExaminersAndStatus(id, ORDER_STATUS.WAITING)==null? 0:
                (int)   labOrderService.getAllByExaminersAndStatus(id, ORDER_STATUS.WAITING).getTotalElements();
    }

    @Override
    public Page<PatientVisit> getProfileVisits(Long examinerId,Boolean examined) {

        return patientVisitService.getByExaminerAndExamined(staffService.getByStaffId(securityService.getStaffId()).getId(),examined);
    }

    @Override
    public Page<LabOrder> getProfileLabOrders(Long examinerId, ORDER_STATUS status) {
        return labOrderService.getAllByExaminersAndStatus(examinerId,status);
    }
}
