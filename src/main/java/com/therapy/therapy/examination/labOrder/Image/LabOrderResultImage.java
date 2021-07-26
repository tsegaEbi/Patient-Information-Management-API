package com.therapy.therapy.examination.labOrder.Image;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.examination.labOrder.LabOrder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name="laborder_result_image")
public class LabOrderResultImage extends Model {
    @ManyToOne
    @JoinColumn(name="laborder_id")
    private LabOrder labOrder;
    private Long fileId;

}
