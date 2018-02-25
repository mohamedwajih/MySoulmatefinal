/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Entities.Matching;
import Entities.Personnalite;
import java.util.List;

/**
 *
 * @author Fatma
 */
public interface IPersonnalite {
     public void ajouter(Personnalite p);
     public List<Personnalite> listerAll();
    public Personnalite listerOne(int id);
    public List<Personnalite> listerDifferent(int id);
    public boolean ajouterPourcentage (String trait, int nb, int idUser);
     public float moyenne(String trait);
     public int nbpourcentagetrait(String trait, int nb);
    
}
