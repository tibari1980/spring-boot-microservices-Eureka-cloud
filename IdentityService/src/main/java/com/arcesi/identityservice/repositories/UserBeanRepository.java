package com.arcesi.identityservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.identityservice.entities.UserBean;

@Repository
public interface UserBeanRepository extends JpaRepository<UserBean, Long> {

	Optional<UserBean> findUserBeanByEmail(String string);

	Optional<UserBean> findUserBeanByUidUser(String uid);

}
