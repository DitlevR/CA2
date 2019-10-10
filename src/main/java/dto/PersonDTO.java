/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Address;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import java.util.List;

/**
 *
 * @author Rumle
 */
public class PersonDTO {

    private int id;
    private String fName;
    private String lName;
    private AddressDTO addres;
    private String phone;
    private String hobbies;
    private String city;
    private String zip;

    public PersonDTO(Person p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
        //this.street = p.getAddress().getStreet();
        this.city = p.getAddress().getCity();
        this.zip = p.getAddress().getZipCode();
        this.phone = p.getPhone().toString();
        this.hobbies = p.getHobbies().toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
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
     
//    this.addres  = new AddressDTO(p.getAddress());
//        this.phone = new PhonesDTO(p.getPhone());
//        this.hobbies = new HobbiesDTO(hobbies);

}
