package com.therapy.therapy.examination.labOrder;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.laboratory.Laboratory;
import com.therapy.therapy.staff.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LabOrderService extends CRUDService<LabOrder> {
    List<LabOrder> getByExamination(Examination examination);
    Page<LabOrder> getByTechnician(Staff staff, Pageable pageable);
    Page<LabOrder>getByExaminer(Staff staff, Pageable pageable);


    ActionResponse<LabOrder> create(Examination exam, List<Laboratory> orders);
    void activate(Long id);

    LabOrder assignTechnician(Long id, Long staffId);


    Page<LabOrder> getByQuery(LabOrderSearchQuery query, Pageable pageable);


    Page<LabOrder>getAllByExaminersAndStatus(Long staffId, ORDER_STATUS status);

}
