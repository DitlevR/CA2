package facades;

//import entities.RenameMe;
import dto.PersonDTO;
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import interfaces.PersonInterface;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements PersonInterface{

    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Address getAddresFromPhone(String phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hobby> getHobbiesFromPhone(String phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getPersonsWithHobby(String hobby) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> getAllPersonWithZipcode(String zip) {
         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             List<Person> personWithZip = em.createQuery("Select p from Person p JOIN p.Address a where a.id = p.id");
             em.getTransaction().commit();
             return personWithZip;
         }
    }

    @Override
    public Integer countPersonsWithHobby(String hobby) {
         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             int count = em.createQuery("select COUNT(p) from ")
             
         } finally {
             em.close();
         }
    }

    @Override
    public List<CityInfo> getAllZipcodes() {
        EntityManager em = emf.createEntityManager();
        
        try {
          em.getTransaction().begin();
          List<CityInfo> allCities = em.createQuery("Select c from Cityinfo c", CityInfo.class).getResultList();
          em.getTransaction().commit();
          return allCities;
        } finally {
            em.close();
        }
          
    }
    
    @Override
    public Person editPerson(Person p) throws PersonNotFoundException {
      EntityManager em = emf.createEntityManager();
      try {
          em.getTransaction().begin();
          Person findPerson = em.find(Person.class, p.getId());
          if(findPerson == null) {
              throw new PersonNotFoundException("Person doesn't exist");
          }
          em.refresh(p);
          
      } finally {
          em.close();
      }
      return p;
    }

    @Override
    public Person deletePerson(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            if(p == null) {
                throw new PersonNotFoundException("Person doesn't exist");
            }
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
           em.close();
        }
        
    }

    @Override
    public Person addPerson(String email, String fname, String lname) throws MissingInputException {
        EntityManager em = emf.createEntityManager();
        if(email == null || fname == null || lname == null 
                || email == "" || fname == "" || lname == "") {
            throw new MissingInputException("Missing input");
        }
        Person person = new Person(email, fname, lname);
        int id = 0;
        try{
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
            
        } finally {
            em.close();
        }
        return person;
    }

    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Person> all = em.createQuery("select p from Person p", Person.class).getResultList();
            return all;
        } finally {
            em.close();
        }
    }
    
    

}
