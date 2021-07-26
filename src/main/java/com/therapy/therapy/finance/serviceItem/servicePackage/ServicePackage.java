package com.therapy.therapy.finance.serviceItem.servicePackage;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.finance.serviceItem.ServiceItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name="service_package")

public class ServicePackage extends Model {

    @ManyToOne
    @JoinColumn(name="service_id")
    private ServiceItem service;

    private String packageName;

    private Double price;

    private Boolean active;
}
