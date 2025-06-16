package org.example;


public class Database {
    private static String Systeme;
    private static int NbBatteries;


    // donnée saisis
    private static String nomProjet;
    private static double Ec;
    private static double Ir;
    private static double Pref;
    private static double Up;

    // paramtres
    private static String conso;
    private static String Rd;
    private static double TensionMod;

    //résultas
    private static double Ep;
    private static double Pc;
    private static String TypePn;
    private static int Np;
    private static int Nps;
    private static int Npp;
    private static double Surface;
    private static double k;

    // getters
    public static String getNomProjet() { return nomProjet; }
    public static double getEc() { return Ec; }
    public static double getIr() { return Ir; }
    public static double getPref() { return Pref; }
    public static double getUp() { return Up; }
    public static String getConso() { return conso; }
    public static String getRd() { return Rd; }
    public static double getTensionMod() { return TensionMod; }

    public static double getEp() { return Ep; }
    public static double getPc() { return Pc; }
    public static String getTypePn() { return TypePn; }
    public static int getNp() { return Np; }
    public static int getNps() { return Nps; }
    public static int getNpp() { return Npp; }
    public static double getSurface() { return Surface; }
    public static String getSysteme() {return Systeme;}
    public static int getNbBatteries() {return NbBatteries;}


    // setter
    public static void setNomProjet(String nom) { nomProjet = nom; }
    public static void setEc(double e) { Ec = e; }
    public static void setIr(double i) { Ir = i; }
    public static void setPref(double p) { Pref = p; }
    public static void setUp(double up) { Up = up; }
    public static void setConso(String c) { conso = c; }
    public static void setRd(String r) { Rd = r; }
    public static void setTensionMod(double t) { TensionMod = t; }
    public static void setEp(double ep) { Ep = ep; }
    public static void setPc(double pc) { Pc = pc; }
    public static void setTypePn(String type) { TypePn = type; }
    public static void setNp(int np) { Np = np; }
    public static void setNps(int nps) { Nps = nps; }
    public static void setNpp(int npp) { Npp = npp; }
    public static void setSurface(double s) { Surface = s; }
    public static void setSysteme(String s) {Systeme = s;}
    public static void setNbBatteries(int n) {NbBatteries = n;}

    public static void setK(double k) {
        Database.k = k;
    }
}
