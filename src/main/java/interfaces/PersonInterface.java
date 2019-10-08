/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Address;
//import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import java.util.List;

public interface PersonInterface {

    public Address getAddresFromPhone(String phone);
    
    public List<Hobby> getHobbiesFromPhone(String phone);
    
    public List<Person> getPersonsWithHobby(String hobby);
    
    public List<Person> getAllPersonWithZipcode(String zip);
    
    public Integer countPersonsWithHobby(String hobby);
    
    public List<Address> getAllZipcodes();
    
    public Person editPerson(Person p) throws PersonNotFoundException;
    
    public Person deletePerson(int id) throws PersonNotFoundException;
    
    public Person addPerson(String fname, String lname, String street, String city, String zipcode) throws MissingInputException;
    
    public List<Person> getAllPersons();
       
    
    
    
    
    
    
    
    

}
