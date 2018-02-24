/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Fos_user;
import Entities.Question;
import Util.Role;
import static Util.Role.utilisateur;
import DataStorage.Mydb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatma
 */
public class SUser {
    Connection cnx;

    public SUser() {
        cnx = Mydb.getinstance().getCnx();
    }
    public Fos_user getUserById(int id)
    {
        String query = "SELECT * FROM fos_user WHERE id=" + id;
        Fos_user q = new Fos_user();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {

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
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }
}
