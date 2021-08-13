package com.therapy.therapy.patientTreatment;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.treatment.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class PatientTreatmentServiceImp implements PatientTreatmentService{
    private final PatientTreatmentRepository repository;

    public PatientTreatmentServiceImp(PatientTreatmentRepository repository){
        this.repository=repository;
    }
    @Override
    public PatientTreatment get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<PatientTreatment> list(Pageable pageable) {
        return null;
    }


    @Override
    public PatientTreatment add(PatientTreatment patientTreatment) throws Exception {
        return null;
    }


    @Override
    @Transactional
    public PatientTreatment update(PatientTreatment patientTreatment) throws Exception {
        return repository.save(patientTreatment);
    }

    @Override
    @Transactional
    public void delete(PatientTreatment patientTreatment) throws Exception {

            repository.delete(patientTreatment);

    }

    @Override
    public ActionResponse<PatientTreatment> create(PatientTreatment treatment) {
        ActionResponse<PatientTreatment> response = new ActionResponse<>();
        List<PatientTreatment> treatments =getByExaminationAndTreatment(treatment.getExamination(),treatment.getTreatment());
        if(treatments!=null){

          PatientTreatment tr =treatments.stream().filter(t->t.getResult().equals(PATIENT_TREATMENT_RESULT.IN_PROGRESS)).findFirst().orElse(null);

          if(tr!=null) {
              response.setMessage("Previous treatment for the Checkup is in progress");
              response.setResult(false);
              return response;
          }
        }

        try {
            repository.save(treatment);
            response.setMessage("valid");
            response.setResult(true);
            response.setT(treatment);
        }
        catch (Exception e)
        {
            response.setMessage("DB Exception");
            response.setResult(false);
            response.setT(treatment);
        }
        return response;
    }

    @Override
    public List<PatientTreatment> getByExaminationAndTreatment(Examination exam, Treatment treatment) {
        return  repository.findByExaminationAndTreatment(exam,treatment);
    }

    @Override
    public List<PatientTreatment> getByExamination(Examination examination) {
        return repository.findByExamination(examination);
    }

    @Override
    public Page<PatientTreatment> searchByQuery(PatientTreatmentSearchQuery query, Pageable pageable) {
        return repository.searchByQuery(query.getPatientId(),query.getExaminerId(),query.getTreatmentId(),
                query.getType(),query.getCategory(),
                query.getActive(),query.getResult(),pageable);
    }

    @Override
    @Transactional
    public PatientTreatment updateStatus(Long id) {
        PatientTreatment tr =get(id);

        if(tr !=null){
            tr.setActive(true);
            return repository.save(tr);
        }
        return null;
    }

    @Override
    public PatientTreatment updateStart(Long id) {
        PatientTreatment tr =get(id);

        if(tr !=null){
            tr.setStarted(true);
            tr.setStartedDate(new Date());
            return repository.save(tr);
        }
        return null;
    }

    @Override
    @Transactional
    public PatientTreatment updateResult(Long id, PATIENT_TREATMENT_RESULT result) {
        PatientTreatment tr =get(id);

        if(tr!=null && tr.getResult()!=result){
            tr.setResult(result);

            if(result.equals(PATIENT_TREATMENT_RESULT.COMPLETED))
                tr.setCompletedDate(new Date());

            return repository.save(tr);
        }
        return null;
    }

    @Override
    public Page<PatientTreatment> getAllByExaminerAndStatus(Long staffId, PATIENT_TREATMENT_RESULT result) {
        Pageable pageable= PageRequest.of(0,Constants.PAGE_FOR_ALL);
        return repository.searchByExaminerAndStatus(staffId,result,pageable);
    }

    @Override
    @Transactional
    public ActionResponse<PatientTreatment> createMultiple(List<PatientTreatment> treatments) {
        ActionResponse<PatientTreatment> result =new ActionResponse<>();
        result.setMessage(" ");

        if(treatments!=null){
            ActionResponse<PatientTreatment> createResponse =new ActionResponse<>();
            Boolean totalResult =true;

            for(PatientTreatment tr:treatments){
                  createResponse= create(tr);
                  if(!createResponse.getResult()){
                      result.setMessage(result.getMessage()+","+createResponse.getMessage());
                      totalResult =false;
                   }

            }
            result.setResult(totalResult);
            return result;
        }
        return null;
    }
}
