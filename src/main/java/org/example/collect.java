package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class collect extends JFrame {
    private double totalPwt = 0;
    private JLabel jl = new JLabel("Consommation Journalière : 0 W");
    JFrame jf = new JFrame("Sunsizer");
    private DefaultTableModel tableModel;
    JTextField txtEquip = new JTextField(6);
    JTextField txtPw = new JTextField(6);
    JTextField txtQuant = new JTextField("1", 6);
    JTextField txtHeures = new JTextField("1", 6);
    JButton jb1 = new JButton("➖");
    JButton jb2 = new JButton("➕");
    JButton jb3 = new JButton("Enregistrer");
    JButton jb4 = new JButton("Suivant");

    public collect() {
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setSize(800, 500);
        jf.setLocationRelativeTo(null);
        jf.setResizable(true);
        Actions();
        intcomposant();
        jf.setVisible(true);
    }

    public void intcomposant() {
        JPanel jp1 = new JPanel(new BorderLayout()); // Meilleur pour intégrer un JScrollPane
        JPanel JP = new JPanel(new BorderLayout(5, 10));
        JPanel centralPanel = new JPanel(new BorderLayout());
        // === TABLEAU ===
        tableModel = new DefaultTableModel(new Object[]{"Équipements","Puissance", "Quantités", "Heures/jours", "Énergie Totales"}, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        jp1.add(scrollPane, BorderLayout.CENTER);
        setContentPane(JP);

        // === jp2 ===
        JPanel jp2 = new JPanel();
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
        Dimension d = new Dimension(240, 250);
        jp2.setPreferredSize(d);
        jp2.setMaximumSize(d);
        jp2.setMinimumSize(d);
        jp2.setBorder(BorderFactory.createTitledBorder("Ajouter un équipement"));
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS)); // disposition verticale

        Box equipementBox = Box.createHorizontalBox();
        equipementBox.add(new JLabel("Équipement : "));
        equipementBox.add(Box.createHorizontalStrut(10));
        equipementBox.add(txtEquip);
        txtEquip.setMaximumSize(new Dimension(200, 30));
        jp2.add(equipementBox);

        Box puissanceBox = Box.createHorizontalBox();
        puissanceBox.add(new JLabel("Puissance (W) : "));
        puissanceBox.add(Box.createHorizontalStrut(10));
        puissanceBox.add(txtPw);
        txtPw.setMaximumSize(new Dimension(200, 30));
        jp2.add(puissanceBox);

        Box dureeBox = Box.createHorizontalBox();
        dureeBox.add(new JLabel("Heures/jour : "));
        dureeBox.add(Box.createHorizontalStrut(10));
        dureeBox.add(txtHeures);
        txtHeures.setMaximumSize(new Dimension(200, 30));
        jp2.add(dureeBox);

        Box quantiteBox = Box.createHorizontalBox();
        quantiteBox.add(new JLabel("Quantité : "));
        quantiteBox.add(Box.createHorizontalStrut(10));
        quantiteBox.add(txtQuant);
        txtQuant.setMaximumSize(new Dimension(200, 30));
        txtQuant.setEditable(true);
        jp2.add(quantiteBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        jb2.setBackground(new Color(161, 253, 208));
        jb1.setBackground(new Color(255, 120, 120));
        jb3.setBackground(new Color(55, 216, 227));
        jb4.setBackground(new Color(161, 253, 208));
        buttonPanel.add(jb1);
        buttonPanel.add(jb2);
        jp2.add(buttonPanel);
        jp2.add(jb3);


        jp2.add(Box.createVerticalStrut(10));
        jb3.setAlignmentX(Component.CENTER_ALIGNMENT);
        jp2.add(jb3);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,250, 0));
        bottomPanel.add(jl);
        bottomPanel.add(jb4);

        centralPanel.add(jp1, BorderLayout.CENTER);
        centralPanel.add(jp2, BorderLayout.EAST);
        JP.add(centralPanel, BorderLayout.CENTER);
        JP.add(bottomPanel, BorderLayout.SOUTH);
        jf.setContentPane(JP);
    }


    public void Actions() {
        jb2.addActionListener(e -> {
            try {
                int qt = Integer.parseInt(txtQuant.getText());
                qt++;
                txtQuant.setText(String.valueOf(qt));
            } catch (NumberFormatException ex) {
                txtQuant.setText("1"); //par defaut 1
            }
        });
        jb1.addActionListener(e -> {
            try {
                int qt = Integer.parseInt(txtQuant.getText());
                if (qt > 0) qt--;
                txtQuant.setText(String.valueOf(qt));
            } catch (NumberFormatException ex) {
                txtQuant.setText("0");
            }
        });
        jb3.addActionListener(e -> {
           apply();
            txtEquip.setText("");
            txtPw.setText("");
            txtQuant.setText("1");
        });
        jb4.addActionListener(e -> {
            double Ec= totalPwt;
            Database.setEc(Ec);
            jf.dispose();
            new Interface1();
        });
    }

    public void apply() {
        String eq = txtEquip.getText();
        String quantiteText = txtQuant.getText();
        String puissanceText = txtPw.getText();
        String heuresText = txtHeures.getText();

        try {
            int qt = Integer.parseInt(quantiteText);
            double pw = Double.parseDouble(puissanceText);
            double heures = Double.parseDouble(heuresText);
            double pwt = qt * pw * heures;

            // Ajout ds tbleau
            tableModel.addRow(new Object[]{eq,pw, qt,heures, pwt});
            totalPwt += pwt;
            jl.setText("Consommation Journalière : " + totalPwt + " W");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer des valeurs numériques valides.",
                    "Erreur de saisie", JOptionPane.ERROR_MESSAGE);
            jf.dispose();
            new collect();
        }
}

        public static void main(String[] args) {
        new collect();
    }
}
