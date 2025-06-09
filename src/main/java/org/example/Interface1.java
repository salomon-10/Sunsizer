package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class Interface1 extends JFrame {
    private JTextArea logArea = new JTextArea(8, 40);
    private Traitement traitement;
    JFrame jf= new JFrame("Sunsizer");
    String conso;

    //composant
    JTextField txtConso= new JTextField(15);
    JTextField txtIrr= new JTextField(15);
    JTextField txtEtat= new JTextField(15);

    JComboBox<String>TypePanneaux=new JComboBox<>(new String[]{"Aucun","Cellules monocristallines","Module PV Amorphe","Cellules Polycristallines"});
    JComboBox<String> TypeConso = new JComboBox<>(new String[]{" ",
            "Résidentiel", "Tertiaire (ou commercial)", "Industriel", "Agricole", "Autres"
    });

    JButton jb1=new JButton("Calculer");
    JButton jb2=new JButton("Afficher les Données");
    JButton jb3=new JButton("Continuer");
    JButton jb4=new JButton("Actualiser");
    JButton jb5=new JButton("Retour");


    public Interface1(){
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setSize(800, 500);
        jf.setLocationRelativeTo(null);
        jf.setResizable(true);
        Actions();
        intcomposant();
        jf.setVisible(true);
    }

    public void intcomposant(){
        double  Ed =Database.getEc();
        // layout 1
        JPanel jp1 = new JPanel(new GridLayout(0, 1, 5, 5));
        jp1.setBorder(BorderFactory.createTitledBorder("Entrées"));
        jp1.add(new JLabel("Consomation Journaliere :"));
        if(Ed>00.0){
            txtConso.setText(String.valueOf(Ed));
        }else{
            txtConso.setText(" ");
        }
        jp1.add(txtConso);
        jp1.add(new JLabel("Irradiation du milieu:"));
        jp1.add(txtIrr);
        jp1.add(jb1);
        jb1.setBackground(Color.lightGray);
        jb1.setForeground(Color.blue);
        //layou2
        JPanel jp2 = new JPanel(new GridLayout(0, 1, 5, 5));
        jp2.setBorder(BorderFactory.createTitledBorder("Résultats"));
        jp2.add(new JLabel("Type de Panneaux Recomendé :"));
        jp2.add(TypePanneaux);
        jp2.add(new JLabel("Type de Consomation :"));
        jp2.add(TypeConso);
        jp2.add(new JLabel("Etat:"));
        jp2.add(txtEtat);
        jp2.add(jb2);
        jb2.setBackground(Color.lightGray);
        jb2.setForeground(Color.blue);


        logArea.setEditable(false);
        JScrollPane scrollLogs = new JScrollPane(logArea);


// Layout principal
        JPanel JP = new JPanel(new BorderLayout(10, 10));
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        centerPanel.add(jp1);
        centerPanel.add(jp2);
        JP.add(centerPanel, BorderLayout.CENTER);


        JPanel jp3 = new JPanel(new BorderLayout());
        jp3.add(scrollLogs, BorderLayout.CENTER);
        JPanel Bas2Page = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Bas2Page.add(jb5);
        Bas2Page.add(jb4);
        Bas2Page.add(jb3);

        jp3.add(Bas2Page, BorderLayout.SOUTH);
        jb5.setBackground(new Color(255, 120, 120));
        jb4.setBackground(Color.CYAN);
        jb3.setBackground(Color.GREEN);
        JP.add(jp3, BorderLayout.SOUTH);
        JP.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jf.setContentPane(JP);


    }


    //Actions
    public void Actions() {
        jb1.addActionListener((ActionEvent e) -> {
            try {
                double Ec = Double.parseDouble(txtConso.getText());
                double Ir = Double.parseDouble(txtIrr.getText());
                    // instanciation
                    traitement = new Traitement(Ec, Ir, 0.0, 0.0, 0.0, 0.0); //valeur pr defaut
                    traitement.EnrgiePr();
                    traitement.Puissance();
                    traitement.ChoixPanneau();
                    traitement.NbrPan();
                txtEtat.setText("✅✅ Vérification terminé !");
                TypePanneaux.setSelectedItem(traitement.TypePn);
                Database.setEc(Ec);

            } catch (NumberFormatException ex) {
                txtEtat.setText("❌❌Erreur 404 !");
                logArea.append("Erreur de saisie : vérifie les champs numériques.\n\n");
            }
        });

        jb2.addActionListener((ActionEvent e) -> {
            choixConso();
            if (TypeConso.getSelectedItem() == " ") {
                JOptionPane.showMessageDialog(null, "❌ Aucun type sélectionné !");
            }else {
            logArea.append(">> Résultats du calcul :\n");
            logArea.append("🔹Puissance Crête (Pc):    " + traitement.Pc + " Wc\n");
            logArea.append("🔹Énergie à produire (Ep):    " + traitement.Ep + " Wh\n");
            logArea.append("🔹Type panneau:   " + traitement.TypePn + "\n");
            logArea.append("🔹Type Consomation :   "+conso+ "\n");
            logArea.append("🔹Rendement:   " + traitement.Rd + "\n");
            logArea.append("🔹Tension du module:   " + traitement.TensionMod + "\n");
            logArea.append("🔹Nombre de panneaux:   " + traitement.Np + "\n\n");
            }

        });

        jb3.addActionListener((ActionEvent e) -> {
            try {
                double Ec = Database.getEc();
                double Ir = Double.parseDouble(txtIrr.getText());
                double Ep=traitement.Ep;
                double Pc=traitement.Pc;
                String TypePn=traitement.TypePn;
                String TypeConso=Database.getConso();
                String Rd=traitement.Rd;
                double TensionMod=traitement.TensionMod;
                double Np=traitement.Np;
                new Interface2(Ec, Ir,Pc,Ep,TypePn,TypeConso,Rd,TensionMod,Np); // sauvegarde le données
                jf.dispose();
            } catch (NumberFormatException ex) {
                txtEtat.setText("❌ Erreur : Vérifie les valeurs !");
            }
        });

        jb4.addActionListener((ActionEvent e) ->{
            txtConso.setText("");
            txtIrr.setText("");
            txtEtat.setText("");
            logArea.setText("");
        });

        jb5.addActionListener((ActionEvent e) ->{
            new welcome();
            jf.dispose();
        });
        TypeConso.addActionListener(e -> choixConso());//transfers d type conso a choixconso
    }
    public void choixConso() {
        conso = (String) TypeConso.getSelectedItem();
        Database.setConso(conso);
        if (conso != null) {
            switch (conso.trim()) {
                case "Résidentiel":
                  conso = (" Résidentiel");
                    break;
                case "Tertiaire (ou commercial)":
                    conso = (" commercial");
                    break;
                case "Industriel":
                    conso = (" Industriel");
                    break;
                case "Agricole":
                    conso = (" Agricole");
                    break;
                case "Autres":
                    conso = (" Autres");
                    break;
                default:
                    conso = ("  ");
                    break;
            }
        }
    }

    public static void main(String[] args)  throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        SwingUtilities.invokeLater(Interface1::new);

}}