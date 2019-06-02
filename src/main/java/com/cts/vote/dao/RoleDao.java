package com.cts.vote.dao;

import org.springframework.data.repository.CrudRepository;

import com.cts.vote.domain.security.Role;

public interface RoleDao extends CrudRepository<Role, Integer> {
    
    Role findByName(String name);
}
