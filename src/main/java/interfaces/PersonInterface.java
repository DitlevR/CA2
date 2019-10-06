/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Person;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import java.util.List;

public interface PersonInterface {

    public Person getPerson(int id)
            throws PersonNotFoundException;

    public List<Person> getPersonsWithName(String name)
            throws PersonNotFoundException;
    
    public Person addPerson(String email, String fname, String lname)
            throws MissingInputException;
    
    public Person editPerson(Person p)
            throws PersonNotFoundException;
    
    public List<Person> getAllPersons();
    
    
    
    

}