package com.therapy.therapy.user;

import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<UserEntity> listAll() {
        return repository.findAll();
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



        return repository.save(userEntity);


    }

    @Override
    public UserEntity update(UserEntity userEntity) {

        return repository.save(userEntity);

    }

    @Override
    public void delete(UserEntity userEntity) {
        repository.delete(userEntity);
    }
}
