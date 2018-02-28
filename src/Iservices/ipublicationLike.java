/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservices;

import Entities.PublicationLike;

/**
 *
 * @author feriel
 */
public interface ipublicationLike {
     public boolean getUserLike(PublicationLike pl);
     public void ajouterLike(PublicationLike pl);
      public int getNumberLike(int p);
}
