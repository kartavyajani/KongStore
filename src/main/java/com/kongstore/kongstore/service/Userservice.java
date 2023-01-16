package com.kongstore.kongstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kongstore.kongstore.model.Registrationmodel;
import com.kongstore.kongstore.repository.UserRepo;

@Service
public class Userservice {
	@Autowired
	private UserRepo userrepo;

	public void findOne(String Username, Registrationmodel model) {
		System.out.print(Username);
	
		Registrationmodel cr = userrepo.findPropertyByPropertyId(Username);
		cr.setPassword(model.getPassword());
		cr.setConfirmPassword(model.getConfirmPassword());
		System.out.println(model.getConfirmPassword());
		System.out.println(model.getPassword());

		userrepo.save(cr);

	}

}
