package com.indianbank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.indianbank.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByIdAndPassword(Long id, String password);

	List<User> findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCase(String fname, String lname);

	@Query("SELECT u FROM User u WHERE (LOWER(u.fname) LIKE %:fname% OR LOWER(u.lname) LIKE %:lname%) AND u.sex = :genderFilter")
	List<User> findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCaseAndSex(String fname, String lname,
			Character genderFilter);

//	List<User> findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCaseAndAcType(String fanme, String lname,
//			String actype);
//
//	List<User> findByFnameContainingIgnoreCaseOrLnameContainingIgnoreCaseAndAcTypeAndSex(String fanme, String lname,
//			String actype, Character sex);
}
