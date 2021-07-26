package com.therapy.therapy.user.roles.userRole;

import com.therapy.therapy.common.Model;
import com.therapy.therapy.user.UserEntity;
import com.therapy.therapy.user.roles.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@Table(name="user_role")
@Entity
public class UserRole extends Model {

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;


}
