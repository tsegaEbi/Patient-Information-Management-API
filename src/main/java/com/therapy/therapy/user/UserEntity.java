package com.therapy.therapy.user;

import com.therapy.therapy.common.Model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name="user_entity")
public class UserEntity extends Model {

    @Column(unique = true)
    private String userName;

    private String password;

    private Boolean enabled;

}
