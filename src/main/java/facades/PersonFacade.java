package facades;

//import entities.RenameMe;
import dto.PersonDTO;
import entities.Address;
//import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
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
public class PersonFacade implements PersonInterface {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }

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

    /**
     * EntityManager em = emf.createEntityManager(); try { } finally {
     * em.close(); }
     *
     * @param phone
     */
    @Override
    public List<Address> getAddresFromPhone(String phone) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            List<Address> allAddressFromPhone = em.createQuery("select ph from "
                    + "Phone ph JOIN FETCH ph.p p JOIN FETCH p.a a WHERE "
                    + "ph.number = :number").setParameter("number", phone).getResultList();
            return allAddressFromPhone;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Hobby> getHobbiesFromPhone(String phone) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin(); //"select h from Hobby h JOIN FETCH h.persons p WHERE h.name = :name"
            List<Hobby> hobbiesFromPhone = em.createQuery(
                    "select distinct h from Hobby h JOIN FETCH h.persons p JOIN FETCH p.phones ph WHERE ph.number = :number").setParameter(
                            "number", phone).getResultList();
            return hobbiesFromPhone;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Person> getPersonsWithHobby(String hobby) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin(); //"select h from Hobby h JOIN FETCH h.persons p WHERE h.name = :name"
            List<Person> allPersonWithHobby = em.createQuery(
                    "select p from Person p JOIN FETCH p.hobbies h WHERE h.name = :name").setParameter(
                            "name", hobby).getResultList();
            return allPersonWithHobby;
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
            List<Person> personWithZip = em.createQuery(
                    "Select p from Person p JOIN p.a a WHERE a.zipCode = :zip").setParameter(
                            "zip", zip).getResultList();
            return personWithZip;
        } finally {
            em.close();
        }
    }

    //skal lige konfigureres i forhold til hobby listen
    @Override
    public long countPersonsWithHobby(String hobby) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            long count = (long) em.createQuery(
                    "select COUNT(p) from Person p JOIN FETCH p.hobbies h WHERE h.name = :name").setParameter(
                            "name", hobby).getSingleResult();
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
            List<Address> allCities = em.createQuery("Select a from Address a",
                    Address.class).getResultList();
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
            if (findPerson == null) {
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
            if (p == null) {
                throw new PersonNotFoundException("Person doesn't exist");
            }
            //Query query = em.createQuery("select p FROM Phone p where p.Person_id = : number").setParameter("number", id);

            Query query = em.createQuery(
                    "DELETE FROM Phone p WHERE p.id= :number ");

            query.setParameter("number", id);
            int rowDeleted = query.executeUpdate();
            System.out.println(rowDeleted);
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }

    }

    public Person addPerson(String email, String fname, String lname, int addressId) throws MissingInputException {
        EntityManager em1 = emf.createEntityManager();
        if (email == null || fname == null || lname == null || email == "" || lname == "" || fname == "" || addressId == 0) {
            throw new MissingInputException("Missing input");
        }
        Person person = new Person(email, fname, lname);
        Address address;

        try {
            em1.getTransaction().begin();
            address = em1.find(Address.class, addressId);
            em1.getTransaction().commit();
        } finally {
            em1.close();
        }

        EntityManager em2 = emf.createEntityManager();
        person.setA(address);
        try {

            em2.getTransaction().begin();
            em2.persist(person);
            em2.getTransaction().commit();

        } finally {
            em2.close();
        }
        return person;
    }

//    @Override
//    public Person addPerson(String fname, String lname, String street, String zip, String city) throws MissingInputException {
//        EntityManager em = emf.createEntityManager();
//        if (fname== null || lname == null || lname == "" || fname == "") {
//            throw new MissingInputException("Missing input");
//        }
//        Person person = new Person("", fname, lname);
//        Address address = new Address(street, "", zip, city);
//        person.setA(address);
//
//        int id = 0;
//        try {
//
//            em.getTransaction().begin();
//            em.persist(address);
//            em.persist(person);
//            em.getTransaction().commit();
//
//        } finally {
//            em.close();
//        }
//        return person;
//    }
    @Override
    public List<Person> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Person> all = em.createQuery("select p from Person p",
                    Person.class).getResultList();
            return all;
        } finally {
            em.close();
        }
    }

    @Override
    public Person getPersonFromID(int id) throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person person = em.find(Person.class, id);
            em.getTransaction().commit();
            return person;

        } finally {
            em.close();
        }
    }

    @Override
    public List<Hobby> getAllHobbies() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            List<Hobby> all = em.createQuery("select h from Hobby h",
                    Hobby.class).getResultList();
            return all;
        } finally {
            em.close();
        }
    }
}
