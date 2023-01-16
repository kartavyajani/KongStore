package com.kongstore.kongstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kongstore.kongstore.model.Registrationmodel;
import com.kongstore.kongstore.model.Status;
import com.kongstore.kongstore.repository.UserRepo;
import com.kongstore.kongstore.service.Userservice;

@RestController
@CrossOrigin("*")
public class Usercontroller {
	@Autowired
	UserRepo userRepository;
	@Autowired
	Userservice userservice;
	private String status;

//	else if(admindata.getUsername()==null || admindata.getUsername()==" " || admindata.getPassword()==null || admindata.getPassword()==" " )

	@PostMapping("/users/register")
	public Status registerUser(@Valid @ModelAttribute Registrationmodel newUser) {
		List<Registrationmodel> users = userRepository.findAll();
		System.out.println("New user: " + newUser.toString());
		if (newUser.getUsername() == "" || newUser.getPassword() == "" || newUser.getUsername() == null
				|| newUser.getPassword() == null) {
			return Status.FAILURE;
		} else if (!newUser.getConfirmPassword().equals(newUser.getPassword())) {
			return Status.PASSWORD_NOT_MATCH;

		} else {
			for (Registrationmodel user : users) {
				System.out.println("Registered user: " + newUser.toString());
				System.out.println("user------------------------------------>" + user);

				if (user.getUsername().equals(newUser.getUsername())) {
					System.out.println("User Already exists!");
					return Status.USER_ALREADY_EXISTS;

					// if (user.equals(newUser)) {
					// System.out.println("User Already exists!");
					// return Status.USER_ALREADY_EXISTS;
				}
			}

			userRepository.save(newUser);
			return Status.SUCCESS;
		}
	}

	@PostMapping("/users/password/{username}")
	public String update(@PathVariable String username, @RequestBody Registrationmodel mode) {
		System.out.println("insdie updatepassword method ------>" + username);
		System.out.println("insdie updatepassword method ------>" + mode.getConfirmPassword());
		System.out.println("insdie updatepassword method ------>" + mode.getPassword());
		userservice.findOne(username, mode);

		return status = "update success";
	}

	@PostMapping("/users/login")
	public Status loginUser(@Valid @ModelAttribute Registrationmodel user) {
		List<Registrationmodel> users = userRepository.findAll();
		System.out.println("inside login");
		System.out.println("username------------------------------->" + user.getUsername());
		System.out.println("password------------------------------->" + user.getPassword());
		if (user.getUsername() == "" || user.getPassword() == "" || user.getUsername() == null
				|| user.getPassword() == null) {
			System.out.println("username------------------------------->" + user.getUsername());
			System.out.println("password------------------------------->" + user.getPassword());

			return Status.FAILURE;
		} else {
			System.out.println("inside else ");
			for (Registrationmodel other : users) {
				System.out.println("other user ---------------" + other.getUsername());
				if (other.getUsername().equals(user.getUsername()) && other.getPassword().equals(user.getPassword())) {
					user.setLoggedIn(true);
					System.out.println("logged in user: " + user.toString());
//	 	               userRepository.save(user);
					return Status.SUCCESS;
				}
			}

			return Status.FAILURE;

		}

	}

	@PostMapping("/users/logout")
	public Status logUserOut(@Valid @RequestBody Registrationmodel user) {
		List<Registrationmodel> users = userRepository.findAll();

		for (Registrationmodel other : users) {
			if (other.equals(user)) {
				user.setLoggedIn(false);
				System.out.println("loged out user: " + user.toString());
//	               userRepository.save(user);
				return Status.SUCCESS;
			}
		}

		return Status.FAILURE;
	}

	@DeleteMapping("/users/all")
	public Status deleteUsers() {
		userRepository.deleteAll();
		return Status.SUCCESS;
	}
}
