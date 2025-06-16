package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Historique extends JFrame {
    private DefaultTableModel tableModel;

    public Historique() {
        setTitle("Historique des projets");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComposants();
        setVisible(true);
    }
    private void initComposants() {

            JPanel JP = new JPanel(new BorderLayout());

            tableModel = new DefaultTableModel(new Object[]{"Nom du projet", "Date de création"}, 0);
            JTable table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            chargerHistorique();

            if (tableModel.getRowCount() == 0) {
                JLabel label = new JLabel("Aucun projet enregistré", SwingConstants.CENTER);
                label.setFont(new Font("SansSerif", Font.PLAIN, 16));
                JP.add(label, BorderLayout.CENTER);
            } else {
                JP.add(scrollPane, BorderLayout.CENTER);
            }


            JButton effacerBtn = new JButton("🗑️ Effacer l'historique");
            effacerBtn.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous vraiment effacer tout l'historique ?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    File fichier = new File(System.getenv("APPDATA") + File.separator + "Sunsizer" + File.separator + "historique.txt");
                    if (fichier.exists()) {
                        fichier.delete();
                    }
                    tableModel.setRowCount(0);
                    JOptionPane.showMessageDialog(null, "Historique effacé avec succès !");
                }
            });
            JPanel basPanel = new JPanel();
            basPanel.add(effacerBtn);
            JP.add(basPanel, BorderLayout.SOUTH);

            setContentPane(JP);
        }



    private void chargerHistorique() {
        File fichier = new File(System.getenv("APPDATA") + File.separator + "Sunsizer" + File.separator + "historique.txt");
        if (!fichier.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                int index = ligne.lastIndexOf(" - ");
                if (index != -1) {
                    String nomProjet = ligne.substring(0, index).trim();
                    String date = ligne.substring(index + 3).trim();
                    tableModel.addRow(new Object[]{nomProjet, date});
                }
            }} catch (IOException e) {
        }}
    public static void ajouterProjet(String nomProjet) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String date = sdf.format(new Date());
        String ligne = nomProjet + " - " + date;
        File dossier = new File(System.getenv("APPDATA") + File.separator + "Sunsizer");
        if (!dossier.exists()) {
            dossier.mkdirs();  //new dossier
        }
        File fichier = new File(dossier, "historique.txt");
        try {
            if (fichier.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
                    String ligneExistante;
                    while ((ligneExistante = reader.readLine()) != null) {
                        if (ligneExistante.equals(ligne)) return;  
                    }}}
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier, true))) {
                writer.write(ligne);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement : " + e.getMessage());
        }}
}



