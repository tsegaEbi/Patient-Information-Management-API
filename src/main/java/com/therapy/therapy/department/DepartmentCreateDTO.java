package com.therapy.therapy.department;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class DepartmentCreateDTO {

    private String name;

    public static Department toDepartment(String name){

        Department dept = new Department();
        dept.setName(name);

        return dept;

    }
}
