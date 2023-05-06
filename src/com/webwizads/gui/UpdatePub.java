/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webwizads.gui;

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
public class UpdatePub extends Form{
    ServicePublication sp = new ServicePublication();
    public UpdatePub(Publication p){
        setTitle("Editer Publication");
        setLayout(BoxLayout.y());
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        new ShowPublications().showBack();
        });
        
        TextField auteur=new TextField(p.getAuteur(),"Auteur:");
        TextField titre=new TextField(p.getTitre(),"Titre:");
        TextField contenu=new TextField(p.getContenu(),"Contenu");
        
        
         Button btnvalidator = new Button("Editer");
         btnvalidator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                sp.modifierPublication(new Publication(p.getId(),auteur.getText(),contenu.getText(),titre.getText()));
                    Dialog.show("success", "Connection acceptée",  new Command("ok"));
                
                     new ShowPublications().show();   
                    
                }


          
         
        });
                 this.addAll(btnvalidator,auteur,titre,contenu);
                 
    }

    
}
