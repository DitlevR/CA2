
import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
//import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import errorhandling.MissingInputException;
import errorhandling.PersonNotFoundException;
import facades.PersonFacade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.jsp.PageContext;
import utils.EMF_Creator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ludvig
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/CA2",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE); //DROP_AND_CREATE

    public static final PersonFacade FACADE = PersonFacade.getFacadeExample(EMF);

    public static void main(String[] args) throws PersonNotFoundException, MissingInputException {
//        Persistence.generateSchema("pu", null);
        EntityManager em = EMF.createEntityManager();
        
//        Person p1 = new Person("fuglen@hotmail.com", "Hanne", "Svendsen");
//        Person p2 = new Person("heavymetal@outlook.com", "Thorsen", "Odinsen");
//        Person p3 = new Person("himmelen@outlook.com", "Iben", "Asgersen");
//        Person p4 = new Person("treetrees@hotmail.com", "Benny", "Baldersen");
//        Person p5 = new Person("ørnen@hotmail.com", "Helge", "Svendsen");
//        Person p6 = new Person("celticpunk@outlook.com", "Thomsen", "Odinsen");
//        Person p7 = new Person("jotunheim@outlook.com", "Ibensen", "Asgersen");
//        Person p8 = new Person("bushes@hotmail.com", "Soren", "Baldersen");
//        Person p9 = new Person("pingvin@hotmail.com", "Janne", "Svendsen");
//        Person p10 = new Person("kolibri@outlook.com", "Anne", "Odinsen");
//        Person p11 = new Person("mumle@outlook.com", "Ibensen", "Asgersen");
//        Person p12 = new Person("lars@hotmail.com", "Benny", "Baldersen");
//
//        Phone ph1 = new Phone("12345678", "Til alarmcentralen");
//        Phone ph2 = new Phone("99999999", "Til himmelen");
//        Phone ph3 = new Phone("77777777", "Til jesus");
//        Phone ph4 = new Phone("88888888", "Til Leasy");
//        Phone ph5 = new Phone("123123456", "Til brandstationen");
//        Phone ph6 = new Phone("99999999", "Til hospitalet");
//        Phone ph7 = new Phone("77777777", "Til borgmesteren");
//        Phone ph8 = new Phone("88888888", "Til Ludvif");
//        Phone ph9 = new Phone("12345678", "Til statsministeren");
//        Phone ph10 = new Phone("99999999", "Til Valhalla");
//        Phone ph11 = new Phone("77777777", "Til Thor");
//        Phone ph12 = new Phone("88888888", "Til Ly");
//
//        Hobby h1 = new Hobby("Svømning", "man svømmer");
//        Hobby h2 = new Hobby("Klatring", "man klatrer");
//        Hobby h3 = new Hobby("Tegning", "man tegner");
//        Hobby h4 = new Hobby("Sang", "man synger");
//
//        Address a1 = new Address("Parkvej", "Der ligger en mose i omrÂdet", "1234", "Brovst");
//        Address a2 = new Address("Jagtvej", "ungdomshuset lÂ engang her", "1234", "Brovst");
//        Address a3 = new Address("Kongevejen", "GÂr over Geels bakken", "2830", "Lyngby");
//        Address a4 = new Address("N¯rrebrogade", "Her ligger 3 spa", "4600", "K¯benhavn");
//        Address a5 = new Address("Holstensvej", "Meget fin", "0001", "Verdens ende");
//        Address a6 = new Address("Nordvej", "Dette sted er koldt", "1000", "H¯jt oppe");
//        Address a7 = new Address("Bomgade", "Udkig til Japan", "2830", "Virum");
//        Address a8 = new Address("OkostrÊde", "Frej helligdom i nÊrheden", "7777", "Anholt");
//
//        ph1.setP(p1);
//        ph2.setP(p1);
//        ph3.setP(p2);
//        ph4.setP(p2);
//        ph5.setP(p3);
//        ph6.setP(p3);
//        ph7.setP(p4);
//        ph8.setP(p4);
//        ph9.setP(p5);
//        ph10.setP(p5);
//        ph11.setP(p6);
//        ph12.setP(p6);
//
//        p1.setA(a1);
//        p2.setA(a2);
//        p3.setA(a3);
//        p4.setA(a4);
//        p5.setA(a5);
//        p6.setA(a6);
//        p7.setA(a7);
//        p8.setA(a8);
//        p9.setA(a1);
//        p10.setA(a2);
//        p11.setA(a3);
//        p12.setA(a4);
//
//        p1.setHobby(h1);
//        p2.setHobby(h1);
//        p1.setHobby(h2);
//        p3.setHobby(h3);
//        p3.setHobby(h2);
//        p4.setHobby(h4);
//        p5.setHobby(h4);
//        p6.setHobby(h1);
//        p7.setHobby(h1);
//        p8.setHobby(h2);
//        p9.setHobby(h3);
//        p10.setHobby(h2);
//        p12.setHobby(h4);
//
//        p7.setHobby(h2);
//        p8.setHobby(h1);
//        p10.setHobby(h4);
//        p9.setHobby(h2);
//        p11.setHobby(h2);
//        p8.setHobby(h4);
//        p1.setHobby(h4);
//
//        em.getTransaction().begin();
//
//        em.persist(a1);
//        em.persist(a2);
//        em.persist(a3);
//        em.persist(a4);
//        em.persist(a5);
//        em.persist(a6);
//        em.persist(a7);
//        em.persist(a8);
//
//        em.persist(p1);
//        em.persist(p2);
//        em.persist(p3);
//        em.persist(p4);
//        em.persist(p5);
//        em.persist(p6);
//        em.persist(p7);
//        em.persist(p8);
//        em.persist(p9);
//        em.persist(p10);
//        em.persist(p12);
//        em.persist(p11);
//
//        em.persist(h1);
//        em.persist(h2);
//        em.persist(h3);
//        em.persist(h4);
//
//        em.persist(ph1);
//        em.persist(ph2);
//        em.persist(ph3);
//        em.persist(ph4);
//        em.persist(ph5);
//        em.persist(ph6);
//        em.persist(ph7);
//        em.persist(ph8);
//        em.persist(ph9);
//        em.persist(ph10);
//        em.persist(ph11);
//        em.persist(ph12);
//
//        em.getTransaction().commit();
//        em.close();
//        System.out.println(FACADE.getAllZipcodes());
//        System.out.println(FACADE.countPersonsWithHobby("Sang"));
//        System.out.println(FACADE.getAddresFromPhone("12345678"));
//        System.out.println(FACADE.deletePerson(2));
//        List<Person> persons = FACADE.getAllPersons();
//         List<PersonDTO> dtos = new ArrayList();
//        
//        for(Person p : persons) {
//            dtos.add(new PersonDTO(p));
//        }
//        System.out.println(dtos.toArray());

        System.out.println(FACADE.addPerson("email@email.com", "Lars", "Ibsen", 1));
    }
}
