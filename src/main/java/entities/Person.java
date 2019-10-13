/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import org.eclipse.persistence.jpa.config.Cascade;

/**
 *
 * @author Ludvig
 */
@Entity
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fName;
    private String lName;
    private String email;
    //private int addressId;

    @OneToMany(mappedBy = "p", cascade = CascadeType.REMOVE) //hvilken variabel i Phone.java hvert Person object knyttes til
    private List<Phone> phones; //liste som et Person objekt knyttes til
//(fetch=FetchType.LAZY)
    @ManyToOne//(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address a;

    @ManyToMany
    @JoinTable(
            name = "PER_HOB",
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
    private List<Hobby> hobbies = new ArrayList();

    public Person() {
    }

    public Person(String email, String fName, String lName) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
//        this.addressId = AddressId;
    }

    public void setHobby(Hobby h) {
        hobbies.add(h);
        h.getPersons().add(this);
    }

    public void setA(Address a) {
        this.a = a;
//        this.addressId = this.a.getId();
        //a.getPersons().add(this);
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entities.Person[ id=" + id + " name=" + fName + " " + lName + " ]";
    }

    public Address getAddress() {
        return a;
    }
    
    public List<Phone> getPhone() {
        return phones;
    }
    
    public List<Hobby> getHobbies() {
        return hobbies;
    }

}
