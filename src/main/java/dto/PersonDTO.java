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
    private List<PhoneDTO> phone;
    private List<HobbyDTO> hobbies;

    public PersonDTO(Person p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
<<<<<<< HEAD
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
=======
        this.addres = new AddressDTO(p.getAddress());
//        this.phone = new PhonesDTO(p.getPhone());
//        this.hobbies = new HobbiesDTO(hobbies);
>>>>>>> 6b6435b4228cd9b632a489039cfb32c50cd6c5e2

    }

    
    

    }


