package org.example;

import javax.swing.*;

public class Traitement {
    public Object TypeConso;
    Double Ec, Ir, Uc, Up, Pref, Long, Larg, Lat;

    // Var interne
    double Ep, Pc, Np , TensionMod;
    String TypePn;
    String Conso;
    String Rd;
    private int Nps;
    private int Npp;
    private double Surf;

    public Traitement(Double Ec, Double Ir, Double Uc,  Double Long, Double Larg, Double Lat) {
        this.Ec = Ec;
        this.Ir = Ir;
        this.Uc = Uc;
        this.Long = Long;
        this.Larg = Larg;
        this.Lat = Lat;
    }

    // Getters et Setters
    public Double getEc() {
        return Ec;
    }
    public void setEc(Double ec) {
        Ec = ec;
    }

    public Double getIr() {
        return Ir;
    }
    public void setIr(Double ir) {
        Ir = ir;
    }

    public Double getUc() {
        return Uc;
    }
    public void setUc(Double uc) {
        Uc = uc;
    }


    public Double getLong() {
        return Long;
    }
    public void setLong(Double Long) {
        Long = Long;
    }

    public Double getLarg() {
        return Larg;
    }
    public void setLarg(Double larg) {
        Larg = larg;
    }

    public Double getLat() {
        return Lat;
    }
    public void setLat(Double lat) {
        Lat = lat;
    }

    // Méthodes🤘🤘

    public void EnrgiePr() {
        Ep = Ec / 0.65;
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
        //chiox
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
                recommandation //val pr defaut
        );

        if (choix != null) {
            if (choix.equals("Cellules monocristallines")) {
                TypePn = "Cellules monocristallines";
                Rd = "12-19%"; TensionMod = 48; Pref = 470.0; Up = 37.0;
            } else if (choix.equals("Cellules Polycristallines")) {
                TypePn = "Cellules Polycristallines";
                Rd = "11-13%"; TensionMod = 24; Pref = 200.0; Up = 25.0;
            } else if (choix.equals("Module PV Amorphe")) {
                TypePn = "Module PV Amorphe";
                Rd = "6-10%"; TensionMod = 12; Pref = 325.0; Up = 32.0;
            }
            // maj de bdd
            Database.setPref(Pref);
            Database.setTypePn(TypePn);
            Database.setTensionMod(TensionMod);
            Database.setUp(Up);
        }
    }

    public void NbrPan() {
        Np = Pc / Pref;
    }

    public void Agencement() {
        Nps = (int) (Uc / Up);
        Npp = (int) (Pref*Np /Pc*Nps);

    }
    public void Surface() {
        Surf = Np * Long * Larg * 1.1;

    }

// getters pr les valeu calculer

    public double getSurf() {
        return (int) Surf+1;
    }

    public int getNpp() {
        return Npp;
    }

    public int getNps() {
        return Nps;
    }

    public double getEp() {
        return Ep;
    }
    public double getPc() {
        return Pc;
    }
    public int getNp() {
        return (int) Np+1;
    }
    public String getTypePn() {
        return TypePn;
    }


    public String getRd() {
        return Rd;
    }
    public double getTensionMod() {
        return TensionMod;
    }




}