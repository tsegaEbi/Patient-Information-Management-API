package com.therapy.therapy.appointment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query(value = "FROM Appointment a where  date BETWEEN :startDate AND :endDate")
    public List<Appointment> getAllBetweenDates(Date startDate, Date endDate);

    @Query("FROM Appointment where patient_id=:patientId AND active=:active ORDER BY  date DESC")
    List<Appointment> findByPatientId(Long patientId, Boolean active);

    Page<Appointment> findByExaminerIdAndActive(Long examinerId, Boolean active, Pageable pageable);

    Page<Appointment> findByDepartmentIdAndActive(Long departmentId, Boolean active, Pageable pageable);

    Page<Appointment> findByActive(Boolean active, Pageable pageable);

    Page<Appointment> findByPurpose(APPOINTMENT_PURPOSE purpose, Pageable pageable);


    List<Appointment> findByReferenceIdAndPurpose(Long patientId, APPOINTMENT_PURPOSE purpose);

    @Query("FROM Appointment l WHERE " +
            "(:examinerId is null  or l.examinerId = :examinerId)" +
            " and (:departmentId is null  or l.departmentId = :departmentId)" +
            " and (:purpose is null  or l.purpose = :purpose)" +
            " and (:active is null  or l.active = :active)" )
    Page<Appointment> searchByQuery(Long examinerId, Long departmentId, APPOINTMENT_PURPOSE purpose,
                                         Boolean active, Pageable pageable);

    @Query("FROM Appointment l WHERE " +
            "(:examinerId is null  or l.examinerId = :examinerId)" +
            " and (:departmentId is null  or l.departmentId = :departmentId)" +
            " and (:purpose is null  or l.purpose = :purpose)" +
            " and  l.date >= :date " +
            " and (:active is null  or l.active = :active)" )
    Page<Appointment> searchByQueryComing(Long examinerId, Long departmentId, APPOINTMENT_PURPOSE purpose,
                                    Boolean active, Date date, Pageable pageable);


}
