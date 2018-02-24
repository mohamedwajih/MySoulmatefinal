/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Entities.Matching;
import Entities.Personnalite;
import Entities.Preference;
import java.util.List;

/**
 *
 * @author Fatma
 */
public interface IPreference {
     
    public void ajouter(Preference p);
     public List<Preference> listerAll();
    public Preference listerOne(int id);
    public List<Preference> listerDifferent(int id);
     public boolean ajouterPourcentage (String trait, int nb, int idUser);
}
