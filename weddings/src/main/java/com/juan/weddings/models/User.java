package com.juan.weddings.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(max=15)
	private String firstName;
	@NotBlank
	@Size(max=30)
	private String lastName;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	@Transient // Part of model but not stored in database
	private String confirmPassword;
	
	// One To Many linked by "planner" in "Wedding" model
	@OneToMany(mappedBy="planner", fetch=FetchType.LAZY)
	private List<Wedding> wedding;
	
	// RSVPS
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="guestlist",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="wedding_id")
			)
	private List<User> rsvps;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Wedding> getWedding() {
		return wedding;
	}

	public void setWedding(List<Wedding> wedding) {
		this.wedding = wedding;
	}

	public List<User> getRsvps() {
		return rsvps;
	}

	public void setRsvps(List<User> rsvps) {
		this.rsvps = rsvps;
	}
	
	
}
