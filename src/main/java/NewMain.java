
import entities.Address;
//import entities.CityInfo;
import entities.Hobby;
import entities.Person;
import entities.Phone;
import facades.PersonFacade;
import java.util.List;
import java.util.stream.DoubleStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
    
     public static final PersonFacade FACADE =  PersonFacade.getFacadeExample(EMF);

    public static void main(String[] args) {
        Persistence.generateSchema("pu", null);
        EntityManager em = EMF.createEntityManager();
        
//        System.out.println(FACADE.getAddresFromPhone("12345678"));
        System.out.println(FACADE.getPersonsWithHobby("Svømning"));

//        try {
//            em.getTransaction().begin();
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
    }

}
