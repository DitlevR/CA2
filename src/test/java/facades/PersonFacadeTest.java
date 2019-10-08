package facades;

import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import utils.EMF_Creator;
import entities.Person;
import entities.Phone;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    private static List<CityInfo> cityInfo = new ArrayList();

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = PersonFacade.getFacadeExample(emf);

    }

    /*   **** HINT **** 
        A better way to handle configuration values, compared to the UNUSED example above, is to store those values
        ONE COMMON place accessible from anywhere.
        The file config.properties and the corresponding helper class utils.Settings is added just to do that. 
        See below for how to use these files. This is our RECOMENDED strategy
     */
    @BeforeAll
    public static void setUpClassV2() {
        p1 = new Person("Hans@mail.dk", "Hans", "Hansen");
        p2 = new Person("Jens@mail.dk", "Jens", "Jensen");
        p3 = new Person("John@mail.dk", "John", "Johnson");
        p4 = new Person("Anders@mail.dk", "Anders", "Andersen");
        p1.setId(1);
        p2.setId(2);
        p3.setId(3);
        p4.setId(4);

        Hobby h1 = new Hobby("Svømning", "man svømmer");
        Hobby h2 = new Hobby("Klatring", "man klatrer");
        Hobby h3 = new Hobby("Tegning", "man tegner");
        Hobby h4 = new Hobby("Sang", "man synger");

        Address a1 = new Address("Parkvej", "Der ligger en mosei området");
        Address a2 = new Address("Jagtvej", "ungdomshuset lå engang her");
        Address a3 = new Address("Kongevejen", "Går over Geels bakken");
        Address a4 = new Address("Nørrebrogade", "Ligger i København");

        

        Phone ph1 = new Phone("12345678", "Til alarmcentralen");
        Phone ph2 = new Phone("99999999", "Til himmelen");
        Phone ph3 = new Phone("77777777", "Til jesus");
        Phone ph4 = new Phone("88888888", "Til Leasy");

        ph1.setP(p1);
        ph2.setP(p1);
        ph3.setP(p2);
        ph4.setP(p2);

        CityInfo ci1 = new CityInfo("1234", "Brovst");
        CityInfo ci2 = new CityInfo("4321", "Aabenraa");
        CityInfo ci3 = new CityInfo("87654", "Allerød");

        
        cityInfo.add(ci1);
        cityInfo.add(ci2);
        cityInfo.add(ci3);
        
        p1.setA(a1);
        p2.setA(a2);
        p3.setA(a3);
        p4.setA(a4);

        a1.setCi(ci1);
        a2.setCi(ci2);
        a3.setCi(ci3);
        a4.setCi(ci1);

        all.add(p1);
        all.add(p2);
        all.add(p3);
        all.add(p4);
        address.add(a1);
        address.add(a2);
        address.add(a3);
        address.add(a4);
        emf = EMF_Creator.createEntityManagerFactory(DbSelector.TEST, Strategy.DROP_AND_CREATE);
        facade = PersonFacade.getFacadeExample(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the script below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {

        EntityManager em = emf.createEntityManager();
        

        try {
             for (CityInfo c : cityInfo) {
                em.getTransaction().begin();
                em.persist(c);
                em.getTransaction().commit();
            }
            
            
            for (Address a : address) {
                em.getTransaction().begin();
                em.persist(a);
                em.getTransaction().commit();
            }

            for (Person p : all) {
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
            }

            System.out.println(all.toString());

        } finally {
            em.close();
        }
    }

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
