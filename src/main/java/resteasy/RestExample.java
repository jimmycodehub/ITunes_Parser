package resteasy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DriverDAO;
import dao.TrackDAO;
import dao.UserDAO;
import entity.Track;
import entity.User;
import parser.Parser;

import javax.ws.rs.GET;
import javax.ws.rs.*;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api")
public class RestExample {

    private TrackDAO trackDAO = new TrackDAO();
    private UserDAO userDAO = new UserDAO();
    @GET
    @Path("/createdb")
    public String createDb() throws Exception {


        DriverDAO driverDAO = new DriverDAO();


        driverDAO.createRelationships();
        return "Check tracks in console";
    }


    @GET
    @Path("/getAllTracks")
    @Produces(value={"application/json"})
    public List<Track> getAllTracks(){

        List<Track> tracks = trackDAO.findAll();
        return tracks;
    }

    @GET
    @Path("/getAllUsers")
    @Produces(value={"application/json"})
    public List<User> getAllUsers(){

        List<User> users = userDAO.findAllUsers();
        return users;
    }


    @POST
    @Path(value="/getUser")
    @Produces(value={"application/json"})
    public User getUser(String userJson){

        System.out.println("Ive been hit");
        System.out.println(userJson);
        System.out.println("After printing report");
        User user = new User();
        Map<String, String> map = new HashMap<String, String>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(userJson, new TypeReference<HashMap<String, String>>() {
            });
            String email = (String) map.get("email");
            String password = (String) map.get("password");
            user = userDAO.findUserByUsernameAndPassword(email, password);
            if(user != null){
                System.out.println(user.toString());
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
