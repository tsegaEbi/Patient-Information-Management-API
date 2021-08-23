package com.therapy.therapy.patient;

import com.therapy.therapy.common.BLOOD_TYPE;
import com.therapy.therapy.common.Nationality;
import com.therapy.therapy.common.Regions;
import com.therapy.therapy.common.Sex;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class PatientCreateDTO {

    private String prefix;

    private String firstName;
    private String fatherName;
    private String lastName;

    private String motherName;

    private String emergencyContactName;
    private String emergencyContactMobile;
    private String emergencyContactEmail;

    private Sex sex;
    private Date dob;
    private BLOOD_TYPE bloodType;

    private String placeOfBirth;
    private Nationality countryOfBirth;

    private String address;
    private String mobile;
    private String email;
    private Regions regions;
    private Nationality country;
    private Nationality nationality;

    private Integer age;

    public static Patient  toPatient(PatientCreateDTO patient ){

        Patient dto = new Patient();
        dto.setAddress(patient.getAddress());
        dto.setBloodType(patient.getBloodType());
        dto.setCountry(patient.getCountry());
        dto.setPlaceOfBirth(patient.getPlaceOfBirth());
        dto.setCountryOfBirth(patient.getCountryOfBirth());
        dto.setDob(patient.getDob());
        dto.setNationality(patient.getNationality());

        dto.setEmail(patient.getEmail());
        dto.setMobile(patient.getMobile());
        dto.setEmergencyContactEmail(patient.getEmergencyContactEmail());
        dto.setEmergencyContactName(patient.getEmergencyContactName());
        dto.setEmergencyContactMobile(patient.getEmergencyContactMobile());

        dto.setFirstName(patient.getFirstName());
        dto.setFatherName(patient.getFatherName());
        dto.setLastName(patient.getLastName());
        dto.setSex(patient.getSex());
        dto.setMotherName(patient.getMotherName());
        dto.setPrefix(patient.getPrefix());
        dto.setRegions(patient.getRegions());

        dto.setAge(patient.getAge());
        return dto;
    }
}
