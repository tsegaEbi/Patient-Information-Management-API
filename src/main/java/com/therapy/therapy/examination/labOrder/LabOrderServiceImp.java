package com.therapy.therapy.examination.labOrder;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.examination.ExaminationRepository;
import com.therapy.therapy.laboratory.Laboratory;
import com.therapy.therapy.staff.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LabOrderServiceImp implements LabOrderService {
    private final LabOrderRepository repository;
    private final ExaminationRepository examinationRepository;


    public LabOrderServiceImp(LabOrderRepository repository, ExaminationRepository examinationRepository){

        this.repository =repository;
        this.examinationRepository =examinationRepository;
    }

    @Override
    public List<LabOrder> getByExamination(Examination examination) {

        return repository.findAllByExamination(examination);

    }

    @Override
    public Page<LabOrder> getByTechnician(Staff staff,Pageable pageable) {
        return repository.findByTechnician(staff.getId(),pageable);
    }

    @Override
    public Page<LabOrder> getByExaminer(Staff staff, Pageable pageable) {
        return repository.findByExaminer(staff.getId(),pageable);
    }

    @Override
    public ActionResponse<LabOrder> create(Examination exam, List<Laboratory> laboratories) {

        ActionResponse<LabOrder> response = new ActionResponse<>();
        response.setMessage("valid");
        response.setResult(true);

        Collection<LabOrder> orders= new ArrayList();
        for(Laboratory l: laboratories) {
              LabOrder order =new LabOrder();
              order.setActive(false);
              order.setExamination(exam);
              order.setLaboratory(l);
              orders.add(order);
        }

        if(orders!=null && orders.size()>0){
            repository.saveAll(orders);
        }

        return response;
    }

    @Override
    public LabOrder get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<LabOrder> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public LabOrder add(LabOrder labOrder) throws Exception {
        return null;
    }

    @Override
    public LabOrder update(LabOrder labOrder) throws Exception {
        return repository.save(labOrder);
    }

    @Override
    public void delete(LabOrder labOrder) throws Exception {
        if(!labOrder.getActive())
          repository.delete(labOrder);
    }
    @Override
    @Transactional
    public void activate(Long id) {

        LabOrder lab = get(id);
        lab.setActive(true);
        repository.save(lab);
    }

    @Override
    public LabOrder assignTechnician(Long id, Long staffId) {
        LabOrder order=get(id);
        order.setTechnician(staffId);
        return repository.save(order);
    }

    @Override
    public Page<LabOrder> getByQuery(LabOrderSearchQuery query, Pageable pageable) {

        if(query.getExaminationId()!=null)
            return repository.findByExamination(query.getExaminationId(),pageable);

        if(query.getPatientId()!=null){
            return repository.findByPatientId(query.getPatientId(),pageable);
        }

        return repository.searchByQuery(query.getExaminerId(),query.getTechnicianId(),query.getOrderStatus(),
                query.getActive(),query.getLaboratoryId(),pageable);
    }

    @Override
    public Page<LabOrder> getAllByExaminersAndStatus(Long staffId, ORDER_STATUS status) {
        Pageable pageable = PageRequest.of(0, Constants.PAGE_FOR_ALL);
        return repository.getByExaminerAndStatus(staffId, status,pageable);
    }


}
