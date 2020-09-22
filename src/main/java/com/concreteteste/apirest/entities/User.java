package com.concreteteste.apirest.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="user")
public class User implements Serializable{
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uidd2")
	@GenericGenerator(name = "uidd2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition="VARCHAR(255)")
	private UUID id;
	
	private String name;
	private String email;
	private String password;
	
	private LocalDate created;
	private LocalDateTime lastLogin;
    private LocalDateTime modified;
    
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID token;
    
    @OneToMany(mappedBy = "user")
    private List<Phone> phones;
    
    
    public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public User() {
    	super();
    }

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID token) {
		this.token = token;
	}

	
	public void generateToken() {
        this.token = UUID.randomUUID();
    }
	
    
	
}
