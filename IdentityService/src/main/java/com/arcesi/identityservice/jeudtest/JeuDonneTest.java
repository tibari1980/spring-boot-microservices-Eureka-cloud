package com.arcesi.identityservice.jeudtest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.identityservice.entities.PrivilegeBean;
import com.arcesi.identityservice.entities.RoleBean;
import com.arcesi.identityservice.entities.UserBean;
import com.arcesi.identityservice.enums.AppUserRole;
import com.arcesi.identityservice.enums.Permission;
import com.arcesi.identityservice.repositories.PrivilegeBeanRepository;
import com.arcesi.identityservice.repositories.RoleBeanRepository;
import com.arcesi.identityservice.repositories.UserBeanRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@Transactional
public class JeuDonneTest  implements CommandLineRunner   {
	
	
	private UserBeanRepository userRepository;
	private RoleBeanRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private PrivilegeBeanRepository privilegeBeanRepository;
	
	@SuppressWarnings("unused")
	@Override
	public void run(String... args) throws Exception {
		userRepository.deleteAllInBatch();
		roleRepository.deleteAllInBatch();
		
		
		
		addRoles();
		
		
		/********************* Add User **/
		UserBean user=UserBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.email("tibarinewdzign@gmail.com")
				.uidUser(UUID.randomUUID().toString().replace("-", ""))
				.firstName("zeroual")
				.lastName("tibari")
				.enabled(Boolean.TRUE)
				.locked(Boolean.TRUE)
				.roleBeans(new HashSet<>())
				.password(bCryptPasswordEncoder.encode("Boudarga1980@"))
				.build();
		   UserBean ub=userRepository.save(user);
		  Optional<UserBean> ube=userRepository.findUserBeanByEmail("tibarinewdzign@gmail.com");
		  
		  
		  addPrivilege();
		  
		  Optional<RoleBean> roleEmpl=roleRepository.findRoleBeanByRoleName(AppUserRole.MANAGER.getId());
		  Optional<RoleBean> roleAdmi=roleRepository.findRoleBeanByRoleName(AppUserRole.ADMINISTRATEUR.getId());
		  
		  Optional<RoleBean> roleCustomer=roleRepository.findRoleBeanByRoleName(AppUserRole.CUSTOMER.getId());
		  
		  
		  
		  //ube.get().getRoleBeans().add(roleEmpl.get());
		  ube.get().getRoleBeans().add(roleAdmi.get());
		   userRepository.saveAndFlush(ube.get());
		   
		   
		   UserBean lyna=UserBean.builder()
					.createdAt(Instant.now())
					.updatedAt(null)
					.email("kazzarlyna@gmail.com")
					.uidUser(UUID.randomUUID().toString().replace("-", ""))
					.firstName("kazzar")
					.lastName("lyna")
					.enabled(Boolean.TRUE)
					.locked(Boolean.TRUE)
					.roleBeans(new HashSet<>())
					.password(bCryptPasswordEncoder.encode("Boudarga1980@"))
					.build();
			  UserBean ubl=userRepository.save(lyna);
			  Optional<UserBean> ublSaved=userRepository.findUserBeanByEmail("kazzarlyna@gmail.com");
		      ublSaved.get().getRoleBeans().add(roleCustomer.get());
		      
		      
		      /***** add Manger */
		      UserBean manger=UserBean.builder()
						.createdAt(Instant.now())
						.updatedAt(null)
						.email("zeroual.t@gmail.com")
						.uidUser(UUID.randomUUID().toString().replace("-", ""))
						.firstName("zeroual")
						.lastName("tarik")
						.enabled(Boolean.TRUE)
						.locked(Boolean.TRUE)
						.roleBeans(new HashSet<>())
						.password(bCryptPasswordEncoder.encode("Boudarga1980@"))
						.build();
				  UserBean ubh=userRepository.save(manger);
				  Optional<UserBean> ubhSaved=userRepository.findUserBeanByEmail("zeroual.t@gmail.com");
			      ubhSaved.get().getRoleBeans().add(roleAdmi.get());
			
		
			      //Save privilege to role Admin
			      PrivilegeBean adminCreate=privilegeBeanRepository.findByName(Permission.ADMIN_CREATE.getPermission());
			      PrivilegeBean adminUpdate=privilegeBeanRepository.findByName(Permission.ADMIN_UPDATE.getPermission());
			      PrivilegeBean adminDelete=privilegeBeanRepository.findByName(Permission.ADMIN_DELETE.getPermission());
			      PrivilegeBean adminRead=privilegeBeanRepository.findByName(Permission.ADMIN_READ.getPermission());
			      
			      roleAdmi.get().setPrivilegeBeans(new HashSet<>());
			      roleAdmi.get().getPrivilegeBeans().add(adminRead);
			      roleAdmi.get().getPrivilegeBeans().add(adminCreate);
			      roleAdmi.get().getPrivilegeBeans().add(adminUpdate);
			      roleAdmi.get().getPrivilegeBeans().add(adminDelete);
			      
			      
			      
			      //Save privilege to role MANAGER
			      PrivilegeBean managerCreate=privilegeBeanRepository.findByName(Permission.MANAGER_CREATE.getPermission());
			      PrivilegeBean managerUpdate=privilegeBeanRepository.findByName(Permission.MANAGER_UPDATE.getPermission());
			      PrivilegeBean managerDelete=privilegeBeanRepository.findByName(Permission.MANAGER_DELETE.getPermission());
			      PrivilegeBean managerRead=privilegeBeanRepository.findByName(Permission.MANAGER_READ.getPermission());
			      
			      
			      roleEmpl.get().setPrivilegeBeans(new HashSet<>());
			      roleEmpl.get().getPrivilegeBeans().add(managerRead);
			      roleEmpl.get().getPrivilegeBeans().add(managerCreate);
			      roleEmpl.get().getPrivilegeBeans().add(managerUpdate);
			      roleEmpl.get().getPrivilegeBeans().add(managerDelete);
			      
			    //Save privilege to role CUSTOMER
			      PrivilegeBean customerCreate=privilegeBeanRepository.findByName(Permission.CUSTOMER_CREATE.getPermission());
			      PrivilegeBean customerRead=privilegeBeanRepository.findByName(Permission.CUSTOMER_READ .getPermission());
			      PrivilegeBean cutomerUpdate=privilegeBeanRepository.findByName(Permission.CUSTOMER_UPDATE.getPermission());
			     
			      roleCustomer.get().setPrivilegeBeans(new HashSet<>());
			      
			      roleCustomer.get().getPrivilegeBeans().add(customerCreate);
			      roleCustomer.get().getPrivilegeBeans().add(customerRead);
			      roleCustomer.get().getPrivilegeBeans().add(cutomerUpdate);
			      
	}

	private void addRoles() {
		RoleBean roleBean=RoleBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.CUSTOMER.getId())
				.build();
		RoleBean roleBean1=RoleBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.MANAGER.getId())
				.build();
		RoleBean roleBean2=RoleBean.builder()
				.createdAt(Instant.now())
				.updatedAt(null)
				.codeRole(null)
				.codeUniqueRole(UUID.randomUUID().toString().replace("-", ""))
				.roleName(AppUserRole.ADMINISTRATEUR.getId())
				.build();
		roleRepository.saveAll(Arrays.asList(roleBean,roleBean1,roleBean2));
	}

    private void addPrivilege() {
    	for( Permission permission: Permission.values()) {
    	     PrivilegeBean privilegeBean=PrivilegeBean.builder()
    	    		 .name(permission.getPermission())
    	    		 .build();
    	     privilegeBeanRepository.save(privilegeBean);
    	}
    }
}
