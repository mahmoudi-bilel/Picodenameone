/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.webwizads.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.webwizards.entities.Publication;
import com.webwizards.services.ServicePublication;

/**
 *
 * @author Khalil
 */
public class Addpub extends Form {
    ServicePublication sp = new ServicePublication();
    
    
    public Addpub() {
        setTitle("Ajouter Publication");
        setLayout(BoxLayout.y()); 
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        new ShowPublications().showBack();
        });
        
        TextField auteur = new TextField("", "Auteur:*");
        TextField titre = new TextField("", "Titre:*");
        TextField contenu = new TextField("", "Contenu:*");
        
        Button btnvalidator = new Button("Ajouter Publication");
        btnvalidator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (auteur.getText().equals("") || titre.getText().equals("") || contenu.getText().equals("")) {
                    Dialog.show("Erreur", "Veuillez remplir tous les champs.", new Command("OK"));
                } else {
                    sp.ajouterPublication(new Publication(auteur.getText(), contenu.getText(), titre.getText()));
                    Dialog.show("Success", "La publication a été ajoutée avec succès.", new Command("OK"));
                    new ShowPublications().show();
                }
            }
        });
        
        this.addAll(btnvalidator, auteur, titre, contenu);
    }
}
