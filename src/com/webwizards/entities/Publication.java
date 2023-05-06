/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webwizards.entities;

import com.codename1.ui.List;

/**
 *
 * @author Khalil
 */
public class Publication { 
    
    private int id; 
    private String datepub ; 
    private String auteur; 
    private String contenu; 
    private String image;  
    private String titre;
    private int likes=0; 
    private int dislike=0;  
    private int nbsignal=0; 
    private List<Commentaire> commentaires;

    public Publication() {
    }
    
    

    public Publication(int id, String datepub, String auteur, String contenu, String image, List<Commentaire> commentaires) {
        this.id = id;
        this.datepub = datepub;
        this.auteur = auteur;
        this.contenu = contenu;
        this.image = image;
        this.commentaires = commentaires;
    }

    public Publication(int id, String datepub, String auteur, String contenu, String image, String titre) {
        this.id = id;
        this.datepub = datepub;
        this.auteur = auteur;
        this.contenu = contenu;
        this.image = image;
        this.titre = titre;
    }
    
     public Publication(int id, String datepub, String auteur, String contenu, String image, String titre,int likes, int dislike) {
        this.id = id;
        this.datepub = datepub;
        this.auteur = auteur;
        this.contenu = contenu;
        this.image = image;
        this.titre = titre;
        this.likes = likes;
        this.dislike = dislike;
    }

    public Publication(String auteur, String contenu, String titre) {
        this.auteur = auteur;
        this.contenu = contenu;
        this.titre = titre;
    }
    
    public Publication(int id,String auteur, String contenu, String titre) {
        this.id= id;
        this.auteur = auteur;
        this.contenu = contenu;
        this.titre = titre;
    }
    
    
    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatepub() {
        return datepub;
    }

    public void setDatepub(String datepub) {
        this.datepub = datepub;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

  public int getDislike() {
    return this.dislike;
}
   
    public void setDislike(int par) {
     this.dislike=dislike;
}
   

    public int getNbsignal() {
        return nbsignal;
    }

    public void setNbsignal(int nbsignal) {
        this.nbsignal = nbsignal;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

  
    
    
}
