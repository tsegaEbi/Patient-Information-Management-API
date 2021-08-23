package com.therapy.therapy.report.patientReport;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class PatientIntervalReportRequest {

    private REPORT_INTERVAL_BY interval;

    private Date start;

    private Date end;

    private int size;

}
