package com.therapy.therapy.user.roles.userRole;

import com.therapy.therapy.user.UserEntity;
import com.therapy.therapy.user.UserEntityService;
import com.therapy.therapy.user.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        return repository.findAll(pageable);
    }

    @Override
    public UserRole add(UserRole userRole) throws Exception {
        return repository.save(userRole);
    }

    @Override
    public UserRole update(UserRole userRole) {
        return null;
    }

    @Override
    public void delete(UserRole userRole) throws Exception {
                    repository.delete(userRole);
    }

     @Override
    public List<UserRole> getByUserId(Long userId){
          UserEntity user =userEntityService.get(userId);

          if(user!=null){
              return  repository.findByUser(user);
          }

          return null;
     }

    @Override
    @Transactional
    public List<UserRole> addRoles(UserEntity user, List<Role> roles) throws Exception {
        List<UserRole> addRoles =new ArrayList<>();
        for(Role role:roles){

            UserRole ur = new UserRole();
            ur.setRole(role);
            ur.setUser(user);
            addRoles.add(add(ur));

       }
        return addRoles;
    }

}
