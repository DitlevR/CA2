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
    private String city;
    private String zip;
    private PhonesDTO phone;
    private HobbiesDTO hobbies;

    public PersonDTO(Person p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.city = p.getAddress().getCity();
        this.zip = p.getAddress().getZipCode();
        this.addres = new AddressDTO(p.getAddress());
        this.phone = new PhonesDTO(p.getPhone());
        this.hobbies = new HobbiesDTO(p.getHobbies());
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

//    public PersonDTO(int id, String fName, String lName, AddressDTO addres, PhonesDTO phone, HobbiesDTO hobbies) {
//        this.id = id;
//        this.fName = fName;
//        this.lName = lName;
//        this.addres = addres;
//        this.phone = phone;
//        this.hobbies = hobbies;
//    }
    
    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
