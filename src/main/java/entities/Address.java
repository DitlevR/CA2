/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Ludvig
 */
@Entity
@NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String street;
    private String additionalInfo;
    
    @OneToMany(mappedBy = "a")
    private List<Person> persons;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="CITYINFO_ID") //navn på kolonne som indeholder foreign key
    private CityInfo ci;

    public Address() {
    }
    
    public Address(String street, String additionalInfo) {
        //this.id = id;
        this.street = street;
        this.additionalInfo = additionalInfo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    
    public String getStreet() {
        return street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "entities.Address[ id=" + id + " ]";
    }
    
}
