package com.therapy.therapy.user.roles.userRole;

import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.user.UserEntity;

import java.util.List;


public interface UserRoleService  extends CRUDService<UserRole> {

    List<UserRole> getByUserEntity(UserEntity user);

    List<UserRole> getByUserId(Long userId);
}
