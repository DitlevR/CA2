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
    private String addres;
    private String phone;
    private String hobbies;

    public PersonDTO(Person p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.addres = new AddressDTO(p.getAddress()).toString();
        this.phone = new PhonesDTO(p.getPhone()).toString();
        this.hobbies = new HobbiesDTO(p.getHobbies()).toString();

    }

//    public PersonDTO(int id, String fName, String lName, AddressDTO addres, PhonesDTO phone, HobbiesDTO hobbies) {
//        this.id = id;
//        this.fName = fName;
//        this.lName = lName;
//        this.addres = addres;
//        this.phone = phone;
//        this.hobbies = hobbies;
//    }
    
    

    
    

    }


