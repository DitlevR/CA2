/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;
import entities.Person;
import entities.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Rumle
 */
@Schema(name = "PersonDTO")
public class PersonDTO {

    private int id;
    @Schema(required = true, example = "email@email.dk")
    private String email;
    @Schema(required = true, example = "Hans")
    private String fName;
    @Schema(required = true, example = "Hansen")
    private String lName;
//    @Schema(required = true, example = "1")
//    private int addressId;
    @Schema(required = true, example = "Bakkevej 32")
    private String street;
    @Schema(required = true, example = "Vejle")
    private String city;
    @Schema(required = true, example = "2840")
    private String zip;
    @Schema(example = "[\"1232414\",\76948693\"]")
    private Set<PhoneDTO> phones = new HashSet<>();
    @Schema(example = "[\"svimming\",\"bowling\"]")
    private Set<HobbyDTO> hobbies = new HashSet<>();

    public PersonDTO(Person p) {
        this.id = p.getId();
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.street = p.getAddress().getStreet();
        this.city = p.getAddress().getCity();
        this.zip = p.getAddress().getZipCode();

        for (Phone ph : p.getPhone()) {
            this.phones.add(new PhoneDTO(ph));
        }

        for (Hobby h : p.getHobbies()) {
            this.hobbies.add(new HobbyDTO(h));
        }
    }

    public PersonDTO(String email, String fName, String lName) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
//        this.addressId = addressId;
    }
    
    public String getEmail(){
        return email;
    }
    
//    public int getAddressId(){
//        return addressId;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public PersonDTO() {

    }

    public int getId() {
        return id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
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

    public Set<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneDTO> phones) {
        this.phones = phones;
    }

    public Set<HobbyDTO> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<HobbyDTO> hobbies) {
        this.hobbies = hobbies;
    }

}
