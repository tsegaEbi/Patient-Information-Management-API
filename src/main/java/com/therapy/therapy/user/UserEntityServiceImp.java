package com.therapy.therapy.user;

import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceImp implements UserEntityService {
    private final UserEntityRepository repository;


    private final StaffRepository staffRepository;

    public UserEntityServiceImp(UserEntityRepository repository, StaffRepository staffRepository ){
        this.repository =repository;
        this.staffRepository =staffRepository;
    }
    @Override
    public UserEntity getByStaffId(String staffId) {

        return repository.findByUserNameContainingIgnoreCase(staffId);

    }

    @Override
    public UserEntity get(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<UserEntity> list(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public UserEntity add(UserEntity userEntity) throws Exception {




        if(getByStaffId(userEntity.getUserName())!=null)
            throw new IllegalArgumentException("Staff is already registered="+userEntity.getUserName());
        if(userEntity.getPassword()==null || userEntity.getPassword().length()<2)
            throw new IllegalArgumentException("Password size is too small");
        if(userEntity.getUserName()==null || userEntity.getUserName().length()<1)
            throw new IllegalArgumentException("Staff Id is invalid");

        Staff staff= staffRepository.findByStaffId(userEntity.getUserName());
        if(staff==null)
            throw new IllegalArgumentException("Staff not find with StaffId="+userEntity.getUserName());

        return repository.save(userEntity);


    }

    @Override
    public UserEntity update(UserEntity userEntity) {

        return repository.save(userEntity);

    }

    @Override
    public void delete(UserEntity userEntity) {

    }
}
