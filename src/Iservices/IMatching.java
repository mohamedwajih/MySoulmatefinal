/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Entities.Matching;
import java.util.List;

/**
 *
 * @author Fatma
 */
public interface IMatching {
     public void ajouter(Matching m);
    public List<Matching> listerAll();
    public List<Matching> listerOne(int id);
    public List<Matching> match(int id);
   public int nbpourcentage(int nb);
}
