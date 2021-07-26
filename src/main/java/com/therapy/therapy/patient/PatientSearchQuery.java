package com.therapy.therapy.patient;

import com.therapy.therapy.common.Nationality;
import com.therapy.therapy.common.Regions;
import com.therapy.therapy.common.Sex;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PatientSearchQuery {
    private String value;
    private Sex sex;
    private Regions region;
    private Nationality nationality;
    private int pageNumber;

}
