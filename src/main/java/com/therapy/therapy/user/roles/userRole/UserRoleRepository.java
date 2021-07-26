package com.therapy.therapy.user.roles.userRole;

import com.therapy.therapy.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Long> {

    List<UserRole> findByUser(UserEntity user);
}
