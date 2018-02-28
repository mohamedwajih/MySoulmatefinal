/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

//import com.sun.org.apache.xalan.internal.lib.ExsltDatetime;
import DataStorage.Mydb;
import Entities.Message;
import Entities.Notification;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.datatype.DatatypeConstants;
import Presentation.NewFXMain;
import Iservices.IMessage;

/**
 *
 * @author asus
 */
public class SMessage implements IMessage {

    Connection cnx;

    public SMessage() {
        cnx = Mydb.getinstance().getCnx();
    }
////////////////////////////////////////////////////////////////////////////////
    @Override
    public void ajouter(Message m) {

     Date d = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dat = df.format(d);
        String query = "INSERT INTO messages (id_emet,id_dest,text,date_envoi) VALUES (" + m.getId_emet() + "," + m.getId_dest() + ",'" + m.getText() + "','" + dat + "')";

        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("new message added");
           Notification n= new Notification(0,m.getId_dest(),m.getId_emet(),"msg","new msg",LocalDate.now(),"file:/C:/wamp64/www/Img/clock.png");
            NotificationServices ns=new NotificationServices();
           
            ns.addNotification(n);
        } catch (SQLException ex) {
            Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
//////////////////////////////////////////////////////////////////////////////
    @Override
    public List<Message> lister(int id_dest,int id_emet) {
        String query = "SELECT * FROM messages WHERE (( id_dest=" + id_dest + " AND id_emet="+ id_emet +") OR ( id_dest=" + id_emet + " AND id_emet="+ id_dest +"))";
        List<Message> res = new ArrayList<Message>();
        
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);
             Statement st2 = cnx.createStatement();

            while (result.next()) {
                Message p = new Message();
                String query2="SELECT ( extract(day FROM sysdate()) - extract(day FROM date_envoi) ) FROM messages WHERE id_msg="+result.getInt("id_msg");
               
                ResultSet result2 = st2.executeQuery(query2);
                result2.first();
                p.setId_emet(result.getInt("id_emet"));
                p.setId_dest(result.getInt("id_dest"));
                p.setId_msg(result.getInt("id_msg"));
                p.setText(result.getString("text"));
                p.setDate(result.getDate("date_envoi").toString());
                p.setDuree(result2.getInt(1));
                res.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Message>) res;
    }
    
  /////////////////////////////////////////////////////////////////////

    @Override
    public Message chercherlast(int id) {
        String query = "SELECT * FROM messages WHERE ( id_emet="+id+" OR id_emet="+NewFXMain.idu1+") AND ( id_dest="+id+" OR id_dest="+NewFXMain.idu1+") ORDER BY id_msg DESC";
        Statement st;
        Message p = new Message();

        try {
            st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);
            result.first();
            p.setId_emet(result.getInt("id_emet"));
            p.setId_dest(result.getInt("id_dest"));
            p.setId_msg(result.getInt("id_msg"));
            p.setText(result.getString("text"));
            p.setDate(result.getDate("date_envoi").toString());

        } catch (SQLException ex) {
            Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;

    }
/////////////////////////////////////////////////////
    @Override
    public boolean verif(int id) {
        String query = "SELECT * FROM messages WHERE id_dest="+id+" AND lu=0";
        Statement st; 
        try {
            st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);
            return (result.first());
        } catch (SQLException ex) {
            Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
        }   
return false;
    }
////////////////////////////////////////////////////////
    @Override
    public void lu(int id) {
         String query = "UPDATE messages SET lu=1 WHERE id_dest="+id;
        Statement st; 
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
        }   

        
    }
////////////////////////////////////////////////////////////
    @Override
    public List<Integer> listeid(int id) {
         String query = "SELECT id_emet FROM messages WHERE id_dest="+id+" AND lu=0";
        Statement st;
        List<Integer> lid=new ArrayList<Integer>();
        try {
            st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);
            while (result.next()) {                
                 lid.add(result.getInt("id_emet"));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(SPlace.class.getName()).log(Level.SEVERE, null, ex);
        }  
     
return lid.stream().distinct().collect(Collectors.toList());
    }
}
