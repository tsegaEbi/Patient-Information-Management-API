package com.therapy.therapy.report;

import com.therapy.therapy.report.patientReport.PatientIntervalReport;
import com.therapy.therapy.report.patientReport.PatientIntervalReportRequest;
import com.therapy.therapy.report.patientReport.PatientReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private PatientReportService patientReportService;


    @PostMapping("/patient/interval")
    public List<PatientIntervalReport> getReport(@RequestBody PatientIntervalReportRequest reportRequest){

        return patientReportService.getPatientByInterval(reportRequest);

    }
}
