package com.therapy.therapy.patient;

import com.therapy.therapy.common.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name="patient")
public class Patient extends Model {

    private String prefix;

    private String firstName;
    private String fatherName;
    private String lastName;

    private String motherName;

    private String emergencyContactName;
    private String emergencyContactMobile;
    private String emergencyContactEmail;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private Date dob;

    @Enumerated(EnumType.STRING)
    private BLOOD_TYPE bloodType;

    private String placeOfBirth;

    @Enumerated(EnumType.STRING)
    private Nationality countryOfBirth;

    private String address;
    private String mobile;
    private String email;

    @Enumerated(EnumType.STRING)
    private Regions regions;

    @Enumerated(EnumType.STRING)
    private Nationality country;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;



}
