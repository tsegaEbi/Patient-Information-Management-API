package com.therapy.therapy.finance.itemCategory;

import com.therapy.therapy.common.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter

@Entity
@Table(name="item_category")
public class ItemCategory extends Model {

    @Column(unique=true)
    private String name;
}
