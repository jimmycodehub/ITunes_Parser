package dao;


import entity.Library;
import entity.Track;
import entity.User;
import persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDAO {

    public void createUser(User user){

        PersistenceUtil.persist(user);
        System.out.println("User created");

    }

    public List<User> findAllUsers(){
        EntityManager em = PersistenceUtil.createEM();
        List<User> users = (List<User>)
                em.createNamedQuery("User.findAllUsers").getResultList();
        em.close();
        return users;
    }

     public User findUserByUsernameAndPassword(String username, String password){
        EntityManager em = PersistenceUtil.createEM();
        List<User> users = (List<User>)
                em.createNamedQuery("User.findUserByUsernameAndPassword").setParameter("username", username).setParameter("password", password).getResultList();
        em.close();
        return users.get(0);
    }



}
