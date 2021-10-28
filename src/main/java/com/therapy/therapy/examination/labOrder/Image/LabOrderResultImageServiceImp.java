package com.therapy.therapy.examination.labOrder.Image;

import com.therapy.therapy.examination.labOrder.LabOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabOrderResultImageServiceImp implements LabOrderResultImageService {
    private final LabOrderResultImageRepository repository;

    public LabOrderResultImageServiceImp(LabOrderResultImageRepository repository){
        this.repository =repository;
    }
    @Override
    public LabOrderResultImage get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<LabOrderResultImage> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public LabOrderResultImage add(LabOrderResultImage labOrderResultImage) throws Exception {
        return repository.save(labOrderResultImage);
    }

    @Override
    public LabOrderResultImage update(LabOrderResultImage labOrderResultImage) throws Exception {
        return null;
    }

    @Override
    public void delete(LabOrderResultImage labOrderResultImage) throws Exception {

    }

    @Override
    public List<LabOrderResultImage> getByLabOrder(LabOrder labOrder) {
        return repository.findByLabOrder(labOrder.getId() );
    }

    @Override
    public List<LabOrderResultImage> getByExamination(Long examinationId) {
        return repository.findByExamination(examinationId);
    }

    @Override
    public List<LabOrderResultImage> findByLabOrder(LabOrder labOrder) {
        return repository.findByLabOrder(labOrder.getId());
    }

    @Override
    public List<LabOrderResultImage> findByPatient(Long patientId) {
        return repository.findByPatient(patientId);
    }

    @Override
    public List<LabOrderResultImage> findByVisit(Long visitId) {
        return null;
    }
}
