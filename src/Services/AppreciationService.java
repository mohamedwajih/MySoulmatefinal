/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataStorage.Mydb;
import Entities.Appreciation;
import Iservices.iappreciation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author feriel
 */

public class AppreciationService implements iappreciation{
    Connection cnx;

    public AppreciationService() {
        this.cnx=Mydb.getinstance().getCnx();
    }

    @Override
    public void ajouterAppreciation(Appreciation a) {
   try {           
            String requete1="INSERT INTO appreciation (id_pub) VALUES ('"+a.getId_pub()+"')";
            Statement st1=cnx.createStatement();
            st1.executeUpdate(requete1);
            System.out.println("Appreciation ajoutée !!");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
    }

    @Override
    public void supprimerAppreciation(Appreciation a) {
  try {           
            String requete2= "DELETE FROM appreciation WHERE id_pub='"+a.getId_pub()+"'";
            Statement st2=cnx.createStatement();
            st2.executeUpdate(requete2);
            System.out.println("Appreciation supprimée !!");
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
    }

    @Override
    public List<Appreciation> consulterAppreciation() {
      /*String requete4= "SELECT * FROM AppreciationService";
            List<AppreciationEntity> app=new ArrayList<>();
             try { 
            Statement st4=cnx.createStatement();
            ResultSet rs=st4.executeQuery(requete4);
            while(rs.next())
                
            {
                AppreciationEntity app1=new AppreciationEntity();
                app1.setId_app(rs.getInt("id_app"));
                app1.setId_pub(rs.getInt("id_pub"));
                
                app.add(app1);
                           }
    }
     catch (SQLException ex)
     {
         ex.printStackTrace();
     }
             return (ArrayList<AppreciationEntity>) app;
    }*/
       
             ArrayList<Appreciation> listN = new ArrayList<Appreciation>();
  try {
            String query = "SELECT * FROM appreciation";
            Statement stm= cnx.createStatement();
            ResultSet rs = stm.executeQuery(query);
             while(rs.next())
            {
            /*int id_app=rs.getInt(1);
            int id_pub=rs.getInt(2);
            
             AppreciationEntity app = new AppreciationEntity();
             
             listN.add(app);
               // System.out.println(listN.);*/
                 int id_app=rs.getInt("id_app");
              
                listN.add(new Appreciation(id_app));
                
            }
             System.out.println(listN.size());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return listN;
       }
}