package com.therapy.therapy.security;

import java.util.Collection;

public interface SecurityService {
    String  getStaffId();

    Collection<String> getAuthorities();

    boolean isAuthorized(String role);

    boolean isAdmin();
}
