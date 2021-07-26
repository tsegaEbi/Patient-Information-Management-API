package com.therapy.therapy.finance.payment;

import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public class PaymentDTO {
    private Long id;

    private Long staffId;
    private String staffName;

    private Long paymentReferenceId;
    private Date date;
    private String payerFullName;
    private String payerTin;
    private String receiptNumber;
    private String paymentNote;

    private Double amount;
    private PAYMENT_METHOD paymentMethod;

    private String patientName;
    private Long patientId;

    private String packageName;
    private String serviceName;
    private Long serviceId;
    private Long packageId;
    private Double price;


    public static PaymentDTO toDTO(Payment payment){
        PaymentDTO dto = new PaymentDTO();

        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setDate(payment.getDate());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaymentNote(payment.getPaymentNote());


        dto.setPackageId(payment.getPaymentReference().getServicePackage().getId());
        dto.setPackageName(payment.getPaymentReference().getServicePackage().getPackageName());

        dto.setServiceId(payment.getPaymentReference().getServicePackage().getService().getId());
        dto.setServiceName(payment.getPaymentReference().getServicePackage().getService().getName());

        dto.setPrice(payment.getPaymentReference().getServicePackage().getPrice());

        dto.setPatientId(payment.getPaymentReference().getPatient().getId());
        Patient patient = payment.getPaymentReference().getPatient();
        dto.setPatientName(patient.getPrefix()+" "+patient.getFirstName()+" "+patient.getFatherName());

        Staff staff=  payment.getStaff();
        dto.setStaffName(staff.getPrefix()+" "+staff.getFirstName()+" "+staff.getFatherName());
        dto.setStaffId(staff.getId());

        return dto;
    }

}
