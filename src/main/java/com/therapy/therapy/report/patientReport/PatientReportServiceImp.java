package com.therapy.therapy.report.patientReport;

import com.therapy.therapy.patient.PatientService;
import com.therapy.therapy.support.dateTimeSupport.DateTimeSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PatientReportServiceImp implements PatientReportService{
    @Autowired
    private PatientService patientService;

    @Autowired
    private DateTimeSupportService dateTimeSupportService;

    @Override
    public Integer getByQuery() {
        return null;
    }

    @Override
    public List<PatientIntervalReport> getPatientByInterval(PatientIntervalReportRequest request) {

        List<PatientIntervalReport> reports = new ArrayList();
        REPORT_INTERVAL_BY interval =request.getInterval();

        int size =request.getSize();
        if(interval.equals(REPORT_INTERVAL_BY.ALL)){
             PatientIntervalReport rep = new PatientIntervalReport();
             rep.setEndDate(new Date());
             rep.setStartDate(new Date());
             rep.setLabel("ALL");
             rep.setSize(patientService.countAll());
             reports.add(rep);
             return reports;
        }

        Date startDate=request.getStart();

        Date endDate =request.getEnd();

        for(int i=1; i<=size;i++){

            if(interval.equals(REPORT_INTERVAL_BY.WEEK)){


                endDate= dateTimeSupportService.getWeekBefore(startDate);
                PatientIntervalReport patientIntervalReport =new PatientIntervalReport();
                patientIntervalReport.setSize(patientService.countByInterval(startDate,endDate));
                patientIntervalReport.setStartDate(startDate);
                patientIntervalReport.setEndDate(endDate);
                patientIntervalReport.setInterval(interval);
                patientIntervalReport.setLabel("WEEK "+i);

                reports.add(patientIntervalReport);

                //
                startDate=endDate;

            }

        }
        return reports;
    }
}
