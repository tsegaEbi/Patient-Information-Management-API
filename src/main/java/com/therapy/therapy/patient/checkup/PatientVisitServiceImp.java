package com.therapy.therapy.patient.checkup;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.Validation;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.patient.PatientService;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientVisitServiceImp implements PatientVisitService {
    private final PatientVisitRepository repository;

    @Autowired
    private PatientService patientService;

    Logger logger = LoggerFactory.getLogger(PatientVisitServiceImp.class);


    public PatientVisitServiceImp(PatientVisitRepository repository){

        this.repository =repository;
    }
    @Override
    @Transactional
    public  PatientVisit assignDepartment(Long id,Long departmentId) {

         PatientVisit visit = get(id);
         visit.setDepartment(departmentId);
         visit.setExamined(false);
         repository.save(visit);
         return visit;
    }
    @Override
    @Transactional
    public PatientVisit assignExaminer(Long id,Long examinerId){
        PatientVisit visit = get(id);
        visit.setExaminer(examinerId);
        visit.setExamined(false);
        repository.save(visit);
        return visit;
    }

    @Override
    public Page<PatientVisit> getByExaminerAndExamined(Long examinerId, Boolean examined) {
        Pageable pageable = PageRequest.of(0, Constants.PAGE_FOR_ALL);
        return repository.findByExaminerAndExamined(examinerId,examined,pageable);
    }

    @Override
    public void Examined(PatientVisit visit) {
        visit.setExamined(true);
        repository.save(visit);
    }

    @Override
    public List<PatientVisit> getByPatientId(Long patientId){

        Patient patient =patientService.get(patientId);
        if(patient!=null){

            return repository.findByPatient(patient);
        }
        return null;
    }

    @Override
    public Page<PatientVisit> getByDepartmentId(Long departmentId,Boolean examined,Pageable pageable) {
        return repository.findByDepartmentAndExamined(departmentId,examined,pageable);
    }

    @Override
    public Page<PatientVisit> getByExaminerId(Long examinerId, Boolean examined,Pageable pageable) {
        return repository.findByExaminerAndExamined(examinerId,examined,pageable);
    }

    @Override
    public Page<PatientVisit> getByQuery(PatientVisitSearchQuery query, Pageable pageable) {
        //Long patientId,Long examinerId, Long departmentId, Boolean examined,
        return repository.searchByQuery(query.getPatientId(),query.getExaminerId(),query.getDepartmentId(),query.getExamined(),pageable);
    }

    @Override
    public Page<PatientVisit> getByExamined(Boolean examined, Pageable pageable) {
        return repository.findByExamined(examined,pageable);
    }

    @Override
    @Transactional
    public ActionResponse<PatientVisit> create(PatientVisit visit) {
        ActionResponse<PatientVisit> result =new ActionResponse<>();
         try {
                repository.save(visit);
                result.setT(visit);
                result.setResult(true);
                return result;
            } catch (Exception e) {
                result.setResult(false);
                result.setMessage(e.getMessage());
                return result;
            }



    }
    private Validation validateCreate(PatientVisit visit){
        Validation validate = new Validation();
        validate.setMessage("validate");
        validate.setStatus(true);

        if(visit.getPatient()==null){
            validate.setMessage("Invalid pationt ");
            validate.setStatus(false);
        }
        return validate;
    }
    @Override
    public PatientVisit get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<PatientVisit> list(Pageable pageable) {

        return repository.findAll(pageable);

    }

    @Override
    public PatientVisit add(PatientVisit patientVisit) {
        return null;
    }

    @Override
    public PatientVisit update(PatientVisit patientVisit) {
        return null;
    }

    @Override
    @Transactional
    public void delete(PatientVisit patientVisit) {
            try{
                repository.delete(patientVisit);
            }catch (HibernateException e){
                throw e;
            }
    }
}
