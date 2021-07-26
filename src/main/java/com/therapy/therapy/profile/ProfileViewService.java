package com.therapy.therapy.profile;

import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.examination.labOrder.LabOrder;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.staff.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileViewService {

      Page<PatientVisit> getVisitsPending(Staff staff, Pageable pageable);

      ProfileViewDTO getProfile(Long id);

      int getPendingVisits(Long id);
      int getPendingOrders(Long id);
      int pendingTreatments(Long id);
      int pendingStatusChanges(Long id);

      Page<PatientVisit> getProfileVisits(Long examinerId,Boolean examined);
    Page<LabOrder> getProfileLabOrders(Long examinerId, ORDER_STATUS status);


}
