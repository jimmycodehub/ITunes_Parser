package parser;

import entity.Playlist;
import entity.Track;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public Parser(){
    }

    public ArrayList<Track> parseTracks(String xml){

        ArrayList<Track> songArrayList = new ArrayList<Track>();

        try{
            File xmlFile = new File(xml);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();


            XPath xp = XPathFactory.newInstance().newXPath();
            String expression = "//dict/key[.='Tracks']/following-sibling::*[1]/child::*";
            NodeList nodeList = (NodeList)xp.compile(expression).evaluate(doc, XPathConstants.NODESET);

            NodeList eachTrack = null;
            System.out.println("node list length = "+nodeList.getLength());

            for(int i  = 0;i<nodeList.getLength();i++){


                Track aTrack = new Track();

                if(nodeList.item(i).getNodeName().equalsIgnoreCase("key")){

                    eachTrack=(NodeList) nodeList.item(i).getNextSibling().getNextSibling().getChildNodes();

                    String name="";
                    String artist="";
                    String trackId="";
                    String album="";
                    String genre="";
                    String year="";
                    String persistentID="";
                    for(int j = 0; j < eachTrack.getLength();j++){
                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Name")){
                            name = eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Track Name "+name);
                            aTrack.setSong(name);
                        }


                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Artist")){
                            artist = eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Artist "+eachTrack.item(j).getNextSibling().getTextContent());
                            aTrack.setArtist(artist);
                        }

                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Track ID")){
                            trackId = eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Track ID "+eachTrack.item(j).getNextSibling().getTextContent());
                            aTrack.setTrackId(trackId);
                        }

                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Album")){
                            album= eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Album "+eachTrack.item(j).getNextSibling().getTextContent());
                            aTrack.setAlbum(album);

                        }

                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Genre")){
                            genre = eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Genre "+eachTrack.item(j).getNextSibling().getTextContent());
                            aTrack.setGenre(genre);

                        }

                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Year")){
                            year=eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Year "+eachTrack.item(j).getNextSibling().getTextContent());
                            aTrack.setYear(year);

                        }
                        if(eachTrack.item(j).getTextContent().equalsIgnoreCase("Persistent Id")){
                            persistentID=eachTrack.item(j).getNextSibling().getTextContent();
                            System.out.println("Persistent Id "+eachTrack.item(j).getNextSibling().getTextContent());
                            aTrack.setPersistentId(persistentID);
                        }
                    }
                }
                if(aTrack.getSong()!=null){
                    songArrayList.add(aTrack);
                    System.out.println("\n----------------------\n");

                }


            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for(int i = 0; i <songArrayList.size();i++){
            System.out.println(songArrayList.get(i).toString());
        }//end for

        return songArrayList;
    }//end parse

    public List<Playlist> parsePlaylist(String xmlString, List<Track> tracks) throws Exception{

        ArrayList<Track> trackList = new ArrayList<>();
        ArrayList<Playlist> playlists = new ArrayList<>();
        Playlist playlist;


        File fXmlFile = new File(xmlString);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            String nameExpression = "//dict/key[. = 'Playlists']/following-sibling::*[1]/child::*";
            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nodeList = (NodeList) xPath.compile(nameExpression).evaluate(doc, XPathConstants.NODESET);

            for (int i = 0; i < nodeList.getLength(); i++) {
                playlist = new Playlist();
                Node playlistNode = nodeList.item(i);

                if (playlistNode.getNodeName().equals("dict")) {

                    NodeList playlistsNode = playlistNode.getChildNodes();
                    for (int a = 0; a < playlistsNode.getLength(); a++) {
                        Node another = playlistsNode.item(a);

                        if (another.getTextContent().equals("Name")) {

                            playlist.setName(another.getNextSibling().getTextContent());
                        } else if (another.getTextContent().equals("Name")) {

                            playlist.setPlaylistId(another.getNextSibling().getTextContent());
                        } else if (another.getTextContent().equals("Playlist ID")) {

                            playlist.setPlaylistId(another.getNextSibling().getTextContent());
                        } else if (another.getTextContent().equals("Playlist Persistent ID")) {

                            playlist.setPlaylistPersistentId(another.getNextSibling().getTextContent());

                        } else if (another.getTextContent().equals("Playlist Items")) {

                            NodeList items = another.getNextSibling().getNextSibling().getChildNodes();

                            for (int x = 0; x < items.getLength(); x++) {


                                Node xitem = items.item(x);
                                NodeList mostItems = xitem.getChildNodes();

                                for (int h = 0; h < mostItems.getLength(); h++) {

                                    Node something = mostItems.item(h);
                                    if (something.getTextContent().equals("Track ID")) {
                                        String current = something.getNextSibling().getTextContent();

                                        for (Track t : tracks) {

                                            //System.out.println("The track id is : " + t.getTrackId());
                                            if (current.equals(t.getTrackId())) {
                                                trackList.add(t);
                                            }
                                        }
                                        playlist.setTracks(trackList);
                                    }

                                }
                            }//System.out.println(trackList);
                            playlists.add(playlist);
                        }
                    }
                }
            }
            System.out.println("Playlist array size is: " + playlists.size());

            for (Playlist p : playlists) {
                System.out.println(p.getName());
                System.out.println(p.getPlaylistPersistentId());
                System.out.println(p.getTracks());
                System.out.println("");
            }
        } catch (ParserConfigurationException | IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return playlists;
    }

}//end class