package com.therapy.therapy.user;

import com.therapy.therapy.staff.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/userStaff")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private StaffService staffService;


    @PostMapping("/add")
    public UserEntity  add(UserEntityCreateDTO dto) throws Exception {

        if(dto.getPassword()==null || dto.getPassword().length()<2)
            throw new IllegalAccessException("Password size is not correct");

        if(dto.getStaffId()==null || dto.getStaffId().length()<1)
            throw new IllegalAccessException("Staff id value is not valid");

        UserEntity entity = userEntityService.add(UserEntityCreateDTO.toUserEntity(dto));

        return entity;
    }

    @PostMapping("/get")
    public UserEntity  get(String staffId) throws Exception {
          return   userEntityService.getByStaffId(staffId);
    }

}
