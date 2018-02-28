/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entities.Participation;
import java.util.ArrayList;

/**
 *
 * @author Ste GHRIB-INFO
 */
public interface Iparticipation {
    public void addParticipation(Participation p);
    public void deleteParticipation(Participation p);
    public ArrayList<Participation> getParticipations(int id_event);
   
    
}
