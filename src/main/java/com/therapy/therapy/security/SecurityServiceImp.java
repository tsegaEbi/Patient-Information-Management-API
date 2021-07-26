package com.therapy.therapy.security;

import com.therapy.therapy.staff.StaffRepository;
import com.therapy.therapy.user.UserDetail;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImp implements SecurityService {
    private final StaffRepository staffRepository;

    public SecurityServiceImp(StaffRepository repository){
        this.staffRepository =repository;
    }

    @Override
    public boolean isAuthorized(String role)
    {
        if(getAuthorities().toString().contains(role)){
            return true;
        }
       return false;
    }


    @Override
    public String getStaffId() {
        SecurityContext context = SecurityContextHolder.getContext();
        return ((UserDetail)(context.getAuthentication().getPrincipal())).getUsername();

    }
    @Override
    public List<String> getAuthorities() {
        SecurityContext context = SecurityContextHolder.getContext();
        List<String> authorities =  context.getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return authorities;
     }
     @Override
    public boolean isAdmin()
     {
         return getAuthorities().stream().filter(r->r.equalsIgnoreCase("ADMIN")).findFirst()!=null;
     }

}
