package com.therapy.therapy.user;

import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserDTO {
    private String staffFullName;
    private String  staffId;
    private Long id;
    private Boolean enabled;

    public UserDTO userDTO(Staff staff, UserEntity user){

        UserDTO dto = new UserDTO();

        dto.setEnabled(user.getEnabled());
        dto.setId(user.getId());
        dto.setStaffFullName(staff.getPrefix()+" "+staff.getFirstName()+" "+staff.getFatherName());
        dto.setStaffId(user.getUserName());
        return dto;

    }
}
