/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;

/**
 *
 * @author Rumle
 */
public class AddressDTO {
    private String street;
    private String city;
    private String zip;
    private int id;
    
    public AddressDTO(Address a) {
        this.street = a.getStreet();
        this.city = a.getStreet();
        this.zip = a.getZipCode();
        this.id = a.getId();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    
    
    
}
