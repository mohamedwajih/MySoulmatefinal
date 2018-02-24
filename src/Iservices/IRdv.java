/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;


import Entities.Rdv;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IRdv {
    
    public void ajouter(Rdv m);
    public void supprimer(Rdv m);
    public void modifier(String newdate,Rdv m);
    public List<Rdv> listerAll();
    public Rdv listerOne(int id1,int id2);
    public boolean rechercher(int id1,int id2,String date);
    public List<Integer> listid(int id);
    public void confirmerrdv(Rdv r);
}
