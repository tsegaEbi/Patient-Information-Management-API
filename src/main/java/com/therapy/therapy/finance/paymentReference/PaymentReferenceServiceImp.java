package com.therapy.therapy.finance.paymentReference;

import com.therapy.therapy.finance.payment.Payment;
import com.therapy.therapy.finance.payment.PaymentRepository;
import com.therapy.therapy.finance.serviceItem.ServiceItem;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackage;
import com.therapy.therapy.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service

public class PaymentReferenceServiceImp implements PaymentReferenceService{
    private final PaymentReferenceRepository repository;
    private final PaymentRepository paymentRepository;


    public PaymentReferenceServiceImp(PaymentReferenceRepository referenceRepository, PaymentRepository paymentRepository){
        this.repository =referenceRepository;
        this.paymentRepository =paymentRepository;
    }
    @Override
    public List<PaymentReference> getByRefereeIdAndCode(Long refereeId,PAYMENT_REFERENCE_CODE code){

        return repository.findByRefereeIdAndCode(refereeId,code);
    }

    @Override
    public PaymentReferenceDTO getDetail(PaymentReference paymentReference) {
        if(paymentReference!=null) {
            List<Payment> payments = paymentRepository.findByPaymentReference(paymentReference);
            return PaymentReferenceDTO.toDTO(paymentReference, payments);
        }
        return null;
    }

    @Override
    public Page<PaymentReference> searchByQuery(Long serviceId, String key,Boolean paid,Pageable pageable) {
        return repository.searchByQuery(serviceId,key,paid,pageable);
    }

    @Override
    public List<PaymentReference> searchByCardNumber(Long cardNumber) {
        return repository.searchByCardNumber(cardNumber);
    }

    @Override
    public Page<PaymentReference> searchByServiceId(Long serviceId,Pageable pageable) {

        return repository.searchByServiceID(serviceId,pageable);
    }

    @Override
    public List<PaymentReference> getReferenceByPatient(Patient patient) {
        return  repository.findByPatient(patient);
    }

    @Override
    public Page<PaymentReference> getReferenceByPackage(ServicePackage servicePackage, Pageable pageable) {
        return  repository.findByServicePackage(servicePackage,pageable);
    }

    @Override
    public Page<PaymentReference> getReferenceByServiceItem(ServiceItem item, Pageable pageable) {
        return null;
    }

    @Override
    public Page<PaymentReference> getReferenceByPaid(Boolean status,Pageable pageable) {
        return null;
    }

    @Override
    public PaymentReference get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<PaymentReference> list(Pageable pageable) {
        return  repository.findAll(pageable);
    }

    @Override
    @Transactional
    public PaymentReference add(PaymentReference paymentReference) throws Exception {

        if(validateNew(paymentReference))
        {
            try{
                return repository.save(paymentReference);
            }
            catch (Exception e){

            }
        }
        return null;
    }
    private boolean validateNew(PaymentReference ref){
        if(ref==null || ref.getPatient()==null && ref.getServicePackage()==null){
            return false;
        }
         return true;
    }
    @Override
    public PaymentReference update(PaymentReference paymentReference) {
        return null;
    }

    @Override
    public void delete(PaymentReference paymentReference) {
            repository.delete(paymentReference);
    }
}
