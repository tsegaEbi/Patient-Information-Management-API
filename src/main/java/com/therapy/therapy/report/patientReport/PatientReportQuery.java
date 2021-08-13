package com.therapy.therapy.report.patientReport;

import com.therapy.therapy.common.Nationality;
import com.therapy.therapy.common.Regions;
import com.therapy.therapy.common.Sex;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class PatientReportQuery {

    private Date dateCreatedBefore;
    private Date dateCreatedAfter;

    private Sex sex;
    private Date dobBefore;
    private Date dobAfter;
    private Regions regions;
    private Nationality nationality;
    private Nationality countryOfBirth;


}
