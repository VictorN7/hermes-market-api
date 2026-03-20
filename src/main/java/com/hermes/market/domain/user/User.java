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

		setName(name);
		setEmail(email);
		setPassword(password);
		setBirthDate(birthDate);
		setRole(Role.CLIENT);
		setCpf(cpf);
		setStatus(UserStatus.ACTIVE);
		createdAt = Instant.now();
	}

	private void setRole(Role role) {
		if (role == null) {
			throw new BusinessException("Role cannot be null");
		}
		this.role = role.getCode();
	}

	private void setName(String name) {
		if (name == null || name.isBlank()){
			throw new BusinessException("User name cannot be null or empty");
		}
		this.name = name;
	}

	private void setEmail(String email) {

		if (email == null || email.isBlank()){
			throw new BusinessException("Email cannot be null");
		}

		if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
			throw new BusinessException("Email format is invalid");
		}

		this.email = email.toLowerCase();
	}

	private void setPassword(String password) {
		if (password == null || password.isBlank()){
			throw new BusinessException("Password cannot be null or empty");
		}
		if (password.length() < 8 || password.length() > 15){
			throw new BusinessException("Password must be between 8 and 15 characters long");
		}
		this.password = password;
	}

	private void setBirthDate(LocalDate birthDate) {
		if (birthDate == null){
			throw new BusinessException("Birth date cannot be null");
		}
		if (birthDate.isAfter(LocalDate.now())){
			throw new BusinessException("Birth date cannot be in the future");
		}

		this.birthDate = birthDate;
	}

	private void setCpf(String cpf) {
		if (cpf == null || cpf.isBlank()){
			throw new BusinessException("CPF cannot be null");
		}
		if (!cpf.matches("\\d{11}")){
			throw new BusinessException("CPF must have 11 digits");
		}

		this.cpf = cpf;
	}

	public void updateUser(String name, String email, LocalDate birthDate){

		ensureUserIsNotBlocked();
		setName(name);
		setEmail(email);
		setBirthDate(birthDate);
	}

	public void updatePassword(String newPassword, String confirmPassword, String currentPassword){

		ensureUserIsNotBlocked();

		if (newPassword == null || confirmPassword == null){
			throw new BusinessException("NewPassword or ConfirmPassword can not be null");
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

		setPassword(newPassword);
	}

	public void addAddress(Address address){
		if (address == null){
			throw new BusinessException("Address do not be null");
		}
		if (addresses.contains(address)) return;

		addresses.add(address);
	}

	private void ensureUserIsNotBlocked(){
		if (UserStatus.BLOCKED.equals(getStatus())){
			throw new BusinessException("User is blocked and cannot perform this action");
		}
	}

	public void activate(){

		ensureUserIsNotBlocked();

		if (UserStatus.ACTIVE.equals(getStatus())){
			throw new BusinessException("User is already active");
		}

		setStatus(UserStatus.ACTIVE);
	}

	public void deactivate(){

		ensureUserIsNotBlocked();

		if (UserStatus.INACTIVE.equals(getStatus())){
			throw new BusinessException("User is already inactive");
		}

		setStatus(UserStatus.INACTIVE);
	}

	public void block(){

		if (UserStatus.BLOCKED.equals(getStatus())){
			throw new BusinessException("User is already blocked");
		}

		setStatus(UserStatus.BLOCKED);
	}

	public void unlock(){

		if (!UserStatus.BLOCKED.equals(getStatus())){
			throw new BusinessException("User is not blocked");
		}

		setStatus(UserStatus.ACTIVE);
	}

	private void setStatus(UserStatus status) {
		if (status == null) {
			throw new BusinessException("User status cannot be null");
		}
		this.status = status.getCode();
	}

	public String getCpf() {
		return cpf;
	}

	public UserStatus getStatus() {
		return UserStatus.valueOf(status);
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