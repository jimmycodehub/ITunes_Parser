package dao;


import entity.Library;
import entity.User;
import persistence.PersistenceUtil;

public class LibraryDAO {



    public void createLibrary(Library library){

        PersistenceUtil.persist(library);
        System.out.println("Library created");

    }
}
