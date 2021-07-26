package com.therapy.therapy.examination.labOrder;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.common.ORDER_STATUS;
import com.therapy.therapy.common.POSITIVE_NEGATIVE;
import com.therapy.therapy.examination.Examination;
import com.therapy.therapy.laboratory.Laboratory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name="laboratory_order")
public class LabOrder extends Model {
    @ManyToOne
    @JoinColumn(name="examination_id")
    private Examination examination;

    @ManyToOne
    @JoinColumn(name="laboratory_id")
    private Laboratory laboratory;

    @Enumerated(EnumType.STRING)
    private POSITIVE_NEGATIVE findingStatus=POSITIVE_NEGATIVE.UNKNOWN;

    private String finding;


    private Long technician;

    @Enumerated(EnumType.STRING)
    private ORDER_STATUS orderStatus=ORDER_STATUS.WAITING;


    private Boolean active=true;
}
