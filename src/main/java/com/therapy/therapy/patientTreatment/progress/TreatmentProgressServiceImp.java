package com.therapy.therapy.patientTreatment.progress;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.patientTreatment.PatientTreatment;
import com.therapy.therapy.patientTreatment.PatientTreatmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentProgressServiceImp implements TreatmentProgressService {

    private final TreatmentProgressRepository repository;

    private final PatientTreatmentRepository patientTreatmentRepository;

    public TreatmentProgressServiceImp(TreatmentProgressRepository repository,PatientTreatmentRepository patientTreatmentRepository){

        this.repository =repository;

        this.patientTreatmentRepository =patientTreatmentRepository;
    }

    @Override
    public TreatmentProgress get(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<TreatmentProgress> list(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public TreatmentProgress add(TreatmentProgress treatmentProgress) throws Exception {

        return repository.save(treatmentProgress);
    }

    @Override
    public TreatmentProgress update(TreatmentProgress treatmentProgress) throws Exception {

        return repository.save(treatmentProgress);
    }

    @Override
    public void delete(TreatmentProgress treatmentProgress) throws Exception {

    }

    @Override
    public List<TreatmentProgress> getByExamination(Long examinationId) {
        return repository.findByExamination(examinationId);
    }

    @Override
    public List<TreatmentProgress> getByPatientTreatment(PatientTreatment patientTreatment) {
        return repository.findByPatientTreatment(patientTreatment);
    }

    @Override
    public ActionResponse<TreatmentProgress> create(TreatmentProgress progress) {

        return null;
    }
}
