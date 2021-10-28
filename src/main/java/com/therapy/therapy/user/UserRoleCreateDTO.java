package com.therapy.therapy.user;

import com.therapy.therapy.user.roles.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRoleCreateDTO {

    private Long userId;

    private List<Role> roles;
}
