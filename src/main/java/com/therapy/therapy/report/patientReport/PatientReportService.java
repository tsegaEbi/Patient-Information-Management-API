package com.therapy.therapy.report.patientReport;

import java.util.List;

public interface PatientReportService {
    Integer getByQuery();

    List<PatientIntervalReport> getPatientByInterval(PatientIntervalReportRequest request);
}
