package facades;

//import entities.RenameMe;
import dto.PersonDTO;
import entities.Address;
//import entities.CityInfo;
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
                EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();    
             List<Person> personWithHobby = em.createQuery("SELECT person FROM Person person JOIN PER_HOB hobbies WHERE hobbies.id = :id").getResultList();
             em.getTransaction().commit();
             return personWithHobby;
         } finally {
             em.close();
         }
    }

    
    //virker ikke helt endnu
    @Override
    public List<Person> getAllPersonWithZipcode(String zip) {
         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             List<Person> personWithZip = em.createQuery("Select p from Person p JOIN p.address_id a").getResultList();
             em.getTransaction().commit();
             return personWithZip;
         } finally {
             em.close();
         }
    }

    //skal lige konfigureres i forhold til hobby listen
    @Override
    public Integer countPersonsWithHobby(String hobby) {
         EntityManager em = emf.createEntityManager();
         try {
             em.getTransaction().begin();
             int count = (Integer)em.createQuery("select COUNT(p) from").getSingleResult();
             return count;
         } finally {
             em.close();
         }
    }

    @Override
    public List<Address> getAllZipcodes() {
        EntityManager em = emf.createEntityManager();
        
        try {
          em.getTransaction().begin();
          List<Address> allCities = em.createQuery("Select a from Address a", Address.class).getResultList();
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
    public Person addPerson(String fname, String lname, String street, String city, String zip) throws MissingInputException {
        EntityManager em = emf.createEntityManager();
        if(fname == null || lname == null 
                || fname == "" || lname == "") {
            throw new MissingInputException("Missing input");
        }
        Person person = new Person();
        int id = 0;
        person.setfName(fname);
        person.setlName(lname);
        person.getAddress().setCity(city);
        person.getAddress().setZipCode(zip);
        person.getAddress().setStreet(street);
        
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
