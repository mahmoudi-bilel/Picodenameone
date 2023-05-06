package com.webwizads.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.webwizards.entities.Publication;
import com.webwizards.services.ServicePublication;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Khalil
 */
public class ShowPublications extends Form{
    
 private TextField searchField;
     private ComboBox<String> sortComboBox;

    
 public ShowPublications(){

    List<Publication> list = ServicePublication.getinstance().show();
        setTitle("Publications");
        setLayout(BoxLayout.y());
        
        
        // Ajout du champ de recherche
        searchField = new TextField("", "Rechercher une publication");
        searchField.addDataChangeListener((i, ii) -> {
            search(searchField.getText());
        });
        this.add(searchField);
        
        
           // Ajout du champ de tri
        sortComboBox = new ComboBox<>("Trier par", "date de publication", "Titre");
        sortComboBox.addActionListener((e) -> {
            sort(sortComboBox.getSelectedItem());
        });
        this.add(sortComboBox);
        
        
        for (Publication c :list)
        {
            this.add(create(c));
        }
        Button btnajout = new Button("Nouvelle publication!");
        btnajout.addActionListener(eva->{
        Form addpub = new Addpub();
        addpub.show();
        });
        this.add(btnajout);
    } 
 
 
  public Container create(Publication p) {
    Label Id = new Label("id =" + p.getId());
    Id.setHidden(true);

    Label Auteur = new Label("Auteur: " + p.getAuteur());
    Label titre = new Label("titre: " + p.getTitre());
    Label Contenu = new Label("Contenu: " + p.getContenu());
    Label image = new Label("image: " + p.getImage());
    Label datePub = new Label("Date de publication: " + p.getDatepub());
        Label likesLabel = new Label("Likes: " + p.getLikes()); 
    Label dislikesLabel = new Label("Dislikes: " + p.getDislike()); 

    Label likes = new Label("Likes=" + p.getLikes()); 
    Label dislike = new Label("Likes=" + p.getDislike());

    Button likeBtn = new Button("Like");
    Button dislikeBtn = new Button("Dislike");

    // Set the action listeners for the like/dislike buttons
    likeBtn.addActionListener(e -> {
        // Increment the like count for the publication and update the UI
        ServicePublication.getinstance().like(p);
        Dialog.show("Liked", "Vous avez réagit a cette publication!", "OK", null);
        Form listform = new ShowPublications();
        listform.show();
    });

    dislikeBtn.addActionListener(e -> {
        // Increment the dislike count for the publication and update the UI
        ServicePublication.getinstance().dislike(p);
        Dialog.show("Disliked", "Vous avez réagit a cette publication!", "OK", null);
        Form listform = new ShowPublications();
        listform.show();
    });

    Button modif = new Button("modifier");
    Button supp = new Button("supprimer");
    modif.addActionListener(ev -> {
         Form modifform=new UpdatePub(p);
         modifform.show();
    });
    supp.addActionListener(e -> {
         ServicePublication.getinstance().delete(p);
        Dialog.show("succes", "supprimer avec succes", "ok", "cancel");
        Form listform = new ShowPublications();
        listform.show();
    });

    return new Container(BoxLayout.yCenter())
            .addAll(Id, Auteur, titre, Contenu, image, datePub,likesLabel,dislikesLabel, likeBtn, dislikeBtn, modif, supp);
}

    
      // Fonction de recherche
    public void search(String text) {
        List<Publication> list = ServicePublication.getinstance().show();
        this.removeAll();
        this.add(searchField);

        for (Publication p : list) {
            if (p.getTitre().toLowerCase().contains(text.toLowerCase())) {
                this.add(create(p));
            }
        }
        this.revalidate();
    }
    
            // Fonction de tri
    public void sort(String sortBy) {
        List<Publication> list = ServicePublication.getinstance().show();
        this.removeAll();
        this.add(searchField);
        this.add(sortComboBox);

        if (sortBy.equals("date de publication")) {
            Collections.sort(list, new Comparator<Publication>() {
                @Override
                public int compare(Publication p1, Publication p2) {
                    return p1.getDatepub().compareTo(p2.getDatepub());
                }
            });
        } else if (sortBy.equals("titre")) {
            Collections.sort(list, new Comparator<Publication>() {
                @Override
                public int compare(Publication p1, Publication p2) {
                    return p1.getTitre().compareTo(p2.getTitre());
                }
            });
        } 
        

        for (Publication p : list) {
            this.add(create(p));
        }
        this.revalidate();
    }
    
}
