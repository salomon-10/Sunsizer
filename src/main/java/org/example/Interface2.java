package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class Interface2 extends JFrame{

    private Traitement traitement;
    private double Ec, Ir,k, Ep, Pc, Np, Pref;
    private String TypePn, Rd, TensionMod, TypeConso;
    JFrame jf= new JFrame("Sunsizer");

    //composant
    JTextField txtTchamps= new JTextField(15);
    JTextField txtLong = new JTextField(15);
    JTextField txtLarg = new JTextField(15);
    JTextField txtLat = new JTextField(15);

    JComboBox<String>Hemisphere=new JComboBox<>(new String[]{"Nord","Sud"});
    JComboBox<String>Orientation=new JComboBox<>(new String[]{"Nord (N)", "Est (E)", "Sud (S)", "Ouest (O ou W)", "Nord-Est (NE)", "Sud-Est (SE)", "Sud-Ouest (SO)", "Nord-Ouest (NO)","Autres"});

    JButton jb2=new JButton("Actualiser");
    JButton jb1= new JButton("Confirmer");

    public Interface2(double Ec, double k, double Ir, double Pc, double Pref, double Ep,String TypePn, String TypeConso, String Rd, double TensionMod, double Np){
        this.Ec = Ec;
        this.Ir = Ir;
        this.k = k;
        this.Ep = Ep;
        this.Pc = Pc;
        this.Pref = Pref;
        this.TypePn = TypePn;
        this.TypeConso = TypeConso;
        this.Rd = Rd;
        this.TensionMod = String.valueOf(TensionMod);
        this.Np = Np;

        jf.setSize(800, 500);
        jf.setLocationRelativeTo(null);
        jf.setResizable(true);
        Actions();
        intcomposant();
        jf.setVisible(true);
    }


    public void intcomposant(){

        JPanel jp1 = new JPanel(new GridLayout(0, 1, 5, 5));
        jp1.setBorder(BorderFactory.createTitledBorder("Paramètres du champ photovoltaïque"));

        jp1.add(new JLabel("🔌 Tension système (Uc en Volts) :"));
        jp1.add(txtTchamps);
        txtTchamps.setToolTipText("Ex: 48");

        jp1.add(new JLabel("📏 Longueur du panneau (m) :"));
        jp1.add(txtLong);
        txtLong.setToolTipText("Ex: 1.7");

        jp1.add(new JLabel("📏 Largeur du panneau (m) :"));
        jp1.add(txtLarg);
        txtLarg.setToolTipText("Ex: 1.0");

        // Localisation
        JPanel jp2 = new JPanel(new GridLayout(0, 1, 5, 5));
        jp2.setBorder(BorderFactory.createTitledBorder("Localisation"));
        jp2.add(new JLabel("Hémisphère :"));
        jp2.add(Hemisphere);
        jp2.add(new JLabel("Orientation :"));
        jp2.add(Orientation);
        jp2.add(new JLabel(" Latitude du site (°) :"));
        jp2.add(txtLat);
        txtLat.setToolTipText("Ex: 14.7");

// Layout principal
        JPanel JP = new JPanel(new BorderLayout(10, 10));
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(jp1);
        centerPanel.add(jp2);
        JP.add(centerPanel, BorderLayout.CENTER);

        JPanel jp3 = new JPanel(new BorderLayout());
        JPanel Bas2Page = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Bas2Page.add(jb2);
        Bas2Page.add(jb1);
        jb1.setBackground(Color.CYAN);
        jb2.setBackground(Color.GREEN);
        jp3.add(Bas2Page, BorderLayout.SOUTH);

        JP.add(jp3, BorderLayout.SOUTH);
        JP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jf.setContentPane(JP);


    }



    public void Actions() {
        jb2.addActionListener((ActionEvent e) -> {
            txtTchamps.setText("");
            txtLong.setText("");
            txtLarg.setText("");
            txtLat.setText("");

        });

        jb1.addActionListener((ActionEvent e) -> {
            try {
                // Vérifie les champs vides
                if (txtTchamps.getText().isEmpty() || txtLong.getText().isEmpty() ||
                        txtLarg.getText().isEmpty() || txtLat.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(jf, "❌ Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Conversion des champs
                double Uc = Double.parseDouble(txtTchamps.getText().trim());
                double longueur = Double.parseDouble(txtLong.getText().trim());
                double largeur = Double.parseDouble(txtLarg.getText().trim());
                double latitude = Double.parseDouble(txtLat.getText().trim());

                // Validations
                if (Uc <= 0 || longueur <= 0 || largeur <= 0) {
                    JOptionPane.showMessageDialog(jf, "❌ Longueur, largeur et tension doivent être > 0", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (latitude < -90 || latitude > 90) {
                    JOptionPane.showMessageDialog(jf, "❌ Latitude invalide. Elle doit être entre -90° et 90°.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Traitement principal
                traitement = new Traitement(Ec, Ir, k, Pref, Uc, longueur, largeur, latitude);

                traitement.EnrgiePr();
                traitement.Puissance();
                traitement.NbrPan();
                traitement.ChoixPanneau();
                traitement.choixSysteme();
                traitement.Agencement();
                traitement.Surface();

                // Enregistrement des résultats
                Database.setNp(traitement.getNp());
                Database.setNps(traitement.getNps());
                Database.setNpp(traitement.getNpp());
                Database.setSurface(traitement.getSurf());

                new Interface3();
                jf.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(jf, "❌ Veuillez entrer uniquement des valeurs numériques valides.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });


    }



}