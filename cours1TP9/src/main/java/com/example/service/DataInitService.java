package com.example.service;

import com.example.model.Auteur;
import com.example.model.Livre;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class DataInitService {

    public EntityManager em;

    public void initData() {
        EntityTransaction tx = em.getTransaction();
        boolean ownTx = false;
        if (!tx.isActive()) {
            tx.begin();
            ownTx = true;
        }

        for (int i = 1; i <= 10; i++) {
            Auteur auteur = new Auteur();
            auteur.setNom("Auteur " + i);

            for (int j = 1; j <= 5; j++) {
                Livre livre = new Livre();
                livre.setTitre("Livre " + j + " de l'auteur " + i);
                auteur.ajouterLivre(livre);
            }

            em.persist(auteur);
        }

        if (ownTx) {
            tx.commit();
        }
    }
}
