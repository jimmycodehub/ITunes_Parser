package dao;


import entity.Library;
import entity.Playlist;
import entity.Track;
import entity.User;
import persistence.PersistenceUtil;

public class PlaylistDAO {


    public void createPlaylist(Playlist playlist){

        PersistenceUtil.merge(playlist);
        System.out.println("Playlist Created");

    }
}
