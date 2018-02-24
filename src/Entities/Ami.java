/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author asus
 */
public class Ami {
    
    private int id_u1;
    private int id_u2;
    private int etat=0;

    public Ami(int id_u1, int id_u2) {
        this.id_u1 = id_u1;
        this.id_u2 = id_u2;
    }

    public Ami() {
    }

    public int getId_u1() {
        return id_u1;
    }

    public int getId_u2() {
        return id_u2;
    }

    public int getEtat() {
        return etat;
    }

    public void setId_u1(int id_u1) {
        this.id_u1 = id_u1;
    }

    public void setId_u2(int id_u2) {
        this.id_u2 = id_u2;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Ami{" + "id_u1=" + id_u1 + ", id_u2=" + id_u2 + ", etat=" + etat + '}';
    }
    
    
    
}
