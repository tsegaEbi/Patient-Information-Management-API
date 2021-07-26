package com.therapy.therapy.finance.paymentReference;

import com.therapy.therapy.finance.serviceItem.ServiceItem;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackage;
import com.therapy.therapy.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentReferenceRepository extends JpaRepository<PaymentReference,Long> {

    List<PaymentReference> findByPatient(Patient patient);
    List<PaymentReference> findByPatientAndServicePackage(Patient patient, ServiceItem service);
    Page<PaymentReference> findByServicePackage(ServicePackage servicePackage, Pageable pageable);


    List<PaymentReference> findByRefereeIdAndCode(Long refereeId,PAYMENT_REFERENCE_CODE code);



    @Query("FROM PaymentReference p WHERE " +
            "(:serviceId is null or p.servicePackage.service.id= :serviceId) " +
            " and (:key is null  or upper(p.servicePackage.packageName) like upper(concat('%',:key,'%')))" +
            " and (:paid is null  or  p.paid =:paid)" +
            " and (:key is null or upper(p.servicePackage.service.name) like upper(concat('%',:key,'%')))" )
    Page<PaymentReference> searchByQuery(Long serviceId, String key,Boolean paid,Pageable pageable);


    @Query("FROM PaymentReference p WHERE " +
            "(:serviceId is null or p.servicePackage.service.id= :serviceId) " )
    Page<PaymentReference> searchByServiceID(Long serviceId, Pageable pageable);

    @Query("FROM PaymentReference p WHERE p.patient.id = :cardNumber")
    List<PaymentReference> searchByCardNumber(Long cardNumber);

}
