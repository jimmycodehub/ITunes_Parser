package entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries( {
        @NamedQuery(name = "Track.findAll", query = "select o from Track o"),
        @NamedQuery(name = "Track.findByLibrary", query = "select o from Track o where o.library=:library"),
})

@XmlRootElement
@Entity
public class Track {

    //every entity requires an id, and we can make it auto generated
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String genre;
    private String artist;
    private String album;
    private String song;
    private String persistentId;
    private String year;
    private String trackId;

    @ManyToOne
    private Library library;


    public Track(){}

    public Track(String genre, String artist, String album, String song, String persistentId, String year, String trackId, Library library) {
        this.genre = genre;
        this.artist = artist;
        this.album = album;
        this.song = song;
        this.persistentId = persistentId;
        this.year = year;
        this.trackId = trackId;
        this.library = library;
    }

    @XmlElement
    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
    @XmlElement
    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @XmlElement
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @XmlElement
    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @XmlElement
    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    @XmlElement
    public String getPersistentId() {
        return persistentId;
    }

    public void setPersistentId(String persistentId) {
        this.persistentId = persistentId;
    }

    @XmlElement
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
