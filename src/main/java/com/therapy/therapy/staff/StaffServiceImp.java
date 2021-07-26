package com.therapy.therapy.staff;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.STATUS;
import com.therapy.therapy.common.Validation;
import com.therapy.therapy.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class StaffServiceImp implements StaffService {
    private final StaffRepository repository;
    @Autowired
    private DepartmentService departmentService;

    public StaffServiceImp(StaffRepository repository){
        this.repository =repository;
    }

    @Override
    public List<Staff> findByDepartmentActive(Long departmentId){
        return repository.findByDepartmentIdAndStatus(departmentId,STATUS.ACTIVE);
    }
    @Override
    public Page<Staff> searchByKey(String key,Pageable pageable){

        return repository.searchByKey(key,pageable);
    }
    @Override
    public Page<Staff> searchByQuery(StaffSearchQuery query,Pageable pageable){
        return repository.searchByQuery(query.getDepartmentId(),query.getStatus(),query.getQualification(),query.getEmployment(),query.getSex(), pageable);
    }
    @Override
    public Page<Staff> findAll(Pageable pageable){
        return repository.findByStatus(STATUS.ACTIVE, pageable);
    }
    @Override
    @Transactional
    public ActionResponse<Staff> create(Staff staff){
        Validation validate =validateNew(staff);
        ActionResponse<Staff> result =new ActionResponse<>();
        result.setMessage(validate.getMessage());
        result.setResult(validate.isStatus());


        if(validate.isStatus())
        {
            try{
                 repository.save(staff);
                 result.setT(staff);
            }catch (Exception e){
                result.setResult(false);
                result.setMessage(e.getMessage());
            }
        }
        return result;
    }
    @Override
    public Staff get(Long id) {
        if(id!=null)
        return repository.findById(id).orElse(null);

        return null;
    }

    @Override
    public Page<Staff> list(Pageable pageable) {
        return null;
    }

    @Override
    @Transactional
    public Staff add(Staff staff) throws Exception {

         return null;
    }

    @Override
    public Staff update(Staff staff) {
        return null;
    }

    private Validation validateNew(Staff staff){
        Validation validate = new Validation();
        validate.setMessage("validate");
        validate.setStatus(true);

         if(getByStaffId(staff.getStaffId())!=null){

             validate.setStatus(false);
             validate.setMessage("Employee ID Exists");
         }

         return validate;
    }
    private Validation validateUpdate(Staff staff){
        Validation validate = new Validation();
        validate.setMessage("validate");
        validate.setStatus(true);

        return validate;
    }

    @Override
    public Staff getByStaffId(String staffId){
        return repository.findByStaffId(staffId.toUpperCase());
    }

    @Override
    @Transactional
    public ActionResponse<Staff> updateStaff(Staff dto) {
        Validation validate =validateUpdate(dto);
        ActionResponse<Staff> result =new ActionResponse<>();
        result.setMessage(validate.getMessage());
        result.setResult(validate.isStatus());

        Staff nStaff = get(dto.getId());
        if(nStaff!=null) {
             nStaff = StaffDTO.toStaffForUpdate(nStaff,dto);
                    if (validate.isStatus()) {
                        try {
                            repository.save(nStaff);
                            result.setT(nStaff);
                        } catch (Exception e) {
                            result.setResult(false);
                            result.setMessage(e.getMessage());
                        }
                    }
        }
        else{
            result.setMessage("Unknown Staff ID");
            result.setResult(false);
        }
        return result;
    }

    @Override
    public void delete(Staff staff) {

    }
}
