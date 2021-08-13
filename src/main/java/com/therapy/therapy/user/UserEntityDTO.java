package com.therapy.therapy.user;

import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffDTO;
import com.therapy.therapy.user.roles.userRole.UserRole;
import com.therapy.therapy.user.roles.userRole.UserRoleDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class UserEntityDTO {
    private Long id;
    private Boolean enabled;
    private StaffDTO staff;

    private List<UserRoleDTO> roles;



    public static UserEntityDTO toDTO(UserEntity user, Staff staff, List<UserRole> roles){
        if(user==null || staff==null)
            return null;

        UserEntityDTO dto = new UserEntityDTO();
        dto.setEnabled(user.getEnabled());
        dto.setId(user.getId());
        dto.setStaff(StaffDTO.toDTO(staff));

        if(roles!=null)
            dto.setRoles(roles.stream().map(r->UserRoleDTO.toDTOWithoutUser(r)).collect(Collectors.toList()));
        return dto;
    }

}
