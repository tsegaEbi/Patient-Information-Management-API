package com.therapy.therapy.patient;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.finance.payment.PaymentService;
import com.therapy.therapy.finance.paymentReference.PaymentReference;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceDTO;
import com.therapy.therapy.finance.paymentReference.PaymentReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin

@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientReportHelperService reportHelperService;
    @Autowired
    private PaymentReferenceService referenceService;

    @Autowired
    private PaymentService paymentService;

    private final int pageSize = Constants.PAGE_SIZE;


    @PostMapping("/byQuery")
    public Page<PatientDTO> byQuery(@RequestBody PatientSearchQuery query){
        Sort sort =Sort.by(Sort.Direction.ASC,"firstName");
        Pageable pageable = PageRequest.of(query.getPageNumber(),pageSize,sort);
        Page<Patient> patients=patientService.searchByQuery(query,pageable) ;
        return patients.map(p->PatientDTO.patientDTO(p));
    }
    @GetMapping("/list/{pageNumber}")
    public Page<PatientDTO> list(@PathVariable("pageNumber")int pageNumber){
        Sort sort =Sort.by(Sort.Direction.ASC,"firstName");
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        return PatientDTO.toList(patientService.list(pageable));
    }
    @GetMapping("/cardNumber/{cardNumber}")
    public Page<PatientDTO> list(@PathVariable("cardNumber")Long cardNumber){

        Pageable pageable = PageRequest.of(0,pageSize);
        return PatientDTO.toList(patientService.getByCardNumber(cardNumber,pageable));
    }
    @GetMapping("/search")
    public Page<PatientDTO> search(@RequestParam("key")String key){
        Sort sort =Sort.by(Sort.Direction.ASC,"firstName");
        Pageable pageable = PageRequest.of(0,pageSize,sort);
        return PatientDTO.toList(patientService.findByKey(key,pageable));
    }
    @GetMapping("/{id}")
    public PatientDetailDTO get(@PathVariable("id")Long id)
    {
        Patient patient = patientService.get(id);
        if(patient ==null)
            return null;

        List<PaymentReference> payRefers = referenceService.getReferenceByPatient(patient);

        return PatientDetailDTO.toDetailDTO(patient,payRefers.stream()
                .map(r-> PaymentReferenceDTO.toDTO(r,paymentService.getByPatientId(patient.getId()))).collect(Collectors.toList()));
    }
    @GetMapping("/report/{id}")
    public PatientDetailDTO getReport(@PathVariable("id")Long id)
    {
        Patient patient = patientService.get(id);
        if(patient ==null)
            return null;


       return reportHelperService.getDetail(patient);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CARD')")
    @PostMapping("/update")
    public ActionResponse<PatientDTO> update(@RequestBody PatientDTO dto) throws Exception {
        ActionResponse<PatientDTO> response = new ActionResponse<>();
        ActionResponse<Patient> result= new ActionResponse<>();


        result =patientService.updatePatient(PatientDTO.patient(dto));
        response.setMessage(result.getMessage());
        response.setResult(result.getResult());

        if(result.getT()!=null){
            response.setT(PatientDTO.patientDTO((Patient)result.getT()));
        }

        return response;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN','CARD')")
    @PostMapping("/add")
    public ActionResponse<PatientDTO> add(@RequestBody PatientDTO dto) throws Exception {
        ActionResponse<PatientDTO> response = new ActionResponse<>();
        ActionResponse<Patient> result= new ActionResponse<>();


        result =patientService.createPatient(PatientDTO.patient(dto));
        response.setMessage(result.getMessage());
        response.setResult(result.getResult());

        if(result.getT()!=null){
            response.setT(PatientDTO.patientDTO((Patient)result.getT()));
        }

        return response;
    }

}
