package com.therapy.therapy.security;

import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffDTO;
import com.therapy.therapy.staff.StaffService;
import com.therapy.therapy.user.UserEntity;
import com.therapy.therapy.user.UserEntityService;
import com.therapy.therapy.user.roles.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class AuthenticateController {


    @Autowired
    private DaoAuthenticationProvider authenticate;
    @Autowired
    private UserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRoleService roleService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private UserEntityService userService;



    @RequestMapping("/authenticate")
    public AuthenticationObject login(@RequestBody AuthenticationRequest request) throws Exception{
       try {
           authenticate.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           request.getUserName(),
                           request.getPassword()
                   )
           );
       }
       catch(BadCredentialsException e){
           throw new Exception("Incorrect user and password",e);
       }
        AuthenticationObject object = new AuthenticationObject();

       final UserDetails userDetails =myUserDetailsService
               .loadUserByUsername(request.getUserName());
       final String jwt =jwtUtil.generateToken(userDetails);

       Staff staff = staffService.getByStaffId(request.getUserName());
       if(staff==null)
           throw new IllegalArgumentException("Unknown Staff");
       UserEntity user =userService.getByStaffId(staff.getStaffId());
       if(user==null)
           throw new IllegalArgumentException("Unknown User");

       List<String> roles =roleService.getByUserId(user.getId()).stream().map(r->r.getRole().getName()).collect(Collectors.toList());

       object.setJwt(jwt);
       object.setRoles(roles);
       object.setStaffId(staff.getStaffId());
       object.setUserId(user.getId());
       object.setName(staff.getFirstName()+" "+staff.getFatherName());
       object.setStaff(StaffDTO.toDTO(staff));


       return object;

    }

}
