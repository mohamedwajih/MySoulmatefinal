/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import DataStorage.Mydb;
import Entities.Notification;
import Entities.Rdv;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import Presentation.NewFXMain;
import Iservices.IRdv;

/**
 *
 * @author asus
 */
public class SRdv implements IRdv {
     Connection cnx;
   
   public SRdv(){
       cnx=Mydb.getinstance().getCnx();
   }

    @Override
    public void ajouter(Rdv m) {
         String query="INSERT INTO rdv (id_1,id_2,date,id_lieu,etat) VALUES ('"+m.getId1()+"','"+m.getId2()+"','"+m.getDate()+"','"+m.getLieu()+"','"+m.getEtat()+"')";
         try {
             Statement st=cnx.createStatement();
             st.executeUpdate(query);
             System.out.println("new RDV added");
             Notification n= new Notification(0,m.getId2(),m.getId1(),"rdv","new rdv",LocalDate.now(),"file:/C:/wamp64/www/Img/clock.png");
            NotificationServices ns=new NotificationServices();
           
            ns.addNotification(n);
             
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
////////////////////////////////////////////////////////////
    @Override
    public void supprimer(Rdv m) {
        String query="DELETE FROM rdv WHERE  id_1="+m.getId1()+" AND id_2="+m.getId2()+" AND date='"+m.getDate()+"'";
        System.out.println(m.getDate());
         try {
             Statement st=cnx.createStatement();
             st.executeUpdate(query);
             System.out.println("rdv removed");
         } catch (SQLException ex) {
             Logger.getLogger(SRdv.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }
///////////////////////////////////////////////////////////////////
    @Override
    public void modifier(String newdate,Rdv m) {
        String query="UPDATE rdv SET date='"+newdate+"' WHERE id_1="+m.getId1()+" and id_2="+m.getId2();
        Statement st;
         try {
             st = cnx.createStatement();
                st.executeUpdate(query);
                System.out.println("rdv updated");
         } catch (SQLException ex) {
             Logger.getLogger(SRdv.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    }
////////////////////////////////////////////////////////////////////
    @Override
    public List<Rdv> listerAll() {
         String query="SELECT * FROM rdv WHERE ( id_1="+NewFXMain.idu1+" OR id_2="+NewFXMain.idu1+") AND etat=1";
        List<Rdv> res= new ArrayList<Rdv>();
        
        try {
             Statement st=cnx.createStatement();
             ResultSet result=st.executeQuery(query);
             
             while (result.next()) { 
                 Rdv p=new Rdv();
                p.setId1(result.getInt("id_1"));
                p.setId2(result.getInt("id_2"));
                 System.out.println(result.getString("date"));
                 p.setDate2(result.getString("date"));
                  p.setLieu(result.getInt("id_lieu"));
                   p.setEtat(result.getInt("etat"));
                   res.add(p);
            }
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  (ArrayList<Rdv>) res;
    }
    /////////////////////////////////////////////////////////////////////////
        public Rdv listerOne(int id1,int id2) {           
         String query="SELECT * FROM rdv WHERE id_1="+id1+" AND id_2="+id2+" AND etat=0";
         Rdv p=new Rdv();
        try {
             Statement st=cnx.createStatement();
             ResultSet result=st.executeQuery(query);
              result.first();
                p.setId1(result.getInt("id_1"));
                p.setId2(result.getInt("id_2"));
                 p.setDate2(result.getString("date"));
                 //System.out.println("test"+p.getDate());
                  p.setLieu(result.getInt("id_lieu"));
                   p.setEtat(result.getInt("etat"));
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  p;
    }
    

 
///////////////////////////////////////////////////////////////////
    @Override
    public boolean rechercher(int id1,int id2,String date){
          String query="SELECT * FROM rdv WHERE id_1="+id1+" AND id_2="+id2+" AND date='"+date+"'";
 Rdv p=new Rdv();
        try {
             Statement st=cnx.createStatement();
             ResultSet result=st.executeQuery(query);
             
             while (result.next()) { 
                 
                p.setId1(result.getInt("id_1"));
                p.setId2(result.getInt("id_2"));
                 p.setDate2(result.getDate("date").toString());
                  p.setLieu(result.getInt("id_lieu"));
                   p.setEtat(result.getInt("etat"));
                  
            }
         } catch (SQLException ex) {
             Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
         }
       
         if (p.getId1()==0)
             return false;
         return true;
         
        
    }
//////////////////////////////////////////////////////
    @Override
    public List<Integer> listid(int id) {
           String query = "SELECT id_1 FROM rdv WHERE id_2="+id+" AND etat=0";
        Statement st;
        List<Integer> lid=new ArrayList<Integer>();
        try {
            st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {                
                 lid.add(result.getInt("id_1"));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
        }  
     
return lid;
        
    }
////////////////////////////////////////////////////////////////////
    @Override
    public void confirmerrdv(Rdv r) {
        System.out.println(r);
                 String query = "UPDATE rdv SET etat=1 WHERE id_1="+r.getId1()+" AND id_2="+r.getId2()+" AND date='"+r.getDate()+"'";
        Statement st; 
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
        }   

        
    }
    
}
