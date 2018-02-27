/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Entities.Fos_user;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IFos_user {

    public void ajouter_utilisateur(Fos_user u);

    public void supprimer_utilisateur(Fos_user u);

    public void modifier_utilisateur(Fos_user u);

    public void ajouter_responsable_event(Fos_user u);

    public void modifier_responsable_event(Fos_user u);

    public void modifier_mdp(Fos_user u);

    public List<Fos_user> afficher_responsable_event();

    public List<Fos_user> afficher_user();

    public List<Fos_user> chercher_user_par_nom_prenom(String nom, String prenom);

    public List<Fos_user> chercher_user_par_nom(String nom);

    public List<Fos_user> chercher_user_par_prenom(String prenom);

    public void activerCompte(int id);

    public boolean Authentifier(String login, String password );

    public boolean verifier_compte(int id);
    
    public boolean verifier_username(String login);
        public boolean verifier_email(String login);
           public void validerCompte(String code);
  public void modifierImage(String image,int id);
  

}
