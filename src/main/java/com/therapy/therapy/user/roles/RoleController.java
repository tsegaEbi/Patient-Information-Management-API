package com.therapy.therapy.user.roles;

import com.therapy.therapy.user.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserEntityService userEntityService;

     @PostMapping("/create")
     public List<RoleDTO> create(@RequestBody String name) throws Exception {
         if(name!=null) {

             Role role = new Role();
             role.setName(name.toUpperCase());
             roleService.add(role);
         }
         return roleService.findAll().stream().map(r->RoleDTO.toDto(r)).collect(Collectors.toList());
       }


}
