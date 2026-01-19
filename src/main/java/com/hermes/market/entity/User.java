package com.hermes.market.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.hermes.market.enums.Role;
import com.hermes.market.enums.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private LocalDate dateBirth;
	
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
	
	public User() {
		
	}

	public User(String name, String email, String password, LocalDate dateBirth, String cpf) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.dateBirth = dateBirth;
		this.role = Role.CLIENT;
		this.cpf = cpf;
		this.status = UserStatus.ACTIVE;
		this.createdAt = LocalDateTime.now(); // Implementação Temporária
	}
	
	// Implementar o PerPersist e Lombok
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", dateBirth=" + dateBirth + ", role=" + role
				+ ", cpf=" + cpf + ", status=" + status + ", createdAt=" + createdAt + "]";
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
	
}