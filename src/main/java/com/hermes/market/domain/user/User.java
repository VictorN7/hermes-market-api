package com.hermes.market.domain.user;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.hermes.market.application.exception.BusinessException;
import com.hermes.market.domain.order.Order;

import jakarta.persistence.*;

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
	
	@Column(nullable = false, updatable = false)
	private Instant createdAt;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();
	
	protected User() {

	}
	
	public User(String name, String email, String password, LocalDate birthDate, String cpf) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		setRole(Role.CLIENT);
		this.cpf = cpf;
		setStatus(UserStatus.ACTIVE);
		this.createdAt = Instant.now();
	}

	public void updateUser(String name, String email, LocalDate birthDate){

		if (name != null) this.name = name;
		if (email != null)this.email = email;
		if (birthDate != null)this.birthDate = birthDate;
	}

	public void updatePassword(String newPassword, String confirmPassword, String currentPassword){

		if (newPassword == null || confirmPassword == null){
			throw new IllegalArgumentException("NewPassword or ConfirmPassword can not be null");
		}
		if (!this.password.equals(currentPassword)) {
			throw new BusinessException("Current password is incorrect");
		}
		if (!newPassword.equals(confirmPassword)){
			throw new BusinessException("The new passwords do not match");
		}
		if (newPassword.equals(this.password)) {
			throw new BusinessException("The new password can not be the same as the old one");
		}

		this.password = newPassword;
	}

	public void addAddress(Address address){
		if (address == null) throw new IllegalArgumentException("Address do not be null");
		addresses.add(address);
	}

	public List<Address> getAddresses() {
		return addresses;
	}
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
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

	public UserStatus getStatus() {
		return UserStatus.valueOf(status);
	}

	private void ensureUserIsNotBlocked(){
		if (UserStatus.BLOCKED.equals(getStatus())){
			throw new BusinessException("User is blocked");
		}
	}

	public void activateUser(){

		ensureUserIsNotBlocked();

		if (UserStatus.ACTIVE.equals(getStatus())){
			throw new BusinessException("User is already active");
		}

		setStatus(UserStatus.ACTIVE);
	}

	public void deactivateUser(){

		ensureUserIsNotBlocked();

		if (UserStatus.INACTIVE.equals(getStatus())){
			throw new BusinessException("User is already inactive");
		}

		setStatus(UserStatus.INACTIVE);
	}

	public void blockUser(){

		if (UserStatus.BLOCKED.equals(getStatus())){
			throw new BusinessException("User is already blocked");
		}

		setStatus(UserStatus.BLOCKED);
	}

	public void unlockUser(){

		if (!UserStatus.BLOCKED.equals(getStatus())){
			throw new BusinessException("User is not blocked");
		}

		setStatus(UserStatus.ACTIVE);
	}

	private void setStatus(UserStatus status) {
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