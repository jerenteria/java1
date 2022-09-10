package com.juan.dogs.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;


@Entity
@Table(name="dogs")
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max=30)
	@NotBlank
	private String name;
	@NotBlank
	private String breed;
	@NotNull
	private int age;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User owner;
	
	// Many To Many 
	@ManyToMany
	@JoinTable(
			name="likes",
			joinColumns = @JoinColumn(name="dog_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<User> likers;
	
	// One to One Relationship
	@OneToOne(mappedBy="dog", cascade=CascadeType.ALL, fetch=FetchType.LAZY) // mapped in springboot
	private Tag tag;
	
	// One To Many
	@OneToMany(mappedBy="dog", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Toy> toys;
	
	public List<Toy> getToys() {
		return toys;
	}


	public List<User> getLikers() {
		return likers;
	}


	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}


	public void setLikers(List<User> likers) {
		this.likers = likers;
	}


	public void setToys(List<Toy> toys) {
		this.toys = toys;
	}


	public Tag getTag() {
		return tag;
	}
	
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yy-MM-DD HH:mm:ss")
	private Date createdAt;
	@DateTimeFormat(pattern="yy-MM-DD HH:mm:ss")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Dog() {
		
	}
	
	
	public Dog(String name, String breed, int age) {
		this.name = name;
		this.breed = breed;
		this.age = age;
	}
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
