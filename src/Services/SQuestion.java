/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Matching;
import Entities.Personnalite;
import Entities.Question;
import Iservices.IQuestion;
import Util.util;
import DataStorage.Mydb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fatma
 */
public class SQuestion implements IQuestion {

    Connection cnx;

    public SQuestion() {
        cnx = Mydb.getinstance().getCnx();
    }

    @Override
    public boolean ajouter(Question q) {
        String query = "INSERT INTO question (question, type, trait, reponse1, reponse2, reponse3) VALUES "
                + "('" + q.getQuestion() + "','" + q.getType() + "','" + q.getTrait() + "','" + q.getReponse1() + "','" + q.getReponse2() + "','" + q.getReponse3() + "')";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Question ajoutée!");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public List<Question> listerAll() {
        String query = "SELECT * FROM question";
        List<Question> res = new ArrayList<Question>();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Question p = new Question();
                p.setQuestion(result.getString("question"));
                p.setType(result.getString("type"));
                p.setTrait(result.getString("trait"));
                p.setReponse1(result.getString("reponse1"));
                p.setReponse2(result.getString("reponse2"));
                p.setReponse3(result.getString("reponse3"));
                res.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Question>) res;
    }

    @Override
    public Question listerOne(String id) {
        String query = "SELECT * FROM personnalite WHERE idUser=" + id;
        Question q = new Question();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {

                q.setQuestion(result.getString("question"));
                q.setType(result.getString("type"));
                q.setTrait(result.getString("trait"));
                q.setReponse1(result.getString("reponse1"));
                q.setReponse2(result.getString("reponse2"));
                q.setReponse2(result.getString("reponse3"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }

    @Override
    public List<Question> listerDifferent(String question) {
        String query = "SELECT * FROM question WHERE question!=" + question;
        List<Question> res = new ArrayList<Question>();

        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Question p = new Question();
                p.setQuestion(result.getString("question"));
                p.setType(result.getString("trait"));
                p.setTrait(result.getString("type"));
                p.setReponse1(result.getString("reponse1"));
                p.setReponse2(result.getString("reponse2"));
                p.setReponse3(result.getString("reponse3"));
                res.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (ArrayList<Question>) res;
    }

    @Override
    public void supprimer(String question) {
        String query = "DELETE FROM question WHERE  question='" + question + "'";
        try {
            Statement st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Question supprimée!");
        } catch (SQLException ex) {
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(Question newq, String ancien) {
        String query = "UPDATE question SET question='" + newq.getQuestion() + "', type='" + newq.getType() + "', trait='" + newq.getTrait()
                + "', reponse1='" + newq.getReponse1() + "', reponse2='" + newq.getReponse2()
                + "', reponse3='" + newq.getReponse3() + "' WHERE question='" +ancien + "'";
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("Question mise à jour!");
        } catch (SQLException ex) {
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Question> rechercher(String question) {
        String query = "SELECT * FROM question WHERE question LIKE '%" + question + "%' OR type LIKE '%" + question
                + "%' OR trait LIKE '%" + question + "%' OR reponse1 LIKE '%" + question
                + "%' OR reponse2 LIKE '%" + question + "%' OR reponse3 LIKE '%" + question + "%'";

        List<Question> res = new ArrayList<Question>();
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Question p = new Question();
                p.setQuestion(result.getString("question"));
                p.setType(result.getString("type"));
                p.setTrait(result.getString("trait"));

                p.setReponse1(result.getString("reponse1"));
                p.setReponse2(result.getString("reponse2"));
                p.setReponse3(result.getString("reponse3"));
                res.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (res == null) {
            return null;
        }
        return res;
    }

    @Override
    public List<Question> listerTrait(String trait) {
        String query = "SELECT * FROM question WHERE trait='" + trait + "'";

        List<Question> res = new ArrayList<Question>();
        try {
            Statement st = cnx.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                Question p = new Question();
                p.setQuestion(result.getString("question"));
                p.setType(result.getString("type"));
                p.setTrait(result.getString("trait"));

                p.setReponse1(result.getString("reponse1"));
                p.setReponse2(result.getString("reponse2"));
                p.setReponse3(result.getString("reponse3"));
                res.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (res == null) {
            return null;
        }
        return res;
    }

    public List<Question> listerDeux(String trait, String type) {

        List<Question> res = new ArrayList<>();

        List<Question> ltrait = new ArrayList<>();
        ltrait = listerTrait(trait);
        int nb = ltrait.size();
        int rand1 = util.getRandomNumberInRange(1, nb);
        int rand2 = 0;

        do {
            rand2 = util.getRandomNumberInRange(1, nb);
        } while (rand2 == rand1);

        System.out.println(rand1);
        System.out.println(rand2);

        Question q1 = new Question();
        Question q2 = new Question();
        q1 = ltrait.get(rand1 - 1);
        q2 = ltrait.get(rand2 - 1);
        res.add(q1);
        res.add(q2);
        return (ArrayList<Question>) res;
    }

    public List<Question> TestPersonnalite() {
        List<Question> res = new ArrayList<>();
         List<Question> resfinal = new ArrayList<>();
        List<Question> nerveux = new ArrayList<>(listerDeux("nerveux", "personnalite"));
        List<Question> attirant = new ArrayList<>(listerDeux("attirant", "personnalite"));
        List<Question> sensible = new ArrayList<>(listerDeux("sensible", "personnalite"));
        List<Question> romantique = new ArrayList<>(listerDeux("romantique", "personnalite"));
        List<Question> sportif = new ArrayList<>(listerDeux("sportif", "personnalite"));
        List<Question> artiste = new ArrayList<>(listerDeux("artiste", "personnalite"));
        List<Question> musicien = new ArrayList<>(listerDeux("musicien", "personnalite"));
        List<Question> famille = new ArrayList<>(listerDeux("famille", "personnalite"));
        List<Question> actif = new ArrayList<>(listerDeux("actif", "personnalite"));
        List<Question> sociable = new ArrayList<>(listerDeux("sociable", "personnalite"));
        List<Question> fidele = new ArrayList<>(listerDeux("fidele", "personnalite"));
        List<Question> soigne = new ArrayList<>(listerDeux("soigne", "personnalite"));
        List<Question> genereux = new ArrayList<>(listerDeux("genereux", "personnalite"));
        List<Question> ambitieux = new ArrayList<>(listerDeux("ambitieux", "personnalite"));
        List<Question> jaloux = new ArrayList<>(listerDeux("jaloux", "personnalite"));
        List<Question> serieux = new ArrayList<>(listerDeux("serieux", "personnalite"));
        List<Question> drole = new ArrayList<>(listerDeux("drole", "personnalite"));
        List<Question> materialiste = new ArrayList<>(listerDeux("materialiste", "personnalite"));
        List<Question> tolerant = new ArrayList<>(listerDeux("tolerant", "personnalite"));
        List<Question> aventurier = new ArrayList<>(listerDeux("aventurier", "personnalite"));
        res.add(nerveux.get(0));
        res.add(nerveux.get(1));
        res.add(attirant.get(0));
        res.add(attirant.get(1));
        res.add(sensible.get(0));
        res.add(sensible.get(1));
        res.add(romantique.get(0));
        res.add(romantique.get(1));
        res.add(sportif.get(0));
        res.add(sportif.get(1));
        res.add(artiste.get(0));
        res.add(artiste.get(1));
        res.add(musicien.get(0));
        res.add(musicien.get(1));
        res.add(famille.get(0));
        res.add(famille.get(1));
        res.add(actif.get(0));
        res.add(actif.get(1));
        res.add(sociable.get(0));
        res.add(sociable.get(1));
        res.add(fidele.get(0));
        res.add(fidele.get(1));
        res.add(soigne.get(0));
        res.add(soigne.get(1));
        res.add(genereux.get(0));
        res.add(genereux.get(1));
        res.add(ambitieux.get(0));
        res.add(ambitieux.get(1));
        res.add(jaloux.get(0));
        res.add(jaloux.get(1));
        res.add(serieux.get(0));
        res.add(serieux.get(1));
        res.add(drole.get(0));
        res.add(drole.get(1));
        res.add(materialiste.get(0));
        res.add(materialiste.get(1));
        res.add(tolerant.get(0));
        res.add(tolerant.get(1));
        res.add(aventurier.get(0));
        res.add(aventurier.get(1));

        System.out.println(res);
        System.out.println(res.size());
Collections.sort(res, (p1, p2) -> p1.getReponse1().compareTo(p2.getReponse1()));
        return res;
    }

    public List<Question> TestPreference() {
        List<Question> res = new ArrayList<>();
        return res;
    }

  

}
