package com.therapy.therapy.examination;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.Validation;
import com.therapy.therapy.examination.labOrder.LabOrderService;
import com.therapy.therapy.laboratory.Laboratory;
import com.therapy.therapy.laboratory.LaboratoryService;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.patient.checkup.PatientVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service

public class ExaminationServiceImp implements ExaminationService{
    private final ExaminationRepository repository;

    @Autowired
    private LabOrderService labOrderService;

    @Autowired
    private PatientVisitService visitService;

    @Autowired
    private LaboratoryService labService;

    public ExaminationServiceImp(ExaminationRepository repository){
        this.repository =repository;
    }

    @Override
    public Examination get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Examination> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Examination add(Examination examination) throws Exception {
        return null;
    }

    @Override
    public Examination update(Examination examination) throws Exception {
        return repository.save(examination);
    }

    @Override
    public void delete(Examination examination) throws Exception {

    }

    @Override
    @Transactional
    public ActionResponse<Examination> create(Examination examination, List<Laboratory> labOrders) {
        ActionResponse<Examination>result = new ActionResponse<>();
        //only one examination is possible for each checkups/visits
        Examination prevExamination =repository.findByPatientVisit(examination.getPatientVisit());

        if(prevExamination!=null){
            result.setResult(false);
            result.setMessage("Already Examined, Please Edit");
            result.setT(prevExamination);
            return result;
        }

        List<Laboratory> labs = new ArrayList<>();
        for(Laboratory l: labOrders){
            labs.add(labService.get(l.getId()));
        }

        try{

            repository.save(examination);

            if(labOrders!=null)
            {
                labOrderService.create(examination,labs);
            }

            visitService.Examined(examination.getPatientVisit());

            result.setT(examination);

            result.setResult(true);

            result.setMessage("Successful");

        }
        catch(Exception e){

            result.setT(examination);
            result.setResult(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Examination> getAllByPatient(Long patientId) {

        return repository.findByPatientId(patientId);
    }

    @Override
    public Examination getByPatientVisit(PatientVisit visit) {
        return repository.findByPatientVisit(visit);
    }

    @Override
    @Transactional
    public void activate(Long id) {

        Examination examination = get(id);
        examination.setActive(true);
        repository.save(examination);
    }

    private Validation  validateNew(Examination exam){

         return null;
    }

}
