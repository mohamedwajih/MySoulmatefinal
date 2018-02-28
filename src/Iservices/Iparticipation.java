/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD
<<<<<<< HEAD
package Iservices;

import Entities.Participation;

/**
 *
 * @author Fatma
 */
public interface Iparticipation {
    public void addParticipation(Participation p) ;
    public void deleteParticipation(Participation p);
=======
=======
>>>>>>> 11d6054ad30d2d9224d93ec307caeae86a12c2d9
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
   
<<<<<<< HEAD
>>>>>>> origin/master
=======
>>>>>>> 11d6054ad30d2d9224d93ec307caeae86a12c2d9
    
}
