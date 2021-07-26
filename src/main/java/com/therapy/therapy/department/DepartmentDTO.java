package com.therapy.therapy.department;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter

public class DepartmentDTO {
    private Long id;
    private String name;

    public static DepartmentDTO toDTO(Department d){
        if(d==null)
            return null;

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(d.getId());
        dto.setName(d.getName());
        return dto;
    }

    public static List<DepartmentDTO> toList(List<Department> depts){

        return depts.stream().map(t->toDTO(t)).collect(Collectors.toList());
    }
}
