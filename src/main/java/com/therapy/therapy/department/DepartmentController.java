package com.therapy.therapy.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/department")


public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getAll")
    public List<DepartmentDTO> getAll(){
        return DepartmentDTO.toList(departmentService.getAll());
    }
    @PostMapping("/create")
    public DepartmentDTO createDepartment(@RequestBody String name) throws Exception {
        return DepartmentDTO.toDTO(departmentService.add(DepartmentCreateDTO.toDepartment(name)));
    }
   @GetMapping("{id}")
    public DepartmentDTO get(@PathVariable Long id){
        return DepartmentDTO.toDTO(departmentService.get(id));
   }
}
