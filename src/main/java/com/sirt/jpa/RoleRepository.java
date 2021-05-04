package com.sirt.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirt.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, UserCustom{

	Role findOneByName(String role);
}
