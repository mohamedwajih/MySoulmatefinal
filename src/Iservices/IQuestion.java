/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Entities.Preference;
import Entities.Question;
import java.util.List;

/**
 *
 * @author Fatma
 */
public interface IQuestion {
    public boolean ajouter(Question q);
     public List<Question> listerAll();
     public List<Question> listerTrait(String trait);
    public Question listerOne(String id);
    public List<Question> listerDifferent(String question);
     public List<Question> listerDeux(String trait, String type);
    public void supprimer(String q);
    public void modifier(Question newq, String ancien);
    public List<Question> rechercher(String question);
  public List<Question> TestPersonnalite ();
  public List<Question> TestPreference ();
  
}
