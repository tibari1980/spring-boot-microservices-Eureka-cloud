package com.arcesi.identityservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.identityservice.entities.PrivilegeBean;

@Repository
public interface PrivilegeBeanRepository  extends JpaRepository<PrivilegeBean, Long>{

	PrivilegeBean findByName(String permission);

}
