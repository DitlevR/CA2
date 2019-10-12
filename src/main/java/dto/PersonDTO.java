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
import java.util.List;

/**
 *
 * @author Rumle
 */
@Schema(name = "PersonDTO")
public class PersonDTO {

    private int id;
    @Schema(required = true, example = "Hans")
    private String fName;
    @Schema(required = true, example = "Hansen")
    private String lName;
    @Schema(required = true, example = "Vejen 32")
    private AddressDTO address;
    @Schema(required = true, example = "1232414")
    private PhonesDTO phones;
    @Schema(required = true, example = "svimming")
    private HobbiesDTO hobbies;

    public PersonDTO(Person p) {
        this.fName = p.getfName();
        this.lName = p.getlName();
        this.address = new AddressDTO(p.getAddress());
        this.phones = new PhonesDTO(p.getPhone());
        this.hobbies = new HobbiesDTO(p.getHobbies());

    }
    
    public PersonDTO() {
        
    }

//    public PersonDTO(int id, String fName, String lName, AddressDTO addres, PhonesDTO phone, HobbiesDTO hobbies) {
//        this.id = id;
//        this.fName = fName;
//        this.lName = lName;
//        this.addres = addres;
//        this.phone = phone;
//        this.hobbies = hobbies;
//    }

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public PhonesDTO getPhones() {
        return phones;
    }

    public void setPhones(PhonesDTO phones) {
        this.phones = phones;
    }

    public HobbiesDTO getHobbies() {
        return hobbies;
    }

    public void setHobbies(HobbiesDTO hobbies) {
        this.hobbies = hobbies;
    }

   
    

    
    
    
}
