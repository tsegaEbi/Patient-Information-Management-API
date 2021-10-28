package com.therapy.therapy.user;

import com.therapy.therapy.common.ActionResponse;
import com.therapy.therapy.staff.Staff;
import com.therapy.therapy.staff.StaffService;
import com.therapy.therapy.user.roles.Role;
import com.therapy.therapy.user.roles.RoleDTO;
import com.therapy.therapy.user.roles.RoleService;
import com.therapy.therapy.user.roles.userRole.UserRole;
import com.therapy.therapy.user.roles.userRole.UserRoleDTO;
import com.therapy.therapy.user.roles.userRole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/userStaff")
public class UserController {

    @Autowired
    private UserEntityService userEntityService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ActionResponse<UserEntityDTO>  add(UserEntityCreateDTO dto) throws Exception {
         Staff staff = staffService.getByStaffId(dto.getStaffId());
        ActionResponse<UserEntityDTO> response = new ActionResponse<>();

        if(dto.getPassword()==null || dto.getPassword().length()<2)
        {
            response.setResult(false);
            response.setMessage("password must be more than 3 characters");
            return response;
        }

        if(staff==null)
        {
            response.setResult(false);
            response.setMessage("Unknown Staff ID");
            return response;
        }

        UserEntity entity = userEntityService.add(UserEntityCreateDTO.toUserEntity(dto));
        if(entity==null){
            response.setMessage("Unknown exception");
            response.setResult(false);
            return response;

        }
        response.setResult(true);
        response.setMessage("valid");
        response.setT(UserEntityDTO.toDTO(entity,staff,null));
        return response;
    }

    @GetMapping("/get/{staffId}")
    public UserEntityDTO  get(String staffId) throws Exception {
        Staff staff = staffService.getByStaffId(staffId);
        if(staff==null) return null;

        UserEntity user =userEntityService.getByStaffId(staffId);
        if(user==null)return null;

        List<UserRole> roles= userRoleService.getByUserEntity(user);
        if(roles!=null)
          return  UserEntityDTO.toDTO(userEntityService.getByStaffId(staffId),staffService.getByStaffId(staffId),roles);
        return  UserEntityDTO.toDTO(userEntityService.getByStaffId(staffId),staffService.getByStaffId(staffId), null);

    }
    @GetMapping("/list")
    public List<UserEntityDTO>  list() throws Exception {
        return userEntityService.listAll().stream().map(t->UserEntityDTO.toDTO(t,staffService.getByStaffId(t.getUserName()),userRoleService.getByUserEntity(t))).collect(Collectors.toList());

    }

    @PostMapping("/addRole")
    @Transactional
    public UserEntityDTO addRole(@RequestBody UserRoleCreateDTO dto) throws Exception {
                UserEntity user = userEntityService.get(dto.getUserId());
                if(user==null || dto.getRoles()==null)return null;

                List<Role>rolesToBeAdded =dto.getRoles().stream().map(r->roleService.get(r.getId())).collect(Collectors.toList());
                List<UserRole> addRoles =userRoleService.addRoles(user,rolesToBeAdded) ;

                if(addRoles!=null){
                    return UserEntityDTO.toDTO(user,staffService.getByStaffId(user.getUserName()),addRoles);
                }
                return UserEntityDTO.toDTO(user,staffService.getByStaffId(user.getUserName()),null);
    }
    @PostMapping("/removeRole")
    @Transactional
    public Boolean removeRole(@RequestBody UserRoleDTO dto) throws Exception {
          UserRole userRole =userRoleService.get(dto.getId());
         if(userRole!=null) {
             userRoleService.delete(userRole);

             return true;
         }
         return false;
    }
    @GetMapping("/getRoles")
    public List<RoleDTO>  getRoles() throws Exception {
        return roleService.findAll().stream().map(r->RoleDTO.toDto(r)).collect(Collectors.toList());

    }
}
