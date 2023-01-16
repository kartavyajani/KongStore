package com.kongstore.kongstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.kongstore.kongstore.model.Registrationmodel;


@Repository

public interface UserRepo extends JpaRepository<Registrationmodel, Long> {
	
	@Query(value = "SELECT * FROM kongstore.kongregistration where username=:username", nativeQuery = true)
	Registrationmodel findPropertyByPropertyId(@Param("username") String username);

}
