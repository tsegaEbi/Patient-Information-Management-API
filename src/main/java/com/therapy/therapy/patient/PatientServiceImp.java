package com.therapy.therapy.patient;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.common.Sex;
import com.therapy.therapy.common.Validation;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.finance.paymentReference.PAYMENT_REFERENCE_CODE;
import com.therapy.therapy.finance.paymentReference.PaymentReference;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceDTO;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImp implements PatientService {
    private final PatientRepository repository;

    private final int pageSize = Constants.PAGE_SIZE;

    @Autowired
    private PaymentReferenceService paymentReferenceService;

    public PatientServiceImp(PatientRepository repository){
        this.repository =repository;
    }
    @Override
    public Page<Patient> searchByQuery(PatientSearchQuery query,Pageable pageable){

        return repository.searchByQuery(query.getSex(),query.getRegion(),query.getNationality(), pageable);
    }
    @Override
    public   Page<Patient> findByKey(String key, Pageable pageable){
        return repository.searchKey(key,pageable);
    }
    @Override
    public Page<Patient> findBySex(Sex sex, Pageable pageable){
        return repository.findBySex(sex,pageable);
    }
    @Override
    public Patient get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Page<Patient> list(Pageable pageable) {

        return repository.findAll(pageable);
    }

    @Override
    public Patient add(Patient patient) {
        return repository.save(patient);
    }

    @Override
    @Transactional
    public Patient update(Patient patient) {

        return repository.save(patient);

    }

    @Override
    @Transactional
    public ActionResponse<Patient> updatePatient(Patient dto) {
        Validation validate =validateUpdate(dto);
        ActionResponse<Patient> result =new ActionResponse<>();
        result.setMessage(validate.getMessage());
        result.setResult(validate.isStatus());

        Patient oPatient = get(dto.getId());
        if(oPatient!=null) {
            oPatient = PatientDTO.toPatientForUpdate(oPatient,dto);
            if (validate.isStatus()) {
                try {
                    repository.save(oPatient);
                    result.setT(oPatient);
                } catch (Exception e) {
                    result.setResult(false);

                    result.setMessage(e.getMessage());
                }
            }
        }
        else{
            result.setMessage("Unknown Patient ID");
            result.setResult(false);
        }
        return result;
    }
    private Validation validateUpdate(Patient patient){
        Validation validate = new Validation();
        validate.setMessage("validate");
        validate.setStatus(true);

        return validate;
    }
    @Override
    @Transactional
    public ActionResponse<Patient> createPatient(Patient patient) {
        Validation validate =validateCreate(patient);
        ActionResponse<Patient> result =new ActionResponse<>();
        result.setMessage(validate.getMessage());
        result.setResult(validate.isStatus());
       if (validate.isStatus()) {
                try {
                    repository.save(patient);
                    result.setT(patient);
                } catch (Exception e) {
                    result.setResult(false);

                    result.setMessage(e.getMessage());
                }
            }

        return result;
    }

    @Override
    public List<PaymentReferenceDTO> getCardPaymentReference(Long patientId) {
        List<PaymentReference> references =paymentReferenceService.getByRefereeIdAndCode(patientId, PAYMENT_REFERENCE_CODE.CARD);

        if(references!=null){

            return references.stream().map(r->paymentReferenceService.getDetail(r)).collect(Collectors.toList());
        }

       return null;

    }

    @Override
    public Page<Patient> getByCardNumber(Long cardNumber, Pageable pageable) {
        return repository.findById(cardNumber,pageable);
    }

    private Validation validateCreate(Patient patient){
        Validation validate = new Validation();
        validate.setMessage("validate");
        validate.setStatus(true);

        if(patient.getFirstName()==null){
            validate.setMessage("First Name must be valid");
            validate.setStatus(false);
        }
        return validate;
    }
    @Override
    @Transactional
    public void delete(Patient patient) {

    }
}
