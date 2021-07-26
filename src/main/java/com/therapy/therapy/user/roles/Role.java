package com.therapy.therapy.user.roles;

import com.therapy.therapy.common.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name="role")
public class Role extends Model {
    private String name;
}
