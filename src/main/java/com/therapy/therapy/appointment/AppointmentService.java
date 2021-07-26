package com.therapy.therapy.appointment;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AppointmentService extends CRUDService<Appointment> {

    List<Appointment> getByPatient(Long patientId,Boolean active);

    Page<Appointment> getByExaminer(Long examinerId, Boolean active, Pageable pageable);

    Page<Appointment> getByDepartment(Long departmentId, Boolean active, Pageable pageable);

    Page<Appointment> getByActive(Boolean active,Pageable pageable);

    Page<Appointment> getByPurpose(APPOINTMENT_PURPOSE purpose,Pageable pageable);

    ActionResponse<Appointment> create(Appointment appointment);

    List<Appointment> getByReferenceId(Long referenceId, APPOINTMENT_PURPOSE purpose);

    Appointment activate(Long id, Boolean active);

    List<Appointment> getByExamination(Long examinationId);
    List<Appointment>getByTreatment(Long treatmentId);
    List<Appointment>getByCheckUp(Long visitId);
    List<Appointment>getByReferenceIdAndPurpose(Long visitId, APPOINTMENT_PURPOSE purpose);

    Page<Appointment> getByQuery(AppointmentSearchQueryDTO query,Pageable pageable);
}
