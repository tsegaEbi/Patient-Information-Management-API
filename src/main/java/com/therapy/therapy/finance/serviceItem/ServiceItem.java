package com.therapy.therapy.finance.serviceItem;

import com.therapy.therapy.common.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="service")
public class ServiceItem extends Model {

    @Column(nullable = false, unique = true)
    private String name;
    private String description;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private SERVICE_CATEGORY category;

}
