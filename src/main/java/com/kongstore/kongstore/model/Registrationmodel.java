package com.kongstore.kongstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="Kongregistration")
public class Registrationmodel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO	)
	
	@Column(name="id")
	private long id;
	
	  private @NotBlank String username;
	  
	
	 private @NotBlank boolean loggedIn;
	 
	 private @NotBlank boolean termsandcondition;
	
	

	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="password")
    private String password;
	
	@Column(name="organisation")
    private String organisation;
	


	public String getOrganisation() {
		return organisation;
	}






	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	@Column(name="confirmPassword")
    private String confirmPassword;
	
	
	@Column(name="mobile")
	private String mobile;
	@Column(name="country")
	private String country;
	
	@Column(name="email")
	private String email;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="role")
	private String role;
	
	
	  public Registrationmodel(@NotBlank String username, 
              @NotBlank String password) {
      this.username = username;
      this.password = password;
    this.loggedIn = false;
  }
	  public Registrationmodel() {
		// TODO Auto-generated constructor stub
	}







	public String getUsername() {
		return username;
	}






	public void setUsername(String username) {
		this.username = username;
	}






	public boolean isLoggedIn() {
		return loggedIn;
	}






	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}






	@Override
	public String toString() {
		return "Registrationmodel [id=" + id + ", username=" + username + ", loggedIn=" + loggedIn + ", firstName="
				+ firstName + ", lastName=" + lastName + ", password=" + password + ", organisation=" + organisation
				+ ", confirmPassword=" + confirmPassword + ", mobile=" + mobile + ", country=" + country + ", email="
				+ email + ", telephone=" + telephone + ", role=" + role + "]";
	}






	public boolean isTermsandcondition() {
		return termsandcondition;
	}






	public void setTermsandcondition(boolean termsandcondition) {
		this.termsandcondition = termsandcondition;
	}






	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	
}
