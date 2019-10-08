package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import entities.RenameMe;
import utils.EMF_Creator;
import facades.PersonFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("api")
public class PersonResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/CA2",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final PersonFacade FACADE = PersonFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getperson() {
        return "{\"msg\":\"Hello form person person\"}";
    }

    @Path("/allPersonsHobby")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPersonsHobby() {

        //System.out.println("--------------->"+count);
        return "{\"msg\":\"Hello form person person hobby\"}";
    }

    @Path("/allPersonsCity")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPersonsCity() {

        //System.out.println("--------------->"+count);
        return "{\"msg\":\"Hello form person person city\"}";
    }

    @Path("/countofPeopleHobby")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String countofPeopleHobby() {

        //System.out.println("--------------->"+count);
        return "{\"msg\":\"Hello form person person hobby count\"}";
    }

    @Path("/allZip")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allZip() {

        //System.out.println("--------------->"+count);
        return "{\"msg\":\"Hello form all zip codes\"}";
    }

}
