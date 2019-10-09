package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.PersonDTO;
import entities.Person;
import errorhandling.MissingInputException;
//import entities.RenameMe;
import utils.EMF_Creator;
import facades.PersonFacade;
import java.util.List;
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
@Path("person")
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

//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getPerson(@PathParam("id") int id) {
//        return GSON.toJson(new PersonDTO("Hans", "Hansen", "Lyng", "Holte", "2340", "45609675", "tennis"));
//    }
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String savePerson(String person) throws MissingInputException {
        PersonDTO persondto = GSON.fromJson(person, PersonDTO.class);
        Person added = FACADE.addPerson(persondto.getfName(),
                persondto.getlName(),
                 persondto.getStreet(), persondto.getCity(), persondto.getZip());
        return GSON.toJson(persondto);
    }

    @Path("/allPersonsHobby/{Hobby}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPersonsHobby(@PathParam("Hobby") String hobby) {

        List<Person> hobbylist = FACADE.getPersonsWithHobby(hobby);
        return GSON.toJson(hobbylist);
    }

    @Path("/allPersonsCity/{zip}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPersonsCity(@PathParam("zip") String zip) {
        List allPersonwithZip = FACADE.getAllPersonWithZipcode(zip);
        return GSON.toJson(allPersonwithZip);
    }

    @Path("/countofPeopleHobby/{Hobby}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String countofPeopleHobby(@PathParam("Hobby") String Hobby) {
        int count = FACADE.countPersonsWithHobby(Hobby);

        //System.out.println("--------------->"+count);
        return GSON.toJson(count);
    }

    @Path("/allZip")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allZip() {

        List getAll = FACADE.getAllZipcodes();
        return GSON.toJson(getAll);
    }

    @Path("/getAddresFromPhone/{phone}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAddresFromPhone(@PathParam("phone") String phone) {

        List getAll = FACADE.getAddresFromPhone(phone);
        return GSON.toJson(getAll);
    }

}
