package com.therapy.therapy.staff;

import com.therapy.therapy.common.*;
import com.therapy.therapy.department.Department;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter

public class StaffCreateDTO {

    private String prefix;
    private String staffId;
    private String firstName;
    private String fatherName;
    private String lastName;

    private Sex sex;

    private Date dob;

    private String email;
    private String mobile;
    private String address;

    private String profession;


    private EMPLOYMENT employment;

    private QUALIFICATION qualification;

    private Date joinedDate;

    private Date terminatedDate;

    private Long departmentId;

    public static Staff toStaff(StaffCreateDTO dto, Department d){

        Staff staff = new Staff();
        staff.setStaffId(dto.getStaffId());
        staff.setFirstName(dto.getFirstName());
        staff.setFatherName(dto.getFatherName());
        staff.setLastName(dto.getLastName());
        staff.setSex(dto.getSex());
        staff.setDob(dto.getDob());
        staff.setEmail(dto.getEmail());
        staff.setMobile(dto.getMobile());
        staff.setAddress(dto.getAddress());

        staff.setPrefix(dto.getPrefix());
        staff.setProfession(dto.getProfession());
        staff.setEmployment(dto.getEmployment());
        staff.setQualification(dto.getQualification());
        staff.setJoinedDate(dto.getJoinedDate());
        staff.setDepartment(d);


        return staff;
    }
}
