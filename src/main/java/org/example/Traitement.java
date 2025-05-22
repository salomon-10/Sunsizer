package org.example;

public class Traitement {
    public Object TypeConso;
    Double Ec, Ir, Uc, Up, Pref, Long, Larg, Lat;

    // Var interne
    double Ep, Pc, Np , TensionMod;
    String TypePn;
    String Rd;
    private int Nps;
    private int Npp;
    private double Surf;

    public Traitement(Double Ec, Double Ir, Double Uc, Double Up, Double Pref, Double Long, Double Larg, Double Lat) {
        this.Ec = Ec;
        this.Ir = Ir;
        this.Uc = Uc;
        this.Up = Up;
        this.Pref = Pref;
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

    public Double getUp() {
        return Up;
    }
    public void setUp(Double up) {
        Up = up;
    }

    public Double getPref() {
        return Pref;
    }
    public void setPref(Double pref) {
        Pref = pref;
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
        if (Pc > 1000.0) {
            TypePn = "Cellules monocristallines";Rd = "12-19%";TensionMod = 48;
        } else if (Pc < 150) {
            TypePn = "Module PV Amorphe";Rd = "6-10%";TensionMod = 12;
        } else {
            TypePn = "Cellules Polycristallines";Rd = "11-13%";TensionMod = 24;
        }
    }
    public void NbrPan() {
        Np = Pc / Pref;
    }

    public void Agencement() {
        Nps = (int) (Uc / Up);
        Npp = (int) (Np / Nps);

    }
    public void Surface() {
        Surf = Np * Long * Larg * 1.1;

    }

// getters pr les value calculer

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