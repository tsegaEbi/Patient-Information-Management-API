package com.therapy.therapy.user.roles;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class RoleDTO {
    private Long id;
    private String name;

    public Role toRole(RoleDTO dto){

        Role role = new Role();
        role.setName(dto.getName());
        role.setId(dto.getId());
        return role;
    }

    public static RoleDTO toDto(Role role){

        RoleDTO dto = new RoleDTO();
        dto.setName(role.getName());
        dto.setId(role.getId());
        return dto;
    }
}
