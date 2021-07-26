package com.therapy.therapy.treatment.treatmentTherapy;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.therapy.Therapy;
import com.therapy.therapy.treatment.Treatment;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name="treatment_therapy")
public class TreatmentTherapy extends Model
{
    @ManyToOne
    @JoinColumn(name="treatment_id")
    private Treatment treatment;

    @ManyToOne
    @JoinColumn(name="therapy_id")
    private Therapy therapy;
}
