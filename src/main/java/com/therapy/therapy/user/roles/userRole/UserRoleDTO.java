package com.therapy.therapy.user.roles.userRole;

import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.user.UserEntity;
import com.therapy.therapy.user.UserEntityDTO;
import com.therapy.therapy.user.roles.RoleDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRoleDTO {

    private Long id;

    private RoleDTO role;

    private UserEntityDTO user;

    public static UserRoleDTO toDTO(UserRole role, Staff staff){
        UserRoleDTO dto = new UserRoleDTO();
        dto.setId(role.getId());
        dto.setRole(RoleDTO.toDto(role.getRole()));
        dto.setUser(UserEntityDTO.toDTO(role.getUser(),staff,null));
        return dto;
    }

    public static UserRoleDTO toDTOWithoutUser(UserRole role ){
        UserRoleDTO dto = new UserRoleDTO();
        dto.setId(role.getId());
        dto.setRole(RoleDTO.toDto(role.getRole()));
        return dto;
    }
}
