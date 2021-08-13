package com.therapy.therapy.appointment;

import com.therapy.therapy.common.ActionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class AppointmentServiceImp implements AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentServiceImp(AppointmentRepository repository){
        this.repository =repository;
    }



    @Override
    public List<Appointment> getByPatient(Long patientId, Boolean active) {
        return repository.findByPatientId(patientId,active);
    }

    @Override
    public List<Appointment> getToday() {
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, 1);
        Date after = c.getTime();

        c.add(Calendar.DATE, -2);
        Date before = c.getTime();
        return  repository.getAllBetweenDates(before,after);
    }

    @Override
    public Page<Appointment> getByExaminer(Long examinerId, Boolean active, Pageable pageable) {
        return repository.findByExaminerIdAndActive(examinerId,active,pageable);
    }

    @Override
    public Page<Appointment> getByDepartment(Long departmentId, Boolean active, Pageable pageable) {
        return repository.findByDepartmentIdAndActive(departmentId,active,pageable);
    }

    @Override
    public Page<Appointment> getByActive(Boolean active,Pageable pageable) {
        return repository.findByActive(active,pageable);
    }

    @Override
    public Page<Appointment> getByPurpose(APPOINTMENT_PURPOSE purpose, Pageable pageable) {
        return repository.findByPurpose(purpose,pageable);
    }

    @Override
    @Transactional
    public ActionResponse<Appointment> create(Appointment appointment) {
        ActionResponse<Appointment> result =new ActionResponse<>();
        result.setResult(true);
        result.setMessage("successful");

        try{
            appointment=repository.save(appointment);
            result.setT(appointment);

        }catch (Exception e){
            result.setT(appointment);
            result.setResult(false);
            result.setMessage("DB error");
        }
        return result;
    }

    @Override
    public List<Appointment> getByReferenceId(Long referenceId, APPOINTMENT_PURPOSE purpose) {
        return repository.findByReferenceIdAndPurpose(referenceId,purpose);
    }

    @Override
    public Appointment activate(Long id, Boolean active) {
        Appointment appointment =get(id);
        if(appointment!=null){
            appointment.setActive(active);
            return appointment;
        }
        return null;
    }

    @Override
    public List<Appointment> getByExamination(Long examinationId) {
        return repository.findByReferenceIdAndPurpose(examinationId,APPOINTMENT_PURPOSE.EXAMINATION);
    }

    @Override
    public List<Appointment> getByTreatment(Long treatmentId) {
        return repository.findByReferenceIdAndPurpose(treatmentId,APPOINTMENT_PURPOSE.TREATMENT);
    }

    @Override
    public List<Appointment> getByCheckUp(Long visitId) {
        return  repository.findByReferenceIdAndPurpose(visitId,APPOINTMENT_PURPOSE.CHECKUP);
    }

    @Override
    public List<Appointment> getByReferenceIdAndPurpose(Long visitId, APPOINTMENT_PURPOSE purpose) {
        return repository.findByReferenceIdAndPurpose(visitId,purpose);
    }

    @Override
    public Page<Appointment> getByQuery(AppointmentSearchQueryDTO query, Pageable pageable) {
        if(query.getComing())
        {
            return repository.searchByQueryComing(query.getExaminerId(),query.getDepartmentId(),query.getPurpose(),query.getActive(),new Date(),pageable);

        }
        return repository.searchByQuery(query.getExaminerId(),query.getDepartmentId(),query.getPurpose(),query.getActive(),pageable);
    }

    @Override
    public Appointment get(Long id) {
        return  repository.findById(id).orElse(null);
    }

    @Override
    public Page<Appointment> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Appointment add(Appointment appointment) throws Exception {
        return null;
    }

    @Override
    public Appointment update(Appointment appointment) throws Exception {
        return repository.save(appointment);
    }

    @Override
    public void delete(Appointment appointment) throws Exception {
        repository.delete(appointment);
    }
}
