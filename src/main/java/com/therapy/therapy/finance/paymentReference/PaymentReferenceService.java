package com.therapy.therapy.finance.paymentReference;

import com.therapy.therapy.common.CRUDService;
import com.therapy.therapy.finance.serviceItem.ServiceItem;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackage;
import com.therapy.therapy.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentReferenceService extends CRUDService<PaymentReference> {

    List<PaymentReference> getReferenceByPatient(Patient patient);
    Page<PaymentReference> getReferenceByPackage(ServicePackage servicePackage, Pageable pageable);
    Page<PaymentReference> getReferenceByServiceItem(ServiceItem item, Pageable pageable);
    Page<PaymentReference> getReferenceByPaid(Boolean status,Pageable pageable);

    List<PaymentReference> getByRefereeIdAndCode(Long refereeId,PAYMENT_REFERENCE_CODE code);

    PaymentReferenceDTO getDetail(PaymentReference paymentReference);

    Page<PaymentReference> searchByQuery(Long serviceId, String key, Boolean paid,Pageable pageable);

    List<PaymentReference> searchByCardNumber(Long cardNumber);

    Page<PaymentReference> searchByServiceId(Long serviceId, Pageable pageable);

}
