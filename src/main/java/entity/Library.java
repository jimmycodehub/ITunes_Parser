package entity;

import javax.persistence.*;

@Entity
public class Library {

    //every entity requires an id, and we can make it auto generated
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String libPersistenceId;
    @ManyToOne
    User user;

    public Library() {
    }

    public Library(String libPersistenceId, User user) {
        this.libPersistenceId = libPersistenceId;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibPersistenceId() {
        return libPersistenceId;
    }

    public void setLibPersistenceId(String libPersistenceId) {
        this.libPersistenceId = libPersistenceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}