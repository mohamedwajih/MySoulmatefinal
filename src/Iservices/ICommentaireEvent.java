/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    
}
