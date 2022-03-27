package com.br.Autentication.demo.configuration;


import com.br.Autentication.demo.model.Roles;
import com.br.Autentication.demo.model.User;
import com.br.Autentication.demo.repository.RoleRepository;
import com.br.Autentication.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Dataloader implements CommandLineRunner {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Roles("ADMIN"));
        roleRepository.save(new Roles("USER"));

        Roles adminRole = roleRepository.findByRole("ADMIN");
        Roles userRole = roleRepository.findByRole("USER");

        User adminUser = new User("admin@code.com", passwordEncoder.encode("password"), "Admin", "Super", "admin", true);
        adminUser.setRoles(Arrays.asList(adminRole));
        userRepository.save(adminUser);

        User user = new User("user@code.com", passwordEncoder.encode("password"), "User", "Super", "user", true);
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);


    }
}
