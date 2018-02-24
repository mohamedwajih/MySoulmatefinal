/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Entities.Message;

import java.util.List;

/**
 *
 * @author asus
 */
public interface IMessage {
    
    public void ajouter(Message m);
    public List<Message> lister(int id_dest,int id_emet);
    public Message chercherlast(int id);
    public boolean verif(int id);
    public void lu(int id);
    public List<Integer> listeid(int id);
    
    
   
    
}
