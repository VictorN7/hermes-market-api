package com.hermes.market.domain.user;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hermes.market.domain.order.Order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
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
	private Integer role;
	
	@Column(nullable = false, unique = true, length = 11, updatable = false)
	private String cpf;
	
	@Column(nullable = false)
	private Integer status;
	
	@Column(updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant createdAt;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}
	
	public User(String name, String email, String password, LocalDate birthDate, String cpf) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		setRole(Role.CLIENT);
		this.cpf = cpf;
		setStatus(UserStatus.ACTIVE);
		this.createdAt = Instant.now(); // Implementação Temporária
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
		return Role.valueOf(role);
	}

	public void setRole(Role role) {
		if (role != null) {
			this.role = role.getCode(); 
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public UserStatus getStatus() {
		return UserStatus.valueOf(status);
	}

	public void setStatus(UserStatus status) {
		if (status != null) {
			this.status = status.getCode();
		}
	}

	public Long getId() {
		return id;
	}

	public Instant getCreatedAt() {
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