
import entities.Address;
import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import facades.PersonFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
            EMF_Creator.Strategy.DROP_AND_CREATE); //DROP_AND_CREATE

    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
        EntityManager em = EMF.createEntityManager();

        Person p1 = new Person("fuglen@hotmail.com", "Hanne", "Svendsen");
        Person p2 = new Person("heavymetal@outlook.com", "Thorsen", "Odinsen");
        Person p3 = new Person("himmelen@outlook.com", "Iben", "Asgersen");
        Person p4 = new Person("treetrees@hotmail.com", "Benny", "Baldersen");
        Person p5 = new Person("ørnen@hotmail.com", "Helge", "Svendsen");
        Person p6 = new Person("celticpunk@outlook.com", "Thomsen", "Odinsen");
        Person p7 = new Person("jotunheim@outlook.com", "Ibensen", "Asgersen");
        Person p8 = new Person("bushes@hotmail.com", "Soren", "Baldersen");
        Person p9 = new Person("pingvin@hotmail.com", "Janne", "Svendsen");
        Person p10 = new Person("kolibri@outlook.com", "Anne", "Odinsen");
        Person p11 = new Person("mumle@outlook.com", "Ibensen", "Asgersen");
        Person p12 = new Person("lars@hotmail.com", "Benny", "Baldersen");

        Phone ph1 = new Phone("12345678", "Til alarmcentralen");
        Phone ph2 = new Phone("99999999", "Til himmelen");
        Phone ph3 = new Phone("77777777", "Til jesus");
        Phone ph4 = new Phone("88888888", "Til Leasy");
        Phone ph5 = new Phone("123123456", "Til brandstationen");
        Phone ph6 = new Phone("99999999", "Til hospitalet");
        Phone ph7 = new Phone("77777777", "Til borgmesteren");
        Phone ph8 = new Phone("88888888", "Til Ludvif");
        Phone ph9 = new Phone("12345678", "Til statsministeren");
        Phone ph10 = new Phone("99999999", "Til Valhalla");
        Phone ph11 = new Phone("77777777", "Til Thor");
        Phone ph12 = new Phone("88888888", "Til Ly");

        Hobby h1 = new Hobby("Svømning", "man svømmer");
        Hobby h2 = new Hobby("Klatring", "man klatrer");
        Hobby h3 = new Hobby("Tegning", "man tegner");
        Hobby h4 = new Hobby("Sang", "man synger");

        CityInfo ci1 = new CityInfo("1234", "Brovst");
        CityInfo ci2 = new CityInfo("4321", "Aabenraa");
        CityInfo ci3 = new CityInfo("87654", "Allerød");

        Address a1 = new Address("Parkvej", "Der ligger en mosei området");
        Address a2 = new Address("Jagtvej", "ungdomshuset lå engang her");
        Address a3 = new Address("Kongevejen", "Går over Geels bakken");
        Address a4 = new Address("Nørrebrogade", "Ligger i København");
        Address a5 = new Address("Holstensvej", "Ligger ingensteder");
        Address a6 = new Address("Nordvej", "Ligger ved Nordpolen");
        Address a7 = new Address("Bomgade", "Ligger i Japan");
        Address a8 = new Address("Okostræde", "Ligger i København");

        p1.setA(a1);
        p2.setA(a2);
        p3.setA(a3);
        p4.setA(a4);
        p5.setA(a5);
        p6.setA(a6);
        p7.setA(a7);
        p8.setA(a8);
        p9.setA(a1);
        p10.setA(a2);
        p11.setA(a3);
        p12.setA(a4);

        ph1.setP(p1);
        ph2.setP(p4);
        ph3.setP(p1);
        ph4.setP(p2);
        ph5.setP(p1);
        ph6.setP(p2);
        ph7.setP(p1);
        ph8.setP(p3);
        ph9.setP(p4);
        ph10.setP(p1);
        ph11.setP(p2);
        ph12.setP(p2);

        a1.setCi(ci1);
        a2.setCi(ci2);
        a3.setCi(ci3);
        a4.setCi(ci1);
        a5.setCi(ci2);
        a6.setCi(ci3);
        a7.setCi(ci1);
        a8.setCi(ci2);

        try {
            em.getTransaction().begin();

            em.persist(ci1);
            em.persist(ci2);
            em.persist(ci3);

            em.persist(a1);
            em.persist(a2);
            em.persist(a3);
            em.persist(a4);
            em.persist(a5);
            em.persist(a6);
            em.persist(a7);
            em.persist(a8);

            em.persist(p1);
            em.persist(p2);
            em.persist(p3);
            em.persist(p4);
            em.persist(p5);
            em.persist(p6);
            em.persist(p7);
            em.persist(p8);
            em.persist(p9);
            em.persist(p10);
            em.persist(p12);
            em.persist(p11);

            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.persist(h4);

            em.persist(ph1);
            em.persist(ph2);
            em.persist(ph3);
            em.persist(ph4);
            em.persist(ph5);
            em.persist(ph6);
            em.persist(ph7);
            em.persist(ph8);
            em.persist(ph9);
            em.persist(ph10);
            em.persist(ph11);
            em.persist(ph12);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
