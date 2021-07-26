package com.therapy.therapy.user.roles.userRole;

import com.therapy.therapy.user.UserEntity;
import com.therapy.therapy.user.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImp implements UserRoleService {
    private final UserRoleRepository repository;

    @Autowired
    private UserEntityService userEntityService;

    public UserRoleServiceImp(UserRoleRepository repository){
        this.repository =repository;
    }
    @Override
    public List<UserRole> getByUserEntity(UserEntity user) {
        return repository.findByUser(user);
    }

    @Override
    public UserRole get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<UserRole> list(Pageable pageable) {
        return null;
    }

    @Override
    public UserRole add(UserRole userRole) throws Exception {
        return null;
    }

    @Override
    public UserRole update(UserRole userRole) {
        return null;
    }

    @Override
    public void delete(UserRole userRole) throws Exception {

    }

     @Override
    public List<UserRole> getByUserId(Long userId){
          UserEntity user =userEntityService.get(userId);

          if(user!=null){
              return  repository.findByUser(user);
          }

          return null;
     }

}
