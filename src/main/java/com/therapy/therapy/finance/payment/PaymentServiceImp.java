package com.therapy.therapy.finance.payment;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.Validation;
import com.therapy.therapy.finance.paymentReference.PaymentReference;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceDTO;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PaymentServiceImp implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentReferenceRepository referenceRepository;

    public PaymentServiceImp(PaymentRepository repository, PaymentReferenceRepository referenceRepository){
        this.repository=repository;
        this.referenceRepository =referenceRepository;
    }


    @Override
    public List<Payment> getByPatientId(Long id) {
        return null;
    }

    @Override
    public List<Payment> getByReference(PaymentReference ref) {
        return repository.findByPaymentReference(ref);
    }

    @Override
    @Transactional
    public ActionResponse<Payment> create(Payment payment, PaymentReferenceDTO reference ) {
        Validation validation =validateNew(payment,reference);
        ActionResponse<Payment> response =new ActionResponse<>();
        response.setResult(validation.isStatus());
        response.setMessage(validation.getMessage());

        if(validation.isStatus()){
                try{
                      repository.save(payment);
                      if(payment.getId()!=null){
                        // check if the payed amount is equals or greater than the reference
                        Double balance =reference.getPrice() -reference.getTotalPaid();
                        if(payment.getAmount()>=balance)
                        {
                             PaymentReference ref = referenceRepository.findById(reference.getId()).orElse(null);
                             ref.setPaid(true);
                             referenceRepository.save(ref);
                       }
                       }
                    response.setMessage("Successful");
                    response.setResult(true);
                    response.setT(payment);
                 }
                catch(Exception e){
                    response.setMessage("Exception: Failed to save the payment");
                    response.setResult(false);
                    response.setT(payment);
                }
        }
        return response;
    }

    private Validation validateNew(Payment payment, PaymentReferenceDTO reference){
        Validation validation =new Validation();
        validation.setStatus(true);
        validation.setMessage("valid");

        if(payment==null)
        {
            validation.setStatus(false);
            validation.setMessage("Null payment");
        }
        if(reference==null){
            validation.setStatus(false);
            validation.setMessage("Null reference");
        }

        if(reference.getPaid()==true)
        {
            validation.setStatus(false);
            validation.setMessage("Reference already paid");
        }
        if(payment.getAmount()==0)
        {
            validation.setStatus(false);
            validation.setMessage("Zero can't be paid");
        }

        return validation;

    }
    @Override
    public Payment get(Long id) {
        return null;
    }

    @Override
    public Page<Payment> list(Pageable pageable) {
        return null;
    }

    @Override
    public Payment add(Payment payment) throws Exception {
        return null;
    }

    @Override
    public Payment update(Payment payment) throws Exception {
        return null;
    }

    @Override
    public void delete(Payment payment) throws Exception {

    }
}
