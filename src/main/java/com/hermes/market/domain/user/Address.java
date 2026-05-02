package com.hermes.market.domain.user;

import com.hermes.market.application.exception.BusinessException;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false, length = 50)
    private String street;

    @Column(length = 50)
    private String complement;

    @Column(nullable = false, length = 20)
    private String neighborhood;

    @Column(nullable = false, length = 20)
    private String city;

    @Column(nullable = false, length = 20)
    private Integer state;

    @Column(nullable = false, length = 8)
    private String zipcode;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Address() {
    }

    public Address(String street, Integer number, String complement, String neighborhood, String city,
                   Integer state, String zipcode, User user) {

        setStreet(street);
        setNumber(number);
        setComplement(complement);
        setNeighborhood(neighborhood);
        setCity(city);
        setState(state);
        setZipcode(zipcode);
        createdAt = Instant.now();
        assignToUser(user);
        setStatus(AddressStatus.ACTIVE);
    }

    public void deactivate() {

        if (!AddressStatus.ACTIVE.equals(getStatus())) {
            throw new BusinessException("Address is already inactive");
        }
        setStatus(AddressStatus.INACTIVE);
    }

    public AddressStatus getStatus() {
        return AddressStatus.valueOf(status);
    }

    public void setStatus(AddressStatus status) {

        if (status == null) {
            throw new BusinessException("Address status cannot be null");
        }

        this.status = status.getCode();
    }

    private void setComplement(String complement) {
        this.complement = (complement != null && !complement.isBlank()) ? complement : null;
    }

    private void setStreet(String street) {

        if (street == null || street.isBlank()) {
            throw new BusinessException("Street cannot be null or empty");
        }
        this.street = street;
    }

    public void assignToUser(User user){
        if (user == null) {
            throw new BusinessException("User cannot be null");
        }
        this.user = user;
    }

    private void setZipcode(String zipcode) {
        if (zipcode == null) {
            throw new BusinessException("zipcode cannot be null");
        }
        if (!zipcode.matches("\\d{8}")) {
            throw new BusinessException("Zipcode must contain 8 numeric digits");
        }
        this.zipcode = zipcode;
    }

    private void setState(Integer state) {
        if (state == null) {
            throw new BusinessException("State cannot be null or empty");
        }
        this.state = state;
    }

    private void setCity(String city) {
        if (city == null || city.isBlank()) {
            throw new BusinessException("City cannot be null or empty");
        }
        this.city = city;
    }

    private void setNeighborhood(String neighborhood) {
        if (neighborhood == null || neighborhood.isBlank()) {
            throw new BusinessException("Neighborhood cannot be null or empty");
        }
        this.neighborhood = neighborhood;
    }

    private void setNumber(Integer number) {
        if (number == null) {
            throw new BusinessException("Number cannot be null");
        }
        if (number <= 0) {
            throw new BusinessException("Number cannot be less than or equal to 0");
        }
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public State getState() {
        return State.valueOf(state);
    }

    public String getZipcode() {
        return zipcode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", number=" + number +
                ", complement='" + complement + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode=" + zipcode +
                ", createdAt=" + createdAt +
                '}';
    }


}
