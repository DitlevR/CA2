/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Person;

/**
 *
 * @author Rumle
 */
public class PersonDTO {
    private String email;
    private String fName;
    private String lName;
    
    public PersonDTO(Person p) {
        this.email = p.getEmail();
        this.fName = p.getfName();
        this.lName = p.getlName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "PersonDTO{" + "email=" + email + ", fName=" + fName + ", lName=" + lName + '}';
    }
    
    
    
}
