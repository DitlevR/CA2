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
        this.addres = new AddressDTO(p.getAddress());
//        this.phone = new PhonesDTO(p.getPhone());
//        this.hobbies = new HobbiesDTO(hobbies);

    }

    
    

    }


