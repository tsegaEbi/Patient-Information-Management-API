package com.therapy.therapy.finance.paymentReference;

import com.therapy.therapy.finance.payment.Payment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter

public class PaymentReferenceDTO {
    private Long id;
    private Long staffId;
    private Long patientId;
    private Long packageId;
    private Long serviceId;

    private String title;
    private String patientName;

    private String staffName;
    private String serviceName;
    private String packageName;
    private Boolean packageStatus;

    private Boolean paid;

    private Double price;
    private Double totalPaid;

    private Date dateCreated;

    private PAYMENT_REFERENCE_CODE code;

   private List<Payment>payments;


    public static PaymentReferenceDTO toDTO(PaymentReference ref, List<Payment> payments){
        PaymentReferenceDTO dto = new PaymentReferenceDTO();

        dto.setId(ref.getId());
        dto.setTitle(ref.getTitle());
        dto.setDateCreated(ref.getDateCreated());

        dto.setStaffId(ref.getStaff().getId());
        dto.setStaffName(ref.getStaff().getPrefix()+" "+ref.getStaff().getFirstName()+" "+ref.getStaff().getFatherName());

        dto.setPackageId(ref.getServicePackage().getId());
        dto.setPackageName(ref.getServicePackage().getPackageName());

        dto.setServiceId(ref.getServicePackage().getService().getId());
        dto.setServiceName(ref.getServicePackage().getService().getName());


        dto.setPatientId(ref.getPatient().getId());
        dto.setPatientName(ref.getPatient().getPrefix()+" "+ref.getPatient().getFirstName()+" "+ref.getPatient().getFatherName());

        dto.setPrice(ref.getServicePackage().getPrice());
        dto.setPackageStatus(ref.getServicePackage().getActive());

        dto.setCode(ref.getCode());

        // calculate total paid
        Double tPayed = 0.0;
        if(ref.getPaid()==null || ref.getPaid()==false) {
            dto.setPaid(false);
            if (payments == null || payments.size() == 0) {

                dto.setTotalPaid(0.0);
            } else {


                for (Payment p :
                        payments) {
                    tPayed += p.getAmount();
                }
                dto.setTotalPaid(tPayed);

                if (tPayed >= ref.getServicePackage().getPrice()) {
                    dto.setPaid(true);
                }

            }

        }
        else
        {
            dto.setPaid(true);
            dto.setTotalPaid(ref.getServicePackage().getPrice());
        }
        dto.setTotalPaid(tPayed);
        dto.setPayments(payments);
        return dto;
    }

    public static Page<PaymentReferenceDTO> toList(Page<PaymentReference> list){
        return list.map(t->toDTO(t,null));
    }
    public static List<PaymentReferenceDTO> toList(List<PaymentReference> list){
        return list.stream().map(t->toDTO(t,null)).collect(Collectors.toList());
    }
}
