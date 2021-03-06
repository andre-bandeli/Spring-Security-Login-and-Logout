package com.br.Autentication.demo.service;

import com.br.Autentication.demo.model.Roles;
import com.br.Autentication.demo.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class SSUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public SSUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {

            com.br.Autentication.demo.model.User user = userRepository.findByUsername(username);

            if (user == null) {
                return null;
            }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthories(user));

        }catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Set<GrantedAuthority> getAuthories(com.br.Autentication.demo.model.User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Roles roles: user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roles.getRole());
            authorities.add(grantedAuthority);
        }

        return authorities;
    }


}
