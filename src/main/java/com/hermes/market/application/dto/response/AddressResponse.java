package com.hermes.market.application.dto.response;

public class AddressResponse {

    private Long id;
    private Integer number;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipcode;

    public AddressResponse(){
    }

    public AddressResponse(Long id, Integer number, String street, String complement,
                           String neighborhood, String city, String state, String zipcode) {
        this.id = id;
        this.number = number;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
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

    @Override
    public String toString() {
        return "AddressResponse{" +
                "id=" + id +
                ", number=" + number +
                ", street='" + street + '\'' +
                ", complement='" + complement + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
