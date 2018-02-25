/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

//import com.mysql.jdbc.Connection;
import DataStorage.Mydb;
import Entities.Ami;
import Entities.Notification;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Iservices.IAmi;

/**
 *
 * @author asus
 */


public class SAmi  implements IAmi{
    
    java.sql.Connection cnx;
    
    public SAmi() {
        cnx=Mydb.getinstance().getCnx();
    }

    
    
    
    
    
    @Override
    public void ajouter(Ami a) {
        
        String query="INSERT INTO amis(id_u1,id_u2,etat) VALUES("+a.getId_u1()+","+a.getId_u2()+","+a.getEtat()+")";
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("new ami added");
             Notification newnotif=new Notification(0,a.getId_u2(),"like","new like",LocalDate.now());
            NotificationServices ns=new NotificationServices();
           
            ns.addNotification(newnotif);
        } catch (SQLException ex) {
            Logger.getLogger(SAmi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    
    
    
    @Override
    public ArrayList<Ami> listerall(int id) {
        String query="SELECT * FROM amis WHERE id_u1="+id+" OR id_u2="+id;
        List<Ami> l1= new ArrayList<Ami>();
         try {
            Statement st=cnx.createStatement();
             ResultSet res= st.executeQuery(query);
             while (res.next()){
                 Ami a = new Ami();
                 a.setId_u1(res.getInt("id_u1"));
                 a.setId_u2(res.getInt("id_u2"));
                 a.setEtat(res.getInt("etat"));
                 l1.add(a);
             }
        } catch (SQLException ex) {
            Logger.getLogger(SAmi.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return (ArrayList<Ami>) l1;
         
    }
    
    
    
    
    
    @Override
    public ArrayList<String> listerami(int id) {
        String query="SELECT username FROM fos_user WHERE id=SELECT id_u2 FROM amis WHERE id_u1="+id;
        List<String> l1= new ArrayList<String>();
         try {
            Statement st=cnx.createStatement();
             ResultSet res= st.executeQuery(query);
             while (res.next()){
                
                 l1.add(res.getString("username"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(SAmi.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return (ArrayList<String>) l1;
         
    }

    
    
    
    
    @Override
    public boolean chercherami(int id1, int id2) {
        String query="SELECT * FROM amis WHERE ( id_u1="+id1+" OR id_u1="+id2+" ) AND ( id_u2="+id1+" OR id_u2="+id2+" )";
        boolean res=false;
        try {
            Statement st=cnx.createStatement();
           ResultSet rs=st.executeQuery(query);
           if(rs.first())
             res=true;
           
        } catch (SQLException ex) {
            Logger.getLogger(SAmi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(res);
        return res;
        
    }

    
    
    
    
    @Override
    public void confirmerami(int id1, int id2) {
         String query="SELECT * FROM amis WHERE ( id_u1="+id1+" OR id_u1="+id2+" ) AND ( id_u2="+id1+" OR id_u2="+id2+" )";
         
        try {
            Statement st=cnx.createStatement();
            ResultSet rs=st.executeQuery(query);
            rs.first();
            System.out.println("id1="+rs.getInt("id_u1"));
            System.out.println("id2="+rs.getInt("id_u2"));
           
           
        } catch (SQLException ex) {
             System.err.println("erreur confirmer ami");
        }
          String query2="UPDATE amis SET etat=1 WHERE ( id_u1="+id1+" OR id_u1="+id2+" ) AND ( id_u2="+id1+" OR id_u2="+id2+" )";
        try {
            Statement st2=cnx.createStatement();
            st2.executeUpdate(query2);
           
        } catch (SQLException ex) {
            Logger.getLogger(SAmi.class.getName()).log(Level.SEVERE, null, ex);
        }     
    }

    @Override
    public void deleteami(int id1, int id2) {
        String query="DELETE FROM amis WHERE ( id_u1="+id1+" OR id_u1="+id2+" ) AND ( id_u2="+id1+" OR id_u2="+id2+" )";
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("supprimer");
        } catch (SQLException ex) {
            Logger.getLogger(SAmi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
