package com.therapy.therapy.user;

import com.therapy.therapy.user.roles.Role;
import com.therapy.therapy.user.roles.userRole.UserRole;
import com.therapy.therapy.user.roles.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserEntityService userService;

    @Autowired
    private UserRoleService roleService;

    @Override
    public UserDetail loadUserByUsername(String staffId) throws UsernameNotFoundException {

        UserEntity userEntity =userService.getByStaffId(staffId);

        if(userEntity!=null)
        {
            List<UserRole> userRoles=roleService.getByUserId(userEntity.getId());

            List<Role> roles= new ArrayList<>();
            if(userRoles!=null)
                roles =userRoles.stream().map(r->r.getRole()).collect(Collectors.toList());

            UserDetail user = new UserDetail(userEntity.getUserName(),userEntity.getPassword(),userEntity.getEnabled(),roles);

            return user;
        }

        else{
            return null;
        }
    }


}
