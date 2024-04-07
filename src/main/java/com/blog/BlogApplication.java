package com.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.blog.entities.Role;
import com.blog.repositories.RoleRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner{
	
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * in case no roles are configured when the application starts.
		 */
		try {
			Role role1 = new Role();
			role1.setRoleId(1l);
			role1.setName("ZC");
			
			Role role2 = new Role();
			role2.setRoleId(2l);
			role2.setName("SM");
			
			Role role3 = new Role();
			role3.setRoleId(3l);
			role3.setName("ADMIN");
			
			List<Role> rolesList = List.of(role1, role2, role3);
			roleRepository.saveAll(rolesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
}
