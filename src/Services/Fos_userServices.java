/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Services;

import DataStorage.Mydb;
import Entities.Fos_user;
import Entities.Role;
import Iservices.IFos_user;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.util.converter.LocalDateStringConverter;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author hp
 */

public class Fos_userServices implements IFos_user {
   public static int IdUserConnected=-1; 
    Connection connexion;

    /**
     *
     */
    public Fos_userServices() {
        connexion = Mydb.getinstance().getCnx();
    }

    @Override
  public void ajouter_utilisateur(Fos_user u) {

      try {
            
            String query = "INSERT INTO fos_user (username,email,password,roles,nom,prenom,age,sexe,num_tel,photo_de_profil,date_de_naissance,adresse,confirmation_token,locked) VALUES"
                    + " ( '"+u.getUsername()+"','"+u.getEmail()+"','"+u.getPassword()+"','"+Role.utilisateur+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getAge()+"','"+u.getSexe()+"','"+u.getNum_tel()+"','"+u.getPhoto_de_profil()+"','"+u.getDate_de_naissance()+"','"+u.getAdresse()+"','"+u.getConfirmation_key()+"','"+u.getLocked()+"');";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    
    }

    @Override
    public void supprimer_utilisateur(Fos_user u) {
       try{
        String query ="DELETE FROM fos_user WHERE id="+u.getId()+";";
         Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppresion effectué");
        }
        catch (SQLException ex) {
            System.out.println("Echec de suppresion");
        }  
    }

    @Override
    public void modifier_utilisateur(Fos_user u) {
try {
            String query = "UPDATE fos_user SET username='"+u.getUsername()+"', email='"+u.getEmail()+"',nom='"+u.getNom()+"',prenom='"+u.getPrenom()+"',age='"+u.getAge()+"',sexe='"+u.getSexe()+"',num_tel='"+u.getNum_tel()+"',photo_de_profil='"+u.getPhoto_de_profil()+"',date_de_naissance='"+u.getDate_de_naissance()+"',adresse='"+u.getAdresse()+"' WHERE id="+u.getId()+";";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de modification");
        }     
    }

    @Override
    public void ajouter_responsable_event(Fos_user u) {
         try {
            String query = "INSERT INTO fos_user (username, email,password,roles,nom,prenom,num_tel,"
                    + ",numTel,adresse) VALUES ( '"+u.getUsername()+"','"+u.getEmail()+"','"+u.getPassword()+"','"+Role.responsableEvent+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getNum_tel()+"','"+u.getAdresse()+"');";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec ajout");
        }
    }

    @Override
    public void modifier_responsable_event(Fos_user u) {
try {
            String query = "UPDATE fos_user SET username='"+u.getUsername()+"',password='"+u.getPassword()+"', email='"+u.getEmail()+"',nom='"+u.getNom()+"',prenom='"+u.getPrenom()+"',num_tel='"+u.getNum_tel()+"',adresse='"+u.getAdresse()+"' WHERE id="+u.getId()+";";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de modification");
        }    
    }
    @Override
    public void modifier_mdp(Fos_user u)
    {
        try {
            String query = "UPDATE fos_user SET password='"+u.getPassword()+"' where id="+u.getId()+";";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de modification");
        }    
        
    }
    

    @Override
    public List<Fos_user> afficher_responsable_event() {
List<Fos_user> listUser = new ArrayList<Fos_user>();
        try {
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from fos_user WHERE role='"+Role.responsableEvent+"'");

            while (rs.next()) {
                Fos_user user = new Fos_user() ;
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("Password"));
                user.setEmail(rs.getString("email"));
                user.setRoles(Entities.Role.valueOf(rs.getString("role")));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setAdresse(rs.getString("adresse"));
                
              user.toString();
                System.out.println(user.toString());
                listUser.add(user);
                
            }
        } catch (SQLException ex) {
            System.out.println("Affichage echoué");
        
    }
   return listUser;    }

    @Override
    public List<Fos_user> afficher_user() {
List<Fos_user> listUser = new ArrayList<Fos_user>();
        try {
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from fos_user ");

            while (rs.next()) {
                Fos_user user = new Fos_user() ;
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setDate_de_naissance(rs.getString("date_de_naissance"));
                
                user.setAge(rs.getInt("age"));
                user.setUsername(rs.getString("username"));
               // user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                
                user.setRoles(Entities.Role.valueOf(rs.getString("roles")));
                user.setSexe(rs.getString("sexe"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setPhoto_de_profil(rs.getString("photo_de_profil"));
                user.setAdresse(rs.getString("adresse"));
                
                
              user.toString();
                System.out.println(user.toString());
                listUser.add(user);
                
            }
        } catch (SQLException ex) {
            System.out.println("Affichage echoué");
        
    }
   return listUser;    }
    

    @Override
    public List<Fos_user> chercher_user_par_nom_prenom(String nom, String prenom) {
List<Fos_user> listUser = new ArrayList<Fos_user>();
        try {
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from fos_user WHERE nom='" +nom+"' and prenom ='" +prenom+"'");
            System.out.println("les donnees de notre base ");

            while (rs.next()) {
               Fos_user user = new Fos_user() ;
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
           //     user.setDate_de_naissance(é);
                user.setAge(rs.getInt("age"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoles(Entities.Role.valueOf(rs.getString("role")));
                user.setSexe(rs.getString("sexe"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setPhoto_de_profil(rs.getString("photo_de_profil"));
                user.setAdresse(rs.getString("adresse"));
                
               user.toString();
                System.out.println(user.toString());
                listUser.add(user);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;    }

    @Override
    public List<Fos_user> chercher_user_par_nom(String nom) {
List<Fos_user> listUser = new ArrayList<Fos_user>();
        try {
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from fos_user WHERE nom='" + nom +"'");
            System.out.println("les donnees de notre base ");

            while (rs.next()) {
               Fos_user user = new Fos_user() ;
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
           //     user.setDate_de_naissance(é);
                user.setAge(rs.getInt("age"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoles(Entities.Role.valueOf(rs.getString("role")));
                user.setSexe(rs.getString("sexe"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setPhoto_de_profil(rs.getString("photo_de_profil"));
                user.setAdresse(rs.getString("adresse"));
                
               user.toString();
                System.out.println(user.toString());
                listUser.add(user);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;     }

    @Override
    public List<Fos_user> chercher_user_par_prenom(String prenom) {
List<Fos_user> listUser = new ArrayList<Fos_user>();
        try {
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from fos_user WHERE prenom='" + prenom +"'");
            System.out.println("les donnees de notre base ");

            while (rs.next()) {
               Fos_user user = new Fos_user() ;
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
           //     user.setDate_de_naissance(é);
                user.setAge(rs.getInt("age"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoles(Entities.Role.valueOf(rs.getString("role")));
                user.setSexe(rs.getString("sexe"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setPhoto_de_profil(rs.getString("photo_de_profil"));
                user.setAdresse(rs.getString("adresse"));
                
               user.toString();
                System.out.println(user.toString());
                listUser.add(user);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;     }  
     @Override
    public void activerCompte(int id) {

        String sql = "UPDATE fos_user SET locked = '"+1+"' WHERE id ="+id+";";

        try {
            Statement stl = connexion.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Modification effectuée");
        } catch (SQLException ex) {
           System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }    
    }
    
  
    @Override
    public boolean Authentifier(String login, String password) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(login);
           
        if ( m.matches()) { 
            
            try {
            /// login == email donc je veux selecter selon email
                System.out.println("Tentative  avec email");

                String sql = "Select * from fos_user WHERE email = '"+login +"' and password = '"+ DigestUtils.shaHex(password) +"';";
                Statement stl= connexion.createStatement();
                ResultSet rs =stl.executeQuery(sql);
                                if(rs.next()){ 
                                    System.out.println( rs.getInt("id")+ " is connected ");
                                    IdUserConnected=rs.getInt("id");
                                    return true;
                                }
                                else{
                                                       System.err.println("check your email or password");
                                                       return false;

                                }
           } catch (SQLException ex) {
                   System.err.println("check your email or password");
           }
            
        }
        else { /// login == username donc je veux selecter selon username 
            System.err.println("Tentative  avec username");
            try {
                

                String sql = "Select * from fos_user WHERE username = '"+login +"' and password = '"+DigestUtils.shaHex(password)+"';";
                Statement stl= connexion.createStatement();
                ResultSet rs  = stl.executeQuery(sql);
                if(rs.next()){ 
                                    System.out.println( rs.getInt("id")+ " is connected ");
                                    
                                                                        IdUserConnected=rs.getInt("id");

                               return true;
}
                                else{
                                                       System.err.println("check your username or password");
                                                      return false;

                                }
           } catch (SQLException ex) {
                   System.err.println("check your username or password");
           } 
        }
 return false;
    }
    public Fos_user getUserById(int idConnected)
    {               Fos_user q = new Fos_user() ;

        try {
            Statement state = connexion.createStatement();
            ResultSet result = state.executeQuery("select * from fos_user WHERE id='" + idConnected +"'");
            System.out.println("les donnees de notre base ");

            if (result.next()) {
                
                  q.setId(result.getInt("id"));
                q.setUsername(result.getString("username"));
                q.setEmail(result.getString("email"));
                q.setPassword(result.getString("password"));
               
               
                q.setNom(result.getString("nom"));
                q.setPrenom(result.getString("prenom"));
                q.setAge(result.getInt("age"));
                q.setSexe(result.getString("sexe"));
                
                q.setPhoto_de_profil(result.getString("photo_de_profil"));
                q.setDate_de_naissance(result.getString("date_de_naissance".toString()));
                q.setAdresse(result.getString("adresse"));
           
                
              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return q ;
    }
    
     public Fos_user getallUserById(int idConnected)
    {               Fos_user user = new Fos_user() ;

        try {
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from fos_user WHERE id='" + idConnected +"'");
            System.out.println("les donnees de notre base ");

            if (rs.next()) {
                
                 user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
//                user.setDate_de_naissance(LocalDate.parse("date_de_naissance"));
                user.setAge(rs.getInt("age"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoles(Entities.Role.valueOf(rs.getString("roles")));
                user.setSexe(rs.getString("sexe"));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setPhoto_de_profil(rs.getString("photo_de_profil"));
                user.setAdresse(rs.getString("adresse"));
                user.setConfirmation_key(rs.getString("confirmation_token"));
                user.setLocked(rs.getInt("locked"));
                
              

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user ;
    }  
    
    

 

    @Override
    public boolean verifier_compte(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public boolean verifier_username(String login) {
        
            
            try {
            /// login == email donc je veux selecter selon email
                System.out.println("verifier username existant ou pas");

                String sql = "select * from fos_user WHERE username = '"+login+"';";
                Statement stl= connexion.createStatement();
                ResultSet rs =stl.executeQuery(sql);
                                if(rs.next()){ 
                                    System.err.println("username deja existant veuillez choisir un autre");
                                    //IdUserConnected=rs.getInt("id");
                                    return false;
                                }
           } catch (SQLException ex) {
                   System.err.println("username deja existant veuillez choisir un autre");
                                   
           }
            return true;
 
        }
    
     public Fos_user chercherParId(int id) {
        Fos_user user = new Fos_user() ;
         try {
            
            Statement state = connexion.createStatement();
            ResultSet rs = state.executeQuery("select * from fos_user WHERE id='"+id+"'");

            while (rs.next()) {
                
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setRoles(Entities.Role.valueOf(rs.getString("role")));
                user.setNum_tel(rs.getInt("num_tel"));
                user.setAdresse(rs.getString("adresse"));
                
              user.toString();
                System.out.println(user.toString());
               return user; 
                
            }
        } catch (SQLException ex) {
            System.out.println(" echoué");
        
    }
   return user;    }

    @Override
    public boolean verifier_email(String login) {
   try {
            /// login == email donc je veux selecter selon email
                System.out.println("verifier email existant ou pas");

                String sql = "select email from fos_user WHERE email = '"+login+"';";
                Statement stl= connexion.createStatement();
                ResultSet rs =stl.executeQuery(sql);
                                if(rs.next()){ 
                                    System.err.println("email deja existant veuillez choisir un autre");
                                    //IdUserConnected=rs.getInt("id");
                                    return false;
                                }
           } catch (SQLException ex) {
                   System.err.println("email deja existant veuillez choisir un autre");
                                   
           }
            return true;
 
        }  
    @Override
    public void validerCompte(String code) {
       
       
            try {
                String query = "UPDATE fos_user SET locked=1 where confirmation_token='"+code+"';";

                Statement stm = connexion.createStatement();
                stm.executeUpdate(query);
                System.out.println("Ajout effectué");
            } catch (SQLException ex) {
                System.out.println("Echec d'ajout");
            }
        

    }

    @Override
    public void modifierImage(String image,int id) {
        try {
            String query = "UPDATE fos_user SET photo_de_profil='"+image+"' where id='"+id+"'";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de modification");
        }

    }

    

    
}
       
    
        
    
    
    