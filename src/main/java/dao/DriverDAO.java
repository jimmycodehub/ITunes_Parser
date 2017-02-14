package dao;

import entity.Library;
import entity.Playlist;
import entity.Track;
import entity.User;
import parser.Parser;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {

    Parser parser = new Parser();
    List<Track> tracks = new ArrayList<>();
    List<Playlist> playlists = new ArrayList<>();
    //List<Track> tracks = new ArrayList<Track>();
    UserDAO userDAO = new UserDAO();
    LibraryDAO libraryDAO= new LibraryDAO();
    TrackDAO trackDAO= new TrackDAO();
    PlaylistDAO playlistDAO= new PlaylistDAO();

    public void createRelationships() throws Exception {

        User user = new User("Jim", "jim@jim.com", "pass");
        Library library = new Library("12345", user);

        userDAO.createUser(user);
        libraryDAO.createLibrary(library);


        tracks = (parser.parseTracks("/Users/jamesmcgovern/Documents/College/DistributedSystems/DistributedAssignment/src/main/java/xml_files/ss.xml"));
        for(Track t : tracks){
            t.setLibrary(library);
            trackDAO.createTrack(t);
        }

        List<Track> trackList = trackDAO.findByLibrary(library);

        playlists = parser.parsePlaylist("/Users/jamesmcgovern/Documents/College/DistributedSystems/DistributedAssignment/src/main/java/xml_files/ss.xml", trackList);

        for (Playlist p: playlists ){
            p.setLibrary(library);
            playlistDAO.createPlaylist(p);
        }

    }

}