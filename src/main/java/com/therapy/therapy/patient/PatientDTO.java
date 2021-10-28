package com.therapy.therapy.patient;

import com.therapy.therapy.common.BLOOD_TYPE;
import com.therapy.therapy.common.Nationality;
import com.therapy.therapy.common.Regions;
import com.therapy.therapy.common.Sex;
import com.therapy.therapy.patient.checkup.PatientVisit;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter

public class PatientDTO {
    private Long id;
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

    private Date dateCreated;
    private List<PatientVisit> visits;

     private int age;

    public static Patient patient(PatientDTO patient){
        if(patient==null)
            return null;

        Patient dto = new Patient ();
        dto.setAddress(patient.getAddress());
        dto.setBloodType(patient.getBloodType());

        dto.setCountry(patient.getCountry());
        dto.setCountryOfBirth(patient.getCountryOfBirth());
        dto.setNationality(patient.getNationality());

        dto.setDob(patient.getDob());
        dto.setEmail(patient.getEmail());
        dto.setMobile(patient.getMobile());
        dto.setEmergencyContactEmail(patient.getEmergencyContactEmail());
        dto.setEmergencyContactName(patient.getEmergencyContactName());
        dto.setEmergencyContactMobile(patient.getEmergencyContactMobile());

        dto.setFirstName(patient.getFirstName());
        dto.setFatherName(patient.getFatherName());
        dto.setLastName(patient.getLastName());
        dto.setSex(patient.getSex());
        dto.setId(patient.getId());
        dto.setMotherName(patient.getMotherName());

        dto.setPrefix(patient.getPrefix());
        dto.setRegions(patient.getRegions());

        dto.setAge(patient.getAge());
        return dto;
    }
    public static PatientDTO patientDTO(Patient patient){
        if(patient==null)
            return null;

        PatientDTO dto = new PatientDTO();
        dto.setAddress(patient.getAddress());
        dto.setBloodType(patient.getBloodType());
        dto.setCountry(patient.getCountry());
        dto.setCountryOfBirth(patient.getCountryOfBirth());
        dto.setNationality(patient.getNationality());

        dto.setDob(patient.getDob());
        dto.setEmail(patient.getEmail());
        dto.setMobile(patient.getMobile());

        dto.setEmergencyContactEmail(patient.getEmergencyContactEmail());
        dto.setEmergencyContactName(patient.getEmergencyContactName());
        dto.setEmergencyContactMobile(patient.getEmergencyContactMobile());

        dto.setFirstName(patient.getFirstName());
        dto.setFatherName(patient.getFatherName());
        dto.setLastName(patient.getLastName());
        dto.setSex(patient.getSex());
        dto.setId(patient.getId());
        dto.setMotherName(patient.getMotherName());
        dto.setPrefix(patient.getPrefix());
        dto.setRegions(patient.getRegions());

        dto.setDateCreated(patient.getDateCreated());
        dto.setAge(patient.getAge());

        return dto;
    }
    public static Page<PatientDTO> toList(Page<Patient> patients){

        return patients.map(t->patientDTO(t));
    }
    public static List<PatientDTO> toList(List<Patient> patients){
        return patients.stream().map(t->patientDTO(t)).collect(Collectors.toList());
    }

    public static Patient toPatientForUpdate(Patient patient, Patient dto ){

      if(dto.getPrefix()!=null){

          patient.setPrefix(dto.getPrefix());

        }
        if(dto.getAddress()!=null){

            patient.setAddress(dto.getAddress());

        }
        if(dto.getDob()!=null){

            patient.setDob(dto.getDob());

        }
        if(dto.getEmail()!=null){
            patient.setEmail(dto.getEmail());
        }
        if(dto.getMobile()!=null){
            patient.setMobile(dto.getMobile());
        }

        if(dto.getFirstName()!=null){
            patient.setFirstName(dto.getFirstName());
        }
        if(dto.getFatherName()!=null){
            patient.setFatherName(dto.getFatherName());
        }
        if(dto.getLastName()!=null){
            patient.setLastName(dto.getLastName());
        }
        if(dto.getSex()!=null){
           patient.setSex(dto.getSex());
        }
        if(dto.getPrefix()!=null){
            patient.setPrefix(dto.getPrefix());
        }
        if(dto.getBloodType()!=null){
            patient.setBloodType(dto.getBloodType());
        }


        if(dto.getCountry()!=null){
            patient.setCountry(dto.getCountry());
        }
        if(dto.getCountryOfBirth()!=null){
            patient.setCountryOfBirth(dto.getCountryOfBirth());
        }
        if(dto.getNationality()!=null){
            patient.setNationality(dto.getNationality());
        }

        if(dto.getEmergencyContactEmail()!=null){
            patient.setEmergencyContactEmail(dto.getEmergencyContactEmail());
        }
        if(dto.getEmergencyContactName()!=null){
            patient.setEmergencyContactName(dto.getEmergencyContactName());
        }
        if(dto.getEmergencyContactMobile()!=null){
            patient.setEmergencyContactMobile(dto.getEmergencyContactMobile());
        }
        if(dto.getAge()>0){
            patient.setAge(dto.getAge());
        }
        return patient;
    }
}
