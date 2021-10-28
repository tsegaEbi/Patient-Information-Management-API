package com.therapy.therapy.patient;

import com.therapy.therapy.examination.ExaminationDTO;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceDTO;
import com.therapy.therapy.patient.checkup.PatientVisit;
import com.therapy.therapy.patient.checkup.PatientVisitDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class PatientDetailDTO {

    private PatientDTO patient;

    private List<ExaminationDTO> examinations;

    private List<PaymentReferenceDTO> paymentReferences;

    public static  PatientDetailDTO toDetailDTO(Patient patient, List<PaymentReferenceDTO> paymentReferences){

        PatientDetailDTO dto = new PatientDetailDTO();

        dto.setPatient(PatientDTO.patientDTO(patient));
        if(paymentReferences!=null)
        {
            dto.setPaymentReferences(paymentReferences);
        }
        return dto;
    }
}
