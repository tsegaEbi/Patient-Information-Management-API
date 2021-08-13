package com.therapy.therapy.user.roles.userRole;

import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.user.UserEntity;
import com.therapy.therapy.user.roles.Role;

import java.util.List;


public interface UserRoleService  extends CRUDService<UserRole> {

    List<UserRole> getByUserEntity(UserEntity user);

    List<UserRole> getByUserId(Long userId);

    List<UserRole> addRoles(UserEntity user,List<Role>roles) throws Exception;
}
