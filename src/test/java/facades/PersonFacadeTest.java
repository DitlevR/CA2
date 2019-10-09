package facades;

import entities.Address;
import entities.Hobby;
import utils.EMF_Creator;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.Settings;
import utils.EMF_Creator.DbSelector;
import utils.EMF_Creator.Strategy;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonFacade facade;
    private static Person p1, p2, p3, p4;
    private static List<Person> all = new ArrayList();
    private static List<Address> address = new ArrayList();
    private static List<Phone> phones = new ArrayList();
    private static List<Hobby> hobbies = new ArrayList();

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getFacadeExample(emf);

    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @Disabled
//    @BeforeAll
    public static void setUpClassV2() {
        p1 = new Person("Hans@mail.dk", "Hans", "Hansen");
        p2 = new Person("Jens@mail.dk", "Jens", "Jensen");
        p3 = new Person("John@mail.dk", "John", "Johnson");
        p4 = new Person("Anders@mail.dk", "Anders", "Andersen");

        Phone ph1 = new Phone("12345678", "Til alarmcentralen");
        Phone ph2 = new Phone("99999999", "Til himmelen");
        Phone ph3 = new Phone("77777777", "Til jesus");
        Phone ph4 = new Phone("88888888", "Til Leasy");

        Hobby h1 = new Hobby("Svømning", "man svømmer");
        Hobby h2 = new Hobby("Klatring", "man klatrer");
        Hobby h3 = new Hobby("Tegning", "man tegner");
        Hobby h4 = new Hobby("Sang", "man synger");

        Address a1 = new Address("Parkvej", "Der ligger en mose i området", "1234", "Brovst");
        Address a2 = new Address("Jagtvej", "ungdomshuset lå engang her", "1234", "Brovst");
        Address a3 = new Address("Kongevejen", "Går over Geels bakken", "2830", "Lyngby");
        Address a4 = new Address("Nørrebrogade", "Her ligger 3 spa", "4600", "København");

        ph1.setP(p1);
        ph2.setP(p2);
        ph3.setP(p3);
        ph4.setP(p4);

        p1.setA(a1);
        p2.setA(a2);
        p3.setA(a3);
        p4.setA(a4);

        p1.setHobby(h1);
        p2.setHobby(h1);
        p1.setHobby(h2);
        p3.setHobby(h3);
        p3.setHobby(h2);

        all.add(p1);
        all.add(p2);
        all.add(p3);
        all.add(p4);
        address.add(a1);
        address.add(a2);
        address.add(a3);
        address.add(a4);
        phones.add(ph1);
        phones.add(ph2);
        phones.add(ph3);
        phones.add(ph4);
        hobbies.add(h1);
        hobbies.add(h2);
        hobbies.add(h3);
        hobbies.add(h4);

//        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
//        facade = PersonFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @Disabled
//    @BeforeEach
    public void setUp() {

        EntityManager em = emf.createEntityManager();
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.DROP_AND_CREATE);

        try {
            em.getTransaction().begin();

//            em.createNamedQuery("Phone.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Hobby.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Address.deleteAllRows").executeUpdate();
//            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            for (Address a : address) {

                em.persist(a);

            }

            for (Person p : all) {

                em.persist(p);

            }
            for (Hobby h : hobbies) {

                em.persist(h);

            }
            for (Phone p : phones) {

                em.persist(p);

            }
            em.getTransaction().commit();

            System.out.println(all.toString());

        } finally {
            em.close();
        }
    }

    @Disabled
    @Test
    public void getAddressFromPhoneTest() {
        Assertions.assertNotNull(p1);
    }

//    @Test
//    public void getHobbiesFromPhoneTest() {
//
//    }
//
//    @Test
//    public void getPersonsWithHobby() {
//
//    }
//
//    @Test
//    public void countPersonWithHobbyTest() {
//
//    }
//
//    @Test
//    public void getAllZipcodesTest() {
//
//    }
//
//    @Test
//    public void editPersonTest() {
//
//    }
//
//    @Test
//    public void testAddPersonWithNoName() {
//
//    }
//
//    @Test
//    public void deletePersonDontExistTest() {
//
//    }
    @Disabled
    @Test
    public void testGetAll() {
        List<Person> all = facade.getAllPersons();
        assertEquals(4, all.size());

    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    // TODO: Delete or change this method 
//    @Test
//    public void testAFacadeMethod() {
//        assertEquals(2, facade.getRenameMeCount(), "Expects two rows in the database");
//    }
}
