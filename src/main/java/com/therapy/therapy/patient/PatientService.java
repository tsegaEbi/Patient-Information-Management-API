package com.therapy.therapy.patient;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.common.Sex;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService extends CRUDService<Patient> {

    Page<Patient> findBySex(Sex sex, Pageable pageable);

    Page<Patient> findByKey(String key, Pageable pageable);

    Page<Patient> searchByQuery(PatientSearchQuery query,Pageable pageable);

    ActionResponse<Patient> updatePatient(Patient patient);

    ActionResponse<Patient>  createPatient(Patient patient);

    List<PaymentReferenceDTO> getCardPaymentReference(Long patientId);

    Page<Patient> getByCardNumber(Long cardNumber,Pageable pageable);

    int countAll();
    int countBySex();

}
