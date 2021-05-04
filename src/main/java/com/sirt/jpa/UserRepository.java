package com.sirt.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirt.entities.Role;
import com.sirt.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserCustom{
	User findByUsername(String username);
	List<User> findByEnrolledonBetweenAndRolesIn(Date firstDay, Date lastDay, List<Role> roleByName);
}
