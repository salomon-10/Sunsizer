package org.example;

import javax.swing.*;

public class Traitement {
    private double Ec,k, Ir, Uc, Up, longueur, largeur, latitude;
    private double Ep;
    private double Pc;
    private double Np;
    protected double Pref;
    private String TypePn;
    private String TypeSyst;
    private String Rd;
    private int Nps;
    private int Npp;
    private double Surf;

    public Object TypeConso;
    private double tensionMod;

    // Constructeur
    public Traitement(double Ec, double Ir,double k, double Pref, double Uc, double longueur, double largeur, double latitude) {
        this.Ec = Ec;
        this.Ir = Ir;
        this.k=k;
        this.Pref = Pref;
        this.Uc = Uc;
        this.longueur = longueur;
        this.largeur = largeur;
        this.latitude = latitude;
    }

    //mthds
    public void EnrgiePr() {
        Ep = Ec / k;
    }

    public void Puissance() {
        Pc = Ep / Ir;
    }

    public void ChoixPanneau() {
        String recommandation;

        if (Pc > 1000.0) {
            recommandation = "Cellules monocristallines";
        } else if (Pc < 150) {
            recommandation = "Module PV Amorphe";
        } else {
            recommandation = "Cellules Polycristallines";
        }

        String[] options = {
                "Cellules monocristallines",
                "Cellules Polycristallines",
                "Module PV Amorphe"
        };

        String choix = (String) JOptionPane.showInputDialog(
                null,
                "Recommandation : " + recommandation + "\n\nChoisissez le type de panneau :",
                "Sélection du type de panneau",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                recommandation
        );

        if (choix != null) {
            switch (choix) {
                case "Cellules monocristallines":
                    TypePn = choix;
                    Rd = "12-19%";
                    Up = 48.0;
                    break;
                case "Cellules Polycristallines":
                    TypePn = choix;
                    Rd = "11-13%";
                    Up = 24.0;
                    break;
                case "Module PV Amorphe":
                    TypePn = choix;
                    Rd = "6-10%";
                    Up = 12.0;
                    break;
            }
            Database.setTypePn(TypePn);
            Database.setTensionMod(Up);
            Database.setUp(Up);
        }
    }

    public void NbrPan() {
        Np = Math.ceil(Pc / Pref);

    }

    public void Agencement() {
        Nps = (int) Math.ceil(Uc / Up);
        Npp = (int) Math.ceil(Np / (double) Nps);

        int totalOptimal = Nps * Npp;
        if (totalOptimal > Np) {
            Np = totalOptimal;
            JOptionPane.showMessageDialog(null,"🔧 Ajustement: Nombre total de panneaux mis à jour à " + Np
                     +" pour un agencement équilibré.");
            Database.setNp((int) Np);
        }}
    public void Surface() {
        Surf = Np * longueur * largeur * 1.1;

    }
    public void choixSysteme(){String[] systemes = {
            "Autonome",
            "Autonome avec Stockage",
            "Hybride",
            "Hybride avec Stockage"
    };

        String systemeChoisi = (String) JOptionPane.showInputDialog(
                null,
                "💡 Veuillez sélectionner un type de système photovoltaïque :",
                "🔧 Choix du Système Solaire",
                JOptionPane.QUESTION_MESSAGE,
                null,
                systemes,
                systemes[0]
        );

        if (systemeChoisi != null) {
            Database.setSysteme(systemeChoisi);

            if (systemeChoisi.equals(systemes[1]) || systemeChoisi.equals(systemes[3])) {

                double Ep = Database.getEp();
                double Ec = Database.getEc();
                double Vbat = 12;
                double capacite = 100;
                double profondeurDecharge = 0.6;
                double energieStock = Ep - Ec;
                if (energieStock > 0) {
                    double capaciteUtilisableParBatterie = Vbat * capacite * profondeurDecharge;
                    int nbBatteries = (int) Math.ceil(energieStock / capaciteUtilisableParBatterie);
                    Database.setNbBatteries(nbBatteries);
                } else {
                    Database.setNbBatteries(0);
                }
            }
            JOptionPane.showMessageDialog(null, "✅ Système sélectionné : " + systemeChoisi);
        } else {
            JOptionPane.showMessageDialog(null, "❌ Aucun système sélectionné !");
        }

    }




    public double getEp() {
        return Ep;
    }

    public double getPc() {
        return Pc;
    }

    public int getNp() {
        return (int) Math.ceil(Np);
    }

    public int getNps() {
        return Nps;
    }

    public int getNpp() {
        return Npp;
    }

    public double getSurf() {
        return Math.round(Surf * 100.0) / 100.0;
    }

    public String getTypePn() {
        return TypePn;
    }

    public String getRd() {
        return Rd;
    }

    public double getUp() {
        return Up;
    }


    public double getTensionMod() {
        return getUp();
    }

}
