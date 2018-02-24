/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Entities.Ami;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
public interface IAmi {
    
    public void ajouter(Ami a);
    public List<Ami> listerall(int id);
    public List<String> listerami(int id);
    public boolean chercherami(int id1,int id2);
    public void confirmerami(int id1,int id2);
    public void deleteami(int id1,int id2);
    
}
