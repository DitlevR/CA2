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
import entities.Hobby;
import entities.Phone;
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
import javax.persistence.EntityManager;
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
    
    public String createPersons() {
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

        Address a1 = new Address("Parkvej", "Der ligger en mose i omrÂdet", "1234", "Brovst");
        Address a2 = new Address("Jagtvej", "ungdomshuset lÂ engang her", "1234", "Brovst");
        Address a3 = new Address("Kongevejen", "GÂr over Geels bakken", "2830", "Lyngby");
        Address a4 = new Address("N¯rrebrogade", "Her ligger 3 spa", "4600", "K¯benhavn");
        Address a5 = new Address("Holstensvej", "Meget fin", "0001", "Verdens ende");
        Address a6 = new Address("Nordvej", "Dette sted er koldt", "1000", "H¯jt oppe");
        Address a7 = new Address("Bomgade", "Udkig til Japan", "2830", "Virum");
        Address a8 = new Address("OkostrÊde", "Frej helligdom i nÊrheden", "7777", "Anholt");

        ph1.setP(p1);
        ph2.setP(p1);
        ph3.setP(p2);
        ph4.setP(p2);
        ph5.setP(p3);
        ph6.setP(p3);
        ph7.setP(p4);
        ph8.setP(p4);
        ph9.setP(p5);
        ph10.setP(p5);
        ph11.setP(p6);
        ph12.setP(p6);

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

        p1.setHobby(h1);
        p2.setHobby(h1);
        p1.setHobby(h2);
        p3.setHobby(h3);
        p3.setHobby(h2);
        p4.setHobby(h4);
        p5.setHobby(h4);
        p6.setHobby(h1);
        p7.setHobby(h1);
        p8.setHobby(h2);
        p9.setHobby(h3);
        p10.setHobby(h2);
        p12.setHobby(h4);

        p7.setHobby(h2);
        p8.setHobby(h1);
        p10.setHobby(h4);
        p9.setHobby(h2);
        p11.setHobby(h2);
        p8.setHobby(h4);
        p1.setHobby(h4);

        em.getTransaction().begin();

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
        em.close();
        
        return "created";
    }

}
