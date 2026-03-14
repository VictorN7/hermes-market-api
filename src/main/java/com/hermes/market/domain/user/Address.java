package com.hermes.market.domain.user;

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
    private String state;

    @Column(nullable = false, length = 8)
    private String zipcode;

    @Column(nullable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Address(){
    }

    public Address(String street, Integer number, String complement, String neighborhood, String city,
                   String state, String zipcode, User user) {
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        createdAt = Instant.now();
        this.user = user;
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setUser(User user) {
        if (user == null){
            throw new IllegalArgumentException("User can not be null");
        }
        this.user = user;
    }

    public void setZipcode(String zipcode) {
        if (zipcode == null){
            throw new IllegalArgumentException("zipcode can not be null");
        }
        this.zipcode = zipcode;
    }

    public void setState(String state) {
        if (state == null){
            throw new IllegalArgumentException("State can not be null");
        }
        this.state = state;
    }

    public void setCity(String city) {
        if (city == null){
            throw new IllegalArgumentException("City can not be null");
        }
        this.city = city;
    }

    public void setNeighborhood(String neighborhood) {
        if (neighborhood == null){
            throw new IllegalArgumentException("neighborhood can not be null");
        }
        this.neighborhood = neighborhood;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setNumber(Integer number) {
        if (number == null){
            throw new IllegalArgumentException("Number can not be null");
        }
        this.number = number;
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

    public String getState() {
        return state;
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
                ", user=" + user +
                '}';
    }


}
