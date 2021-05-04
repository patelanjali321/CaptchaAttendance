package com.sirt.jpa;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sirt.entities.Arecord;
import com.sirt.entities.User;

@Repository
public interface ArecordRepository extends JpaRepository<Arecord, Long>, UserCustom{
	List<Arecord> findByTimeBetween(Date starDate, Date endDate);
	List<Arecord> findByUserInAndTimeBetweenOrderByUserName(Set<User> keySet, Date startDate, Date endDate);
	Arecord findTopByUserOrderByTimeDesc(User user);
}
