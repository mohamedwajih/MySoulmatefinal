/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;


import Entities.Place;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IPlace {
    
    public void ajouter(Place m);
    public void supprimer(Place m);
    public void modifier(Place m);
    public ArrayList<Place> lister();
    public Place chercher(int id);
    public boolean rechercher(Place m);
    public ArrayList<Place> rechercher(String s);
}
