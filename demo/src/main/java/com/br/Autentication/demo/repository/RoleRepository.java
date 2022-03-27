package com.br.Autentication.demo.repository;

import com.br.Autentication.demo.model.Roles;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Roles, Long> {
    Roles findByRole(String role);
}
