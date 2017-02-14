package dao;

import entity.Library;
import entity.Track;
import entity.User;
import persistence.PersistenceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class TrackDAO {

    public void createTrack(Track track) {

        PersistenceUtil.persist(track);
        System.out.println("Track created + populated");
    }

    public List<Track> findByLibrary(Library library){
        EntityManager em = PersistenceUtil.createEM();
        List<Track> tracks = (List<Track>)
        em.createNamedQuery("Track.findByLibrary").setParameter("library", library).getResultList();
        em.close();
        return tracks;
    }

    public List<Track> findAll(){
        EntityManager em = PersistenceUtil.createEM();
        List<Track> tracks = (List<Track>)
                em.createNamedQuery("Track.findAll").getResultList();
        em.close();
        return tracks;
    }
}