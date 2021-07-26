package com.therapy.therapy.staff;

import com.therapy.therapy.common.EMPLOYMENT;
import com.therapy.therapy.common.QUALIFICATION;
import com.therapy.therapy.common.STATUS;
import com.therapy.therapy.common.Sex;
import com.therapy.therapy.department.Department;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter

public class StaffDTO {
    private Long id;
    private String prefix;
    private String staffId;
    private String firstName;

    private String fatherName;
    private String lastName;
    private Sex sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    private String department;
    private Long departmentId;

    private String email;

    private String mobile;
    private String address;
    private String profession;
    private EMPLOYMENT employment;

    private QUALIFICATION qualification;
    private STATUS status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinedDate;
    private Date terminatedDate;

    public static StaffDTO toDTO(Staff dto)
    {
        StaffDTO staff = new StaffDTO();
        if(dto==null)
            return null;

        staff.setId(dto.getId());
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
        staff.setStatus(dto.getStatus());
        staff.setJoinedDate(dto.getJoinedDate());
        staff.setTerminatedDate(dto.getTerminatedDate());
        staff.setDepartment(dto.getDepartment().getName());
        staff.setDepartmentId(dto.getDepartment().getId());
        System.out.println(dto.getDepartment().getName());

        return staff;
    }
    public static Staff  toStaff(StaffDTO dto)
    {
        Staff staff = new Staff();

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
        staff.setStatus(dto.getStatus());
        staff.setJoinedDate(dto.getJoinedDate());
        staff.setTerminatedDate(dto.getTerminatedDate());

        return staff;
    }
    public static Staff  toStaff(StaffDTO dto, Department department)
    {
        Staff staff = new Staff();

        staff.setId(dto.getId());
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
        staff.setStatus(dto.getStatus());
        staff.setJoinedDate(dto.getJoinedDate());

        if(department!=null){
            staff.setDepartment(department);
        }

        return staff;
    }
  public static Staff toStaffForUpdate(Staff staff, Staff dto ){

      if(dto.getStaffId()!=null){
          staff.setStaffId(dto.getStaffId());
      }
      if(dto.getDepartment()!=null){
            staff.setDepartment(dto.getDepartment());
      }
      if(dto.getAddress()!=null){
          staff.setAddress(dto.getAddress());
      }
      if(dto.getDob()!=null){
          staff.setDob(dto.getDob());
      }
      if(dto.getEmail()!=null){
          staff.setEmail(dto.getEmail());
      }
      if(dto.getMobile()!=null){
          staff.setMobile(dto.getMobile());
      }
      if(dto.getEmployment()!=null){
          staff.setEmployment(dto.getEmployment());
      }
      if(dto.getStatus()!=null)
      {
          staff.setStatus(dto.getStatus());
      }
      if(dto.getFirstName()!=null){
          staff.setFirstName(dto.getFirstName());
      }
      if(dto.getFatherName()!=null){
          staff.setFatherName(dto.getFatherName());
      }
      if(dto.getLastName()!=null){
          staff.setLastName(dto.getLastName());
      }
      if(dto.getSex()!=null){
          staff.setSex(dto.getSex());
      }
      if(dto.getPrefix()!=null){
          staff.setPrefix(dto.getPrefix());
      }
      if(dto.getProfession()!=null){
          staff.setProfession(dto.getProfession());
      }
      if(dto.getJoinedDate()!=null){
          staff.setJoinedDate(dto.getJoinedDate());
      }
      return staff;
  }
    public static Page<StaffDTO> toList(Page<Staff> staffs){

        return staffs.map(t->toDTO(t));
    }
    public static List<StaffDTO> toList(List<Staff> staffs){

        return staffs.stream().map(t->toDTO(t)).collect(Collectors.toList());
    }
}
