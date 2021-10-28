package com.therapy.therapy.user;


import com.therapy.therapy.common.CRUDService;

import java.util.List;

public interface UserEntityService extends CRUDService<UserEntity> {
    UserEntity getByStaffId(String staffId);

    List<UserEntity> listAll();
}
