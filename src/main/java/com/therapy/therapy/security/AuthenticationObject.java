package com.therapy.therapy.security;

import com.therapy.therapy.staff.StaffDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class AuthenticationObject {
    private String jwt;
    private List<String> roles;
    private String staffId;
    private Long userId;
    private String name;
    private StaffDTO staff;
}
