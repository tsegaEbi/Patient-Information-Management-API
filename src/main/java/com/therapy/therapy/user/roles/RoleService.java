package com.therapy.therapy.user.roles;

import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface RoleService extends CRUDService<Role> {
    List<Role> findAll();
}
