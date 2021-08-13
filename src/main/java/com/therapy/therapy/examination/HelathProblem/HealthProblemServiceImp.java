package com.therapy.therapy.examination.HelathProblem;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.icdCode.Icd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HealthProblemServiceImp implements HealthProblemService{
    private final HealthProblemRepository repository;



    public HealthProblemServiceImp(HealthProblemRepository healthProblemRepository){
        this.repository=healthProblemRepository;
    }
    @Override
    public HealthProblem get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<HealthProblem> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public HealthProblem add(HealthProblem healthProblem)  {
        return repository.save(healthProblem);
    }

    @Override
    public HealthProblem update(HealthProblem healthProblem) throws Exception {
        return null;
    }

    @Override
    public void delete(HealthProblem healthProblem) throws Exception {

    }

    @Override
    public List<HealthProblem> getByExaminationAndIcd(Examination examination, Icd icd) {

        if(examination!=null && icd!=null){
            return repository.findByExaminationAndIcd(examination,icd);
        }
        return null;
    }

    @Override
    @Transactional
    public ActionResponse<HealthProblem> create(HealthProblem problem) {

        ActionResponse<HealthProblem> response = new ActionResponse<>();

        if(getByExaminationAndIcd(problem.getExamination(),problem.getIcd())!=null || getByExaminationAndIcd(problem.getExamination(),problem.getIcd()).size()!=0){
            response.setMessage("The Problem already reported for this examination");
            response.setResult(false);
            response.setT(problem);
            return response;
        }
        try{
            repository.save(problem);
            response.setT(problem);
            response.setResult(true);
            response.setMessage("successful");
        }catch(Exception e){
            response.setMessage("Unexpected exception");
            response.setResult(false);

        }
        return response;
    }

    @Override
    public List<HealthProblem> getByExamination(Examination examination) {
        return repository.findByExamination(examination);
    }

    @Override
    public List<HealthProblem> getByPatient(Long patientId) {
        return repository.getByPatientId(patientId);
    }

    @Override
    public Page<HealthProblem> searchByQuery(HealthProblemSearchQuery query, Pageable pageable) {
        return repository.searchByQuery(query.getIcdId(),query.getIcdSubCategory(),query.getCategory(),query.getStatus(),pageable);
    }
}
