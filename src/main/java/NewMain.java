
import facades.PersonFacade;
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
            //            "jdbc:mysql://localhost:3307/Persons",
            //            "dev",
            //            "ax2",
            EMF_Creator.Strategy.CREATE); //DROP_AND_CREATE
    //public static final PersonFacade FACADE = PersonFacade.getPersonFacade(EMF);

    public static void main(String[] args) {
            Persistence.generateSchema("pu", null);
    }

}
