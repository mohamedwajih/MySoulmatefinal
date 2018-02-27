/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.Mydb;
import Entities.Participation;
import IServices.Iparticipation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Ste GHRIB-INFO
 */
public class ParticipationService  implements Iparticipation{
    
     Connection connexion;
    public ParticipationService(){
          connexion = Mydb.getinstance().getCnx();
    }

    @Override
    public void addParticipation(Participation p) {
          try {
            String query = "INSERT INTO partipevent (id_user_par,id_event_part) "
                    + "values ( '"+p.getId_user()+"','"+p.getId_event()+"')";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
      }

    @Override
    public void deleteParticipation(Participation p) {
     try {
            String query = "DELETE FROM partipevent WHERE id_user_par="+p.getId_user()+" and id_event_part= "+p.getId_event();
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("participation Supprimer");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
    
    }

    @Override
    public ArrayList<Participation> getParticipations(int id_event) {
        ArrayList<Participation> listE = new ArrayList<Participation>();
  try {
            String query = "SELECT * FROM partipevent WHERE id_event_part="+id_event;
            Statement stm= connexion.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            int id=rs.getInt(1);
            int id_user=rs.getInt(2);
            int id_eve=rs.getInt(3);
             Participation e = new Participation(id,id_user,id_eve);
             listE.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listE; 
    }
    
}
