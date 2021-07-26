package com.therapy.therapy.examination.labOrder.Image;

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
        return null;
    }

    @Override
    public Page<LabOrderResultImage> list(Pageable pageable) {
        return null;
    }

    @Override
    public LabOrderResultImage add(LabOrderResultImage labOrderResultImage) throws Exception {
        return null;
    }

    @Override
    public LabOrderResultImage update(LabOrderResultImage labOrderResultImage) throws Exception {
        return null;
    }

    @Override
    public void delete(LabOrderResultImage labOrderResultImage) throws Exception {

    }

    @Override
    public List<LabOrderResultImage> getByLabOrder(Long labOrderId) {
        return null;
    }

    @Override
    public List<LabOrderResultImage> getByExamination(Long examinationId) {
        return null;
    }
}
