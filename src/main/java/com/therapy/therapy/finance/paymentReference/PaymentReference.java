package com.therapy.therapy.finance.paymentReference;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.finance.serviceItem.servicePackage.ServicePackage;
import com.therapy.therapy.patient.Patient;
import com.therapy.therapy.staff.Staff;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="payment_reference")
public class PaymentReference extends Model {

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="service_package_id")
    private ServicePackage servicePackage;

    @Enumerated(EnumType.STRING)
    private PAYMENT_REFERENCE_CODE code;

    private Long refereeId;
    private String title;

    @ManyToOne
    @JoinColumn(name="staff_id")
    private Staff staff;

    private Boolean paid;
}
