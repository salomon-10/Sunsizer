package org.example;

public class Database {
    private static String nomProjet;
    private static double Ec;
    private static double Ir;
    private static double Pref;
    private static double Up;

    //  Traitement
    static double Ep;
    private static double Pc;
    private static String TypePn;
    static String conso;
    private static String Rd;
    private static double TensionMod;
    private static int Np;
    private static int Nps;
    private static double Npp;
    private static double Surface;

    // Setters
    public static void setNomProjet(String nom) {
        nomProjet = nom;
    }

    public static void setEc(double e) {
        Ec = e;
    }

    public static void setIr(double i) {
        Ir = i;
    }

    public static void setPref(double p) {
        Pref = p;
    }

    public static void setEp(double ep) {
        Ep = ep;
    }

    public static void setPc(double pc) {
        Pc = pc;
    }

    public static void setTypePn(String type) {
        TypePn = type;
    }
    public static void setConso(String typeC) {
        conso = typeC;
    }

    public static void setRd(String rd) {
        Rd =rd;
    }

    public static void setTensionMod(double tension) {
        TensionMod = tension;
    }

    public static void setNp(int np) {
        Np = np;
    }

    public static void setNps(int nps) {
        Nps = nps;
    }

    public static void setNpp(double npp) {
        Npp = npp;
    }

    public static void setSurface(double s) {
        Surface= s;
    }


    //  Getters
    public static String getNomProjet() {
        return nomProjet;
    }

    public static double getEc() {
        return Ec;
    }

    public static double   getIr() {
        return Ir;
    }

    public static double getPref() {
        return Pref;
    }

    public static double getEp() {
        return Ep;
    }

    public static double getPc() {
        return Pc;
    }

    public static String getTypePn() {
        return TypePn;
    }

    public static String getConso() {
        return conso;
    }

    public static String getRd() {
        return Rd;
    }

    public static double getTensionMod() {
        return TensionMod;
    }

    public static int getNp() {
        return Np;
    }

    public static int getNps() {
        return Nps;
    }

    public static double getNpp() {
        return Npp;
    }

    public static double getSurface() {
        return Surface;
    }


    public static void setUp(Double up) {
        Database.Up = up;
    }
    public static Double getUp() {
        return Up;
    }
}
