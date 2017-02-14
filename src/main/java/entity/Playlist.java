package entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Playlist {

    //every entity requires an id, and we can make it auto generated
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;
    private String playlistId;
    private String playlistPersistentId;

    @ManyToOne
    Library library;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    List<Track> tracks;



    public Playlist(String name, String playlistId, String playlistPersistentId, Library library, List<Track> tracks) {
        this.name = name;
        this.playlistId = playlistId;
        this.playlistPersistentId = playlistPersistentId;
        this.library = library;
        this.tracks = tracks;
    }

    public Playlist() {
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistPersistentId() {
        return playlistPersistentId;
    }

    public void setPlaylistPersistentId(String playlistPersistentId) {
        this.playlistPersistentId = playlistPersistentId;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
