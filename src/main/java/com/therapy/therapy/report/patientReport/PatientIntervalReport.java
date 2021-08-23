package com.therapy.therapy.report.patientReport;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PatientIntervalReport {
    private Date startDate;
    private Date endDate;
    private int size;
    private String label;
    private REPORT_INTERVAL_BY interval;
}
