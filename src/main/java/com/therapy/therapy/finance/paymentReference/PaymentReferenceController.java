package com.therapy.therapy.finance.paymentReference;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.configuration.Constants;
import com.therapy.therapy.finance.payment.Payment;
import com.therapy.therapy.finance.payment.PaymentCreateDTO;
import com.therapy.therapy.finance.payment.PaymentDTO;
import com.therapy.therapy.finance.payment.PaymentService;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackage;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackageService;
import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.patient.PatientService;
import com.therapy.therapy.security.SecurityService;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffService;
import com.therapy.therapy.user.roles.userRole.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/paymentReference")
public class PaymentReferenceController {
    @Autowired
    private PaymentReferenceService referenceService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ServicePackageService packageService;

    @Autowired
    private SecurityService securityService;

    @Autowired
   private UserRoleService roleService;

    @Autowired
    private PaymentService paymentService;

    Logger logger = LoggerFactory.getLogger(PaymentReferenceController.class);
     @GetMapping("/hey")
     public String get()
     {
         return securityService.getAuthorities().toString();
     }
    @PreAuthorize("hasAuthority('ADMIN')")
     @PostMapping("/create")
    public ActionResponse<PaymentReferenceDTO> create(@RequestBody PaymentReferenceCreateDTO dto) throws Exception {

        ActionResponse<PaymentReferenceDTO> response = new ActionResponse<>();
        response.setMessage("Invalid");
        response.setResult(false);

        Patient patient = patientService.get(dto.getPatientId());
        ServicePackage pac = packageService.get(dto.getPackageId());
        Staff staff = staffService.getByStaffId(securityService.getStaffId());

        if (patient == null) {
            response.setMessage("Patient not found");
        } else if (pac == null  ) {
            response.setMessage("Package not found or is not active");
        } else if (pac.getService() == null  ) {
            response.setMessage("Service not found or is not active");
        } else if (staff == null) {
            response.setMessage("Staff is not found or is not authorized");
        } else {
            PaymentReferenceDTO refDTO=PaymentReferenceDTO.toDTO(referenceService.add(PaymentReferenceCreateDTO.toRef(patient, pac, staff)), null);

            if(refDTO!=null){
                response.setMessage("Success");
                response.setT(refDTO);
                response.setResult(true);
            }
        }
        return response;
    }

    @GetMapping("/list/{pageNumber}")
    public Page<PaymentReferenceDTO> list(@PathVariable("pageNumber")int pageNumber){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(pageNumber, Constants.PAGE_SIZE,sort);

        return PaymentReferenceDTO.toList(referenceService.list(pageable));
    }
    @GetMapping("/{id}")
    public  PaymentReferenceDTO  get(@PathVariable("id")Long id){
        PaymentReference ref = referenceService.get(id);
        if(ref==null)
            return null;

        List<Payment> payments =paymentService.getByReference(ref);

        return PaymentReferenceDTO.toDTO(ref,payments);

    }
    @PostMapping("/byQuery")
    public Page<PaymentReferenceDTO> byQuery(@RequestBody PaymentReferenceSearchQuery query){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(query.getPageNumber(), Constants.PAGE_SIZE,sort);

        return PaymentReferenceDTO.toList(referenceService.searchByQuery(query.getServiceId(),null,query.getPaid(),pageable));
    }
    @PostMapping("/byCard")
    public List<PaymentReferenceDTO> byCard(@RequestBody PaymentReferenceSearchQuery query){
             return PaymentReferenceDTO.toList(referenceService.searchByCardNumber(query.getCardNumber()));
    }

    @PostMapping("/byService")
    public Page<PaymentReferenceDTO> byService(@RequestBody PaymentReferenceSearchQuery query){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(query.getPageNumber(), Constants.PAGE_SIZE,sort);

        return PaymentReferenceDTO.toList(referenceService.searchByQuery(query.getServiceId(),null,query.getPaid(),pageable));
    }
    // payment
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/payment/create")
    public ActionResponse<PaymentDTO> createPayment(@RequestBody PaymentCreateDTO dto) throws Exception {

        ActionResponse<PaymentDTO> response = new ActionResponse<>();
        response.setMessage("Invalid");
        response.setResult(false);

        PaymentReference reference =referenceService.get(dto.getReferenceId());
        Staff staff =staffService.getByStaffId(securityService.getStaffId());

        if (reference == null) {
            response.setMessage("Reference not found");
        } else if (reference.getPaid()==true) {
            response.setMessage("Reference already paid");
        } else if (dto.getAmount() == 0) {
            response.setMessage("Zero amount can't be paid");
        } else if (staff == null) {
            response.setMessage("Staff is not found or is not authorized");
        } else {
                List<Payment> payments =paymentService.getByReference(reference);
                PaymentReferenceDTO referenceDTO=PaymentReferenceDTO.toDTO(reference,payments);

                Payment payment =PaymentCreateDTO.toPayment(dto,reference,staff);
                ActionResponse<Payment> result =paymentService.create(payment,referenceDTO);

                if(result.getT()!=null) {
                    response.setT(PaymentDTO.toDTO((Payment) result.getT()));
                }
                response.setResult(result.getResult());
                response.setMessage(result.getMessage());
            }

        return response;

    }
    @PostMapping("/delete")
    public boolean delete(@RequestBody  Long id) throws Exception {
         referenceService.delete(referenceService.get(id));
         return true;
    }
}
