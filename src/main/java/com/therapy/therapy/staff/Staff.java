package com.therapy.therapy.staff;

import com.therapy.therapy.common.*;
import com.therapy.therapy.department.Department;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="staff")
public class Staff extends Model {

    private String prefix;
    private String staffId;
    private String firstName;
    private String fatherName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Date dob;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;


    private String email;
    private String mobile;
    private String address;

    private String profession;
    @Enumerated(EnumType.STRING)
    private EMPLOYMENT employment;

    @Enumerated(EnumType.STRING)
    private QUALIFICATION qualification;

    @Enumerated(EnumType.STRING)
    private STATUS status=STATUS.ACTIVE;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date terminatedDate= new Date();
}
