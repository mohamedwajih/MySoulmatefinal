/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Matching;
import Entities.Personnalite;
import Entities.Preference;
import Iservices.IMatching;
import DataStorage.Mydb;
import static java.lang.Math.abs;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatma
 */
public class SMatching implements IMatching {
 Connection cnx;
   
   public SMatching(){
       cnx=Mydb.getinstance().getCnx();
   }

    @Override
    public void ajouter(Matching m) {
         String query="INSERT INTO matching VALUES "
                 + "('"+m.getIdUser1()+"','"+m.getIdUser2()+"','"+m.getPourcentage()+"')";
         try {
             Statement st=cnx.createStatement();
             st.executeUpdate(query);
             System.out.println("Matching ajouté!");
         } catch (SQLException ex) {
             Logger.getLogger(SMatching.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }

    

    @Override
    public List<Matching> listerAll() {
         String query="SELECT * FROM matching";
        List<Matching> res= new ArrayList<Matching>();
        
        try {
             Statement st=cnx.createStatement();
             ResultSet result=st.executeQuery(query);
             
             while (result.next()) { 
                 Matching p=new Matching();
                p.setIdUser1(result.getInt("idUser1"));
                p.setIdUser2(result.getInt("idUser2"));             
                  p.setPourcentage(result.getInt("pourcentage"));
                   res.add(p);
            }
         } catch (SQLException ex) {
             Logger.getLogger(SMatching.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  (ArrayList<Matching>) res;
    }
    
 @Override
        public List<Matching> listerOne(int id) {
         String query="SELECT * FROM matching WHERE idUser1="+id;
        
        List<Matching> res= new ArrayList<>();
        try {
           
             Statement st=cnx.createStatement();
             ResultSet result=st.executeQuery(query);
             
             while(result.next()){
                  Matching p=new Matching();
                p.setIdUser1(result.getInt("idUser1"));
                p.setIdUser2(result.getInt("idUser2"));
                   p.setPourcentage(result.getInt("Pourcentage"));
                   res.add(p);
             }
            
         } catch (SQLException ex) {
             Logger.getLogger(SMatching.class.getName()).log(Level.SEVERE, null, ex);
         }
         return res;
    }
    

 
public List<Matching> match(int id)
{   SPersonnalite sper= new SPersonnalite();
    SPreference spre= new SPreference();
        Preference preference= new Preference(); 
    List<Personnalite> LPersonnalite= new ArrayList<>();
    List<Matching> matchs = new ArrayList<>();
    LPersonnalite=sper.listerAll();
    preference=spre.listerOne(id); 
    int nb=LPersonnalite.size();
    System.out.println("LPersonnalite= "+LPersonnalite);
    System.out.println("Preference= "+preference.toString());
    int pourcent =0;

    for (int i = 0; i<nb; i++)
    {   
        Matching m= new Matching();
        m.setIdUser1(id);
        m.setIdUser2(LPersonnalite.get(i).getIdUser());
        
        if(abs(LPersonnalite.get(i).getNerveux()-preference.getNerveux())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getActif()-preference.getActif())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getAmbitieux()-preference.getAmbitieux())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getArtiste()-preference.getArtiste())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getAttirant()-preference.getAttirant())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getAventurier()-preference.getAventurier())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getDrole()-preference.getDrole())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getFamille()-preference.getFamille())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getFidele()-preference.getFidele())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getGenereux()-preference.getGenereux())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getJaloux()-preference.getJaloux())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getMaterialiste()-preference.getMaterialiste())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getMusicien()-preference.getMusicien())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getRomantique()-preference.getRomantique())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getSensible()-preference.getSensible())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getSerieux()-preference.getSerieux())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getSociable()-preference.getSociable())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getSoigne()-preference.getSoigne())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getSportif()-preference.getSportif())<26)
           pourcent+=5;
        if(abs(LPersonnalite.get(i).getTolerant()-preference.getTolerant())<26)
           pourcent+=5;
        System.out.println("Pourcent = "+pourcent);
        
        if(pourcent>50)
        {   
         m.setPourcentage(pourcent);
        matchs.add(m);
        }
    
    }
    System.out.println("Nombre de matchs="+matchs.size());
return matchs;
}
  
   }  
