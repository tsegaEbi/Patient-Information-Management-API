package com.therapy.therapy.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEntityCreateDTO {

    private String  staffId;
    private String password;

    public static UserEntity toUserEntity(UserEntityCreateDTO dto){

        UserEntity entity = new UserEntity();

        entity.setEnabled(true);
        entity.setPassword(dto.getPassword());
        entity.setUserName(dto.getStaffId().toUpperCase());
        return entity;
   }
}
