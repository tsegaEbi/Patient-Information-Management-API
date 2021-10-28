package com.therapy.therapy.finance.item;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.finance.itemCategory.ItemCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name="item")
public class Item extends Model {
    @Column(unique=true)
    private String itemCode;   //cash register

    @Column(unique=true)
    private String name;

    private String description;

    private String subCategory;

    private Double price;

    @ManyToOne
    @JoinColumn(name="item_category_id")
    private ItemCategory category;
}
