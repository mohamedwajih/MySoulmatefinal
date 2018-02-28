/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;



import java.util.Date;







/**
 *
 * @author feriel
 */
public class Commentaire {
    private int id_com;
    private String contenu_com;    
    private String date_com;
    private int id_pub;
  

    public Commentaire() {
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public void setContenu_com(String contenu_com) {
        this.contenu_com = contenu_com;
    }

    public void setDate_com(String date_com) {
        this.date_com = date_com;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    
    

    public int getId_pub() {
        return id_pub;
    }
    

    public int getId_com() {
        return id_com;
    }

    public String getContenu_com() {
        return contenu_com;
    }

    public String getDate_com() {
        return date_com;
    }

    public Commentaire(String contenu_com, String date_com, int id_pub) {
        this.contenu_com = contenu_com;
        
        
    this.getDate_com();
        this.id_pub= id_pub;
    }

    public Commentaire(int id_com) {
        this.id_com = id_com;
    }

   

    public Commentaire(int id_com, String contenu_com, String date_com, int id_pub, int id) {
        this.id_com = id_com;
        this.contenu_com = contenu_com;
        this.date_com = date_com;
        this.id_pub = id_pub;
     
    }

    

    public Commentaire(String contenu_com,String date_com) {
        this.contenu_com = contenu_com;
        this.date_com=date_com;
    }

    public Commentaire(String contenu_com, int id_pub) {
        this.contenu_com = contenu_com;
        this.id_pub = id_pub;
    }

    public Commentaire(int id_com, String contenu_com, String date_com) {
        this.id_com = id_com;
        this.contenu_com = contenu_com;
        this.date_com = date_com;
    }

    public Commentaire(String contenu_com) {
        this.contenu_com = contenu_com;
    }

    public Commentaire(int id_com, String contenu_com, String date_com, int id_pub) {
        this.id_com = id_com;
        this.contenu_com = contenu_com;
        this.date_com = date_com;
        this.id_pub = id_pub;
    }
    

    
    
}
