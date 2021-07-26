package com.therapy.therapy.user;


import com.therapy.therapy.common.CRUDService;

public interface UserEntityService extends CRUDService<UserEntity> {
    UserEntity getByStaffId(String staffId);

}
