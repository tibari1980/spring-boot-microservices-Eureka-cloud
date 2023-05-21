package com.arcesi.identityservice.jeudtest;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.internal.bytebuddy.description.NamedElement.WithOptionalName;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.arcesi.identityservice.entities.RoleBean;
import com.arcesi.identityservice.entities.UserBean;
import com.arcesi.identityservice.enums.AppUserRole;
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
				.enabled(Boolean.FALSE)
				.locked(Boolean.FALSE)
				.roleBeans(new HashSet<>())
				.password(bCryptPasswordEncoder.encode("boudarga"))
				.build();
		   UserBean ub=userRepository.save(user);
		  Optional<UserBean> ube=userRepository.findUserBeanByEmail("tibarinewdzign@gmail.com");
		  
		  
		  
		  //RoleBean roleClient=roleRepository.findRoleBeanByRoleName("CLIENT");
		  Optional<RoleBean> roleEmpl=roleRepository.findRoleBeanByRoleName(AppUserRole.MANAGER.getId());
		  Optional<RoleBean> roleAdmi=roleRepository.findRoleBeanByRoleName(AppUserRole.ADMINISTRATEUR.getId());
		  Optional<RoleBean> roleCustomer=roleRepository.findRoleBeanByRoleName(AppUserRole.CUSTOMER.getId());
		  
		  
		  
		  ube.get().getRoleBeans().add(roleEmpl.get());
		  ube.get().getRoleBeans().add(roleAdmi.get());
		   userRepository.saveAndFlush(ube.get());
		   
		   
		   UserBean lyna=UserBean.builder()
					.createdAt(Instant.now())
					.updatedAt(null)
					.email("kazzarlyna@gmail.com")
					.uidUser(UUID.randomUUID().toString().replace("-", ""))
					.firstName("kazzar")
					.lastName("lyna")
					.enabled(Boolean.FALSE)
					.locked(Boolean.FALSE)
					.roleBeans(new HashSet<>())
					.password(bCryptPasswordEncoder.encode("boudarga"))
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
						.enabled(Boolean.FALSE)
						.locked(Boolean.FALSE)
						.roleBeans(new HashSet<>())
						.password(bCryptPasswordEncoder.encode("boudarga"))
						.build();
				  UserBean ubh=userRepository.save(manger);
				  Optional<UserBean> ubhSaved=userRepository.findUserBeanByEmail("zeroual.t@gmail.com");
			      ubhSaved.get().getRoleBeans().add(roleEmpl.get());
			
		
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

}
