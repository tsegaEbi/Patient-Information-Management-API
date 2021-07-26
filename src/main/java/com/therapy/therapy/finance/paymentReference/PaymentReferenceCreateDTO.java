package com.therapy.therapy.finance.paymentReference;

import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackage;
import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class PaymentReferenceCreateDTO {

    private Long patientId;
    private Long packageId;

    public static PaymentReference toRef(Patient patient, ServicePackage pac, Staff staff){

        PaymentReference ref = new PaymentReference();
        ref.setPatient(patient);
        ref.setServicePackage(pac);
        ref.setStaff(staff);
        ref.setPaid(false);
        return ref;
    }
}
