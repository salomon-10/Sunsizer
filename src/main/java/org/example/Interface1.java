package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class Interface1 extends JFrame {
    private JTextArea logArea = new JTextArea(8, 40);
    private Traitement traitement;
    private double k = -1.0;
    JFrame jf= new JFrame("Sunsizer");
    String conso;

    //composant
    JTextField txtConso= new JTextField(15);
    JTextField txtIrr= new JTextField(15);
    JTextField txtPref = new JTextField(15);
    JTextField txtEtat= new JTextField(15);
    String txtK;
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
        jp1.add(new JLabel(" Puissance référentiel:"));
        jp1.add(txtPref);
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
            txtK = JOptionPane.showInputDialog("💡 Entrez la constante de perte k (ex : 1.2) :");
            if (txtK == null || txtK.trim().isEmpty()) {
                txtEtat.setText("❌ Saisie de la constante annulée !");
                return;
            }
            try {
                k = Double.parseDouble(txtK.trim());  // trim pr annuler les espacmts
            } catch (NumberFormatException ex) {
                txtEtat.setText("❌ Constante de perte invalide !");
                return;
            }

            try {
                double Ec = Double.parseDouble(txtConso.getText().trim());
                double Ir = Double.parseDouble(txtIrr.getText().trim());
                double Pref = Double.parseDouble(txtPref.getText().trim());

                if (Ec <= 0 || Ir <= 0 || Pref <= 0) {
                    txtEtat.setText("❌ Tous les champs doivent être > 0 !");
                    return;
                }

                // ✅ Stocker les valeurs dans la base tout de suite
                Database.setEc(Ec);
                Database.setIr(Ir);
                Database.setPref(Pref);
                Database.setK(k);

                traitement = new Traitement(Ec, Ir, k, Pref, 0.0, 0.0, 0.0, 0.0);
                traitement.EnrgiePr();
                traitement.Puissance();
                traitement.ChoixPanneau();
                traitement.NbrPan();

                txtEtat.setText("✅✅ Vérification terminée !");
                TypePanneaux.setSelectedItem(traitement.getTypePn());

            } catch (NumberFormatException ex) {
                txtEtat.setText("❌❌ Erreur de saisie !");
                logArea.append("Erreur de saisie : vérifie les champs numériques.\n\n");
            }
        });


        jb2.addActionListener((ActionEvent e) -> {
            choixConso();
            if (TypeConso.getSelectedItem() == null || ((String)TypeConso.getSelectedItem()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "❌ Aucun type sélectionné !");
            } else if (traitement == null) {
                JOptionPane.showMessageDialog(null, "❌ Calculer d'abord !");
            } else {
                logArea.append(">> Résultats du calcul :\n");
                logArea.append(String.format("🔹 Puissance Crête (Pc): %.2f Wc\n", traitement.getPc()));
                logArea.append(String.format("🔹 Énergie à produire (Ep): %.2f Wh\n", traitement.getEp()));
                logArea.append("🔹 Type panneau: " + traitement.getTypePn() + "\n");
                logArea.append(String.format("🔹 Puissance référentiel : %.2f\n", traitement.Pref));
                logArea.append("🔹 Type Consommation : " + conso + "\n");
                logArea.append("🔹 Rendement: " + traitement.getRd() + "\n");
                logArea.append(String.format("🔹 Tension du module: %.2f V\n", traitement.getUp()));
                logArea.append(String.format("🔹 Nombre de panneaux: %d\n\n", traitement.getNp()));
            }
        });



            jb3.addActionListener((ActionEvent e) -> {
                if (traitement == null) {
                    txtEtat.setText("❌ Calculer d'abord !");
                    return;
                }

                if (k <= 0) {
                    JOptionPane.showMessageDialog(null, "❌ Constante de perte non définie ou invalide !");
                    return;
                }

                try {
                    // Récupération depuis Database
                    double Ec = Database.getEc();
                    double Ir = Database.getIr();
                    double Pref = Database.getPref();
                    String TypePn = traitement.getTypePn();
                    String Rd = traitement.getRd();
                    double TensionMod = traitement.getUp();
                    int Np = traitement.getNp();
                    double Ep = traitement.getEp();
                    double Pc = traitement.getPc();
                    String TypeConso = Database.getConso();

                    // Enregistrement dans Database
                    Database.setEp(Ep);
                    Database.setPc(Pc);
                    Database.setTypePn(TypePn);
                    Database.setRd(Rd);
                    Database.setTensionMod(TensionMod);
                    Database.setNp(Np);
                    Database.setK(k);

                    // Lancement interface suivante
                    new Interface2(Ec, k, Ir, Pc, Pref, Ep, TypePn, TypeConso, Rd, TensionMod, Np);
                    jf.dispose();

                } catch (Exception ex) {
                    txtEtat.setText("❌ Erreur lors du passage à l'étape suivante !");
                    logArea.append("Exception : " + ex.getMessage() + "\n");
                }
            });



        jb4.addActionListener((ActionEvent e) -> {
            txtConso.setText("");
            txtIrr.setText("");
            txtPref.setText("");
            txtEtat.setText("");
            logArea.setText("");
            TypePanneaux.setSelectedIndex(0);
            TypeConso.setSelectedIndex(0);
        });
        jb5.addActionListener((ActionEvent e) ->{
            new welcome();
            jf.dispose();
        });
        TypeConso.addActionListener(e -> choixConso());//transfers d type conso a choixconso
    }
    public void choixConso() {
        conso = (String) TypeConso.getSelectedItem();
        if (conso != null) {
            switch (conso.trim()) {
                case "Résidentiel":
                    conso = "Résidentiel";
                    break;
                case "Tertiaire (ou commercial)":
                    conso = "Commercial";
                    break;
                case "Industriel":
                    conso = "Industriel";
                    break;
                case "Agricole":
                    conso = "Agricole";
                    break;
                case "Autres":
                    conso = "Autres";
                    break;
                default:
                    conso = "";
                    break;
            }
        }
        Database.setConso(conso);
    }


    public static void main(String[] args)  throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        SwingUtilities.invokeLater(Interface1::new);

}}