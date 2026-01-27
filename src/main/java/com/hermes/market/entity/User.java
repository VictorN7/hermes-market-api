package com.hermes.market.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import com.hermes.market.enums.Role;
import com.hermes.market.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name; 
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	private LocalDate birthDate;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Column(nullable = false, unique = true, length = 11, updatable = false)
	private String cpf;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Column(updatable = false)
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Order> orders;
	
	public User() {
	}

	public User(String name, String email, String password, LocalDate birthDate, String cpf) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.role = Role.CLIENT;
		this.cpf = cpf;
		this.status = UserStatus.ACTIVE;
		this.createdAt = LocalDateTime.now(); // Implementação Temporária
	}
	
	// Implementar o PerPersist e Lombok
	
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", dateBirth=" + birthDate + ", role=" + role
				+ ", cpf=" + cpf + ", status=" + status + ", createdAt=" + createdAt + "]";
	}
}