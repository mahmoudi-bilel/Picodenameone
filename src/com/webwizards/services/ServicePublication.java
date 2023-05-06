/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webwizards.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.webwizards.entities.Publication;
import com.webwizards.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

/**
 *
 * @author Khalil
 */
public class ServicePublication { 
    private ConnectionRequest req;  
    public static ServicePublication instance;
    List<Publication> publications;

    public ServicePublication() {
        req = new ConnectionRequest();
    }
    
    public static ServicePublication getinstance(){
        if(instance == null )
            instance = new ServicePublication();
        return instance;
    }
    
    public List<Publication> parsePublications(String jsonTEXT){
        List<Publication> publications = new ArrayList<Publication>();
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> questionlistjson=j.parseJSON(new CharArrayReader(jsonTEXT.toCharArray()));
            List <Map<String,Object>> list = (List<Map<String,Object>>)questionlistjson.get("root");
            
          for(Map<String,Object> obj : list){
              Publication p = new Publication();
              int id = (int)Float.parseFloat(obj.get("id").toString());
              String titre = obj.get("titre").toString();
              String contenu = obj.get("Contenu").toString();
              String auteur = obj.get("Auteur").toString();  

              String image = obj.get("image").toString(); 
              String datePub = obj.get("datepub").toString();
              int likes = (int) Float.parseFloat(obj.get("likes").toString());
              int dislikes = (int) Float.parseFloat(obj.get("dislike").toString());





              publications.add(new Publication(id,datePub,auteur,contenu,image,titre,likes,dislikes));
          }
            
            
            return publications;
        } catch (IOException ex) {
        }   
        return publications;
}
    
     public List<Publication> show(){
       String url = Statics.BASE_URL+"/publication/list";
       req.setUrl(url);
       req.setInsecure(true);
       req.setPost(false);
       req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt) {
    publications =  parsePublications(new String(req.getResponseData()));
    req.removeResponseListener(this);    
           }
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
       return publications;
   } 

     public void ajouterPublication(Publication p){
            String url=Statics.BASE_URL+"/publication/add?auteur="+p.getAuteur()+"&titre="+p.getTitre()+"&contenu="+p.getContenu()+"&typeID=1&UserID=1";            
            req.setUrl(url);
            req.setInsecure(true);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);}
            });
            
            NetworkManager.getInstance().addToQueueAndWait(req);
            
            
        }
     
     public void delete(Publication p){
            String url=Statics.BASE_URL+"/publication/delete/"+p.getId();            
            req.setUrl(url);
            req.setInsecure(true);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);}
            });
            
            NetworkManager.getInstance().addToQueueAndWait(req);
            
            
        }

    public void modifierPublication(Publication p) {
        String url=Statics.BASE_URL+"/publication/update/"+p.getId()+"?auteur="+p.getAuteur()+"&titre="+p.getTitre()+"&contenu="+p.getContenu()+"&typeID=1&UserID=1";            
            req.setUrl(url);
            req.setInsecure(true);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);}
            });
            
            NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void update(Publication p) {
    }
    
    public void like(Publication p){
        String url=Statics.BASE_URL+"/publication/likeJSON/"+p.getId();            
            req.setUrl(url);
            req.setInsecure(true);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);}
            });
            
            NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public void dislike(Publication p){
        String url=Statics.BASE_URL+"/publication/dislikeJSON/"+p.getId();            
            req.setUrl(url);
            req.setInsecure(true);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);}
            });
            
            NetworkManager.getInstance().addToQueueAndWait(req);
        
    }


    
}
