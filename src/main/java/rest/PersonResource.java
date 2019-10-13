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
import dto.AddressDTO;
import dto.HobbiesDTO;
import dto.HobbyDTO;
import entities.Hobby;
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
import java.util.ArrayList;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@OpenAPIDefinition(
        info = @Info(
                title = "Person API",
                version = "0.1",
                description = "Simple API to get info about persons.",
                contact = @Contact(name = "Ditlev Andersen", email = "cph-di22@cphbusiness.dk")
        ),
        tags = {
            @Tag(name = "person", description = "API related to person Info")

        },
        servers = {
            @Server(
                    description = "For Local host testing",
                    url = "http://localhost:8080/CA2"
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
    @Operation(summary = "Get Person info by ID",
            tags = {"person"},
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Person.class))),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Requested Movie"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Movie not found")})
    public String getPersonFromID(@PathParam("id") int id) throws PersonNotFoundException {
        PersonDTO dto = new PersonDTO(FACADE.getPersonFromID(id));
        return GSON.toJson(dto);
    }

    @POST
    @Path("add/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String savePerson(@PathParam("id") int id, String person) throws MissingInputException {
        PersonDTO persondto = GSON.fromJson(person, PersonDTO.class);

        Person added = FACADE.addPerson(persondto.getEmail(), persondto.getfName(),
                persondto.getlName(), id);
        return GSON.toJson(new PersonDTO(added));
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
        List<AddressDTO> allDTO = new ArrayList<>();
        for (Address a : getAll) {
            allDTO.add(new AddressDTO(a));
        }
        return GSON.toJson(allDTO);
    }

    @Path("/allPersonsHobby/{Hobby}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPersonsHobby(@PathParam("Hobby") String hobby) {

        List<Person> hobbylist = FACADE.getPersonsWithHobby(hobby);

        return GSON.toJson(new PersonsDTO(hobbylist));
    }

    @Path("/allPersonsCity/{zip}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allPersonsCity(@PathParam("zip") String zip) {
        List allPersonwithZip = FACADE.getAllPersonWithZipcode(zip);
        return GSON.toJson(new PersonsDTO(allPersonwithZip));
        //return allPersonwithZip;
    }

    @Path("/countofPeopleHobby/{Hobby}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})

    public long countofPeopleHobby(@PathParam("Hobby") String Hobby) {
        long count = FACADE.countPersonsWithHobby(Hobby);
        return count;
    }

//denne virker
    @Path("/getAddresFromPhone/{phone}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})

    public String getAddresFromPhone(@PathParam("phone") String phone) {

        List<Address> getAll = FACADE.getAddresFromPhone(phone);
        List<AddressDTO> allDTO = new ArrayList<>();

        for (Address a : getAll) {
            allDTO.add(new AddressDTO(a));
        }
        return GSON.toJson(allDTO);
    }

    @Path("allHobby")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Operation(summary = "Get all Hobbies",
            tags = {"Hobbies"},
            responses = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(
                        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDTO.class))),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "The Requested Movie"),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Movie not found")})
    public String allHobbies() {

        return GSON.toJson(new HobbiesDTO( FACADE.getAllHobbies()));
    }

}
