package rest;

import dto.PersonDTO;
import dto.PersonsDTO;
import entities.Address;
import entities.Person;
import errorhandling.MissingInputException;
import facades.PersonFacade;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import errorhandling.PersonNotFoundException;
import utils.EMF_Creator;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//Todo Remove or change relevant parts before ACTUAL use

@OpenAPIDefinition(
        info = @Info(
                title = "Simple persons API",
                version = "0.4",
                description = "Simple API to get info about persons.",
                contact = @Contact(name = "Ditlev Andersen", email = "")
        ),
        tags = {
            @Tag(name = "person", description = "API related to person Info")

        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/jpareststarter"
            ),
            @Server(
                    description = "Server API",
                    url = "http://idon.dk/CA2"
            )

        }
)

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
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get Movie info by ID",
            tags = {"movie"},
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Requested Movie"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Movie not found")})
    public String getPersonFromID(@PathParam("id") int id) throws PersonNotFoundException{
         PersonDTO dto = new PersonDTO(FACADE.getPersonFromID(id));
         return GSON.toJson(dto);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String savePerson(String person) throws MissingInputException {
        PersonDTO persondto = GSON.fromJson(person, PersonDTO.class);
//        Person added = FACADE.addPerson(persondto.getfName(),
//                persondto.getlName(),
//                persondto.getStreet(), persondto.getCity(), persondto.getZip());
        return GSON.toJson(persondto);
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Operation(summary = "Get all persons",
            tags = {"person"},
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Requested Movie"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Movie not found")})
    public String allPersons() {
        return GSON.toJson(new PersonsDTO(FACADE.getAllPersons()));
    }

    @GET
    @Path("allZip")
    @Produces({MediaType.APPLICATION_JSON})
    public String allZip() {
        List<Address> getAll = FACADE.getAllZipcodes();
        String g = GSON.toJson(getAll);
        return g;
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
        //return allPersonwithZip;
    }

    @Path("/countofPeopleHobby/{Hobby}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})

    public int countofPeopleHobby(@PathParam("Hobby") String Hobby) {
        int count = FACADE.countPersonsWithHobby(Hobby);

        //System.out.println("--------------->"+count);
        return count;
    }

//    @Path("/allZip")
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public List allZip() {
//
//        List allzip = FACADE.getAllZipcodes();
//        return allzip;
//    }
    //denne virker
    @Path("/getAddresFromPhone/{phone}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})

    public List<Address> getAddresFromPhone(@PathParam("phone") String phone) {

        List getAll = FACADE.getAddresFromPhone(phone);
        return getAll;
    }

}
