/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
<<<<<<< HEAD
<<<<<<< HEAD
package Iservices;

import Entities.CommentaireEvent;
import Entities.Event;
import java.util.ArrayList;

/**
 *
 * @author Fatma
 */
public interface ICommentaireEvent {
     public void addComment(CommentaireEvent c);
      public ArrayList<CommentaireEvent> getCommentaire(Event e);
      public void deleteCommentaire(CommentaireEvent c);
=======
=======
>>>>>>> 11d6054ad30d2d9224d93ec307caeae86a12c2d9
package IServices;

import Entities.CommentaireEvent;
import Entities.Event;
import java.util.ArrayList;

/**
 *
 * @author Ste GHRIB-INFO
 */
public interface ICommentaireEvent {
    public void addComment(CommentaireEvent c);
    public ArrayList<CommentaireEvent> getCommentaire(Event e);
    public void deleteCommentaire(CommentaireEvent c);
    
    
<<<<<<< HEAD
>>>>>>> origin/master
=======
>>>>>>> 11d6054ad30d2d9224d93ec307caeae86a12c2d9
}