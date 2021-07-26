package com.therapy.therapy.staff;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StaffService extends CRUDService<Staff> {
    ActionResponse<Staff> create(Staff staff);

    Staff getByStaffId(String staffId);

    Page<Staff> findAll(Pageable pageable);

    Page<Staff> searchByQuery(StaffSearchQuery query,Pageable pageable);

    Page<Staff> searchByKey(String key,Pageable pageable);

    ActionResponse<Staff> updateStaff(Staff staff);

    List<Staff> findByDepartmentActive(Long departmentId);

}
