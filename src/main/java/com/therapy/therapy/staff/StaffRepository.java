package com.therapy.therapy.staff;

import com.therapy.therapy.common.EMPLOYMENT;
import com.therapy.therapy.common.QUALIFICATION;
import com.therapy.therapy.common.STATUS;
import com.therapy.therapy.common.Sex;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff,Long> {

     Staff findByStaffId(String staffId);
     Page<Staff> findByStatus(STATUS status, Pageable pageable);


    @Query("FROM Staff s WHERE " +
            "(:departmentId is null or s.department.id= :departmentId) " +
            " and (:sex is null  or s.sex = :sex)" +
            " and (:employment is null or s.employment=:employment)" +
            " and (:qualification is null or s.qualification=:qualification)" +
            " and (:status is null or s.status=:status) " )
     Page<Staff> searchByQuery(Long departmentId, STATUS status,
                                     QUALIFICATION qualification,
                                     EMPLOYMENT employment, Sex sex, Pageable pageable);

    @Query("FROM Staff s where lower(s.staffId) like lower(concat('%',:key,'%')) or lower(s.firstName) like lower(concat('%',:key,'%')) or lower(s.fatherName) like lower(concat('%',:key,'%')) or lower(s.lastName) like lower(concat('%',:key,'%'))")

    Page<Staff> searchByKey(String key, Pageable pageable);

    List<Staff> findByDepartmentIdAndStatus(Long departmentId,STATUS status);
}
