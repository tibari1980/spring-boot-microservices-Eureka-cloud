package com.arcesi.identityservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.identityservice.entities.RoleBean;

@Repository
public interface RoleBeanRepository extends JpaRepository<RoleBean, Long> {

	Optional<RoleBean> findRoleBeanByRoleName(String roleName);

	Optional<RoleBean> findByRoleNameIgnoreCase(String roleName);


}
