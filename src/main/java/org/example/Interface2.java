package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.example.Database.*;


public class Interface2 extends JFrame{
    private Traitement traitement;
    double Ec, Ir, Pref,Ep,Pc,Np;
    String TypePn,Rd,TensionMod, TypeConso;
    JFrame jf= new JFrame("Sunsizer");

    //composant
    JTextField txtTchamps= new JTextField(15);
    JTextField txtTpann = new JTextField(15);
    JTextField txtLong = new JTextField(15);
    JTextField txtLarg = new JTextField(15);
    JTextField txtLat = new JTextField(15);

    JComboBox<String>Hemisphere=new JComboBox<>(new String[]{"Nord","Sud"});
    JComboBox<String>Orientation=new JComboBox<>(new String[]{"Nord (N)", "Est (E)", "Sud (S)", "Ouest (O ou W)", "Nord-Est (NE)", "Sud-Est (SE)", "Sud-Ouest (SO)", "Nord-Ouest (NO)","Autres"});

    JButton jb2=new JButton("Actualiser");
    JButton jb1=new JButton("Confirmer");

    public Interface2(double Ec, double Ir, double Pref, double pc, double ep, String typePn,String TypeConso, String rd, double tensionMod, double np){
        this.Ec = Ec;
        this.Ir = Ir;
        this.Pref = Pref;
        this.Ep=Ep;
        this.Pc=Pc;
        this.TypePn=TypePn;
        this.TypeConso=TypeConso;
        this.Rd=Rd;
        this.TensionMod=TensionMod;
        this.Np=Np;

        jf.setSize(800, 500);
        jf.setLocationRelativeTo(null);
        jf.setResizable(true);
        Actions();
        intcomposant();
        jf.setVisible(true);
    }


    public void intcomposant(){

        // layout 1
        JPanel jp1 = new JPanel(new GridLayout(0, 1, 5, 5));
        jp1.setBorder(BorderFactory.createTitledBorder("Entrées"));
        jp1.add(new JLabel("Agencement Des Panneaux"+"\n"));
        jp1.add(new JLabel("Tension du Champs :"));
        jp1.add(txtTchamps);
        jp1.add(new JLabel("Tension du Panneau:"));
        jp1.add(txtTpann);
        jp1.add(new JLabel("Dimensions du Panneaux"+"\n"));
        jp1.add(new JLabel("Longeur :"));
        jp1.add(txtLong);
        jp1.add(new JLabel("Largeur :"));
        jp1.add(txtLarg);
        //layou2
        JPanel jp2 = new JPanel(new GridLayout(0, 1, 5, 5));
        jp2.setBorder(BorderFactory.createTitledBorder("Localisation"));
        jp2.add(new JLabel("Hemisphere :"));
        jp2.add(Hemisphere);
        jp2.add(new JLabel("Orientation :"));
        jp2.add(Orientation);
        jp2.add(new JLabel("Latitude:"));
        jp2.add(txtLat);




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
            txtTpann.setText("");
            txtLong.setText("");
            txtLarg.setText("");
            txtLat.setText("");

        });

        jb1.addActionListener((ActionEvent e) -> {
            try {
                double Uc = Double.parseDouble(txtTchamps.getText());
                double Up = Double.parseDouble(txtTpann.getText());
                double Long = Double.parseDouble(txtLong.getText());
                double Larg= Double.parseDouble(txtLarg.getText());
                double Lat = Double.parseDouble(txtLat.getText());

                // instanciation
                traitement = new Traitement(Ec, Ir, Uc, Up, Pref, Long, Larg, Lat);
                traitement.EnrgiePr();
                traitement.Puissance();
                traitement.ChoixPanneau();
                traitement.NbrPan();
                traitement.Agencement();
                traitement.Surface();




// envoie desDonnées dans la bdd :
                Database.setEp(Ep);
                Database.setPref(Pref);
                Database.setIr(Ir);
                Database.setEc(Ec);
                Database.setNp(traitement.getNp());
                Database.setNps(traitement.getNps());
                Database.setNpp(traitement.getNpp());
                Database.setSurface(traitement.getSurf());
                Database.setPc(traitement.getPc());
                Database.setEp(traitement.getEp());
                Database.setTypePn(traitement.getTypePn());
                Database.setTypeConso(TypeConso);
                Database.setRd(traitement.getRd());
                Database.setTensionMod(traitement.getTensionMod());
                new Interface3();
                jf.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(jf, "❌ Erreur : Vérifiez les champs saisis !", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

    }


}