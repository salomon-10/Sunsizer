package org.example;

import javax.swing.*;
import javax.swing.ImageIcon;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.HorizontalAlignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Interface3 extends JFrame{
    private JFrame jf = new JFrame("Sunsizer");

    public Interface3() {
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setSize(740, 700);
        jf.setLocationRelativeTo(null);
        jf.setBackground( new Color(250, 249, 247));
        jf.setResizable(true);
        initComposants();
        jf.setVisible(true);

    }

    private void initComposants() {

        JPanel JP = new JPanel(new BorderLayout());


        JPanel jp1 = new JPanel();
        jp1.setLayout(new BoxLayout(jp1, BoxLayout.Y_AXIS));
        jp1.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JPanel bandeauHaut = new JPanel(new BorderLayout());
        bandeauHaut.setBackground( new Color(250, 249, 247));
        ImageIcon icon = new ImageIcon("src/main/java/img/logo.jpg");
        java.awt.Image img = icon.getImage().getScaledInstance(160, 140, java.awt.Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        bandeauHaut.add(logoLabel, BorderLayout.WEST); // Ajoute à gauche du titre

        JLabel titre = new JLabel("<html><div style='text-align:center;'>" +
                "<span style='font-size:24px; font-family: cursive;'>Sunsizer</span><br>" +
                "<span style='font-size:8px;'>Développé par Sikasalomon4@gmail.com</span>" +
                "</div></html>", SwingConstants.CENTER);
        bandeauHaut.add(titre, BorderLayout.CENTER);
        jp1.add(bandeauHaut);
        jp1.add(Box.createVerticalStrut(10));

        // Zone des champs
        JPanel box = new JPanel(new GridLayout(0, 2, 10, 10));

        box.add(new JLabel("Nom du projet :"));
        JTextField txtNomProjet = new JTextField(Database.getNomProjet());
        box.add(txtNomProjet);

        box.add(new JLabel("Énergie à produire par jour :"));
        JLabel lblEp = new JLabel(Database.getEp() + " Wh");
        box.add(lblEp);

        box.add(new JLabel("Puissance crête (Pc) :"));
        JLabel lblPc = new JLabel(Database.getPc() + " Wc");
        box.add(lblPc);

        box.add(new JLabel("Type de panneaux recommandé :"));
        JLabel lblTypePn = new JLabel(Database.getTypePn());
        box.add(lblTypePn);

        box.add(new JLabel("Type de Consomation:"));
        JLabel lblTypeConso = new JLabel(Database.getTypeConso());
        box.add(lblTypeConso);

        box.add(new JLabel("Rendement :"));
        JLabel lblRd = new JLabel(Database.getRd() + "");
        box.add(lblRd);

        box.add(new JLabel("Tension du Module :"));
        JLabel lblTension = new JLabel(Database.getTensionMod() + " V");
        box.add(lblTension);

        box.add(new JLabel("Nombre de panneaux :"));
        JLabel lblNp = new JLabel(Database.getNp() + "");
        box.add(lblNp);

        box.add(new JLabel("Agencement :"));
        JLabel lblAgencement = new JLabel("Nps = " + Database.getNps() + " |   Npp = " + Database.getNpp());
        box.add(lblAgencement);

        box.add(new JLabel("Surface recommandée :"));
        JLabel lblSurface = new JLabel(Database.getSurface() + " m²");
        box.add(lblSurface);

        jp1.add(box);
        box.setBackground( new Color(250, 249, 247));
        // Signature
        JPanel Baspage = new JPanel(new FlowLayout(FlowLayout.LEFT));
        Baspage.add(new JLabel("date : .............................................."));
        jp1.add(Box.createVerticalStrut(15));
        jp1.add(Baspage);
        JPanel Baspage2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Baspage2.add(new JLabel("Signature : .............................................."));
        jp1.add(Box.createVerticalStrut(15));
        jp1.add(Baspage2);
        Baspage2.setBackground( new Color(250, 249, 247));
        Baspage.setBackground( new Color(250, 249, 247));
        JP.add(jp1, BorderLayout.CENTER);
        jp1.setBackground( new Color(250, 249, 247));
        // Boutons
        JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jp2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JButton jb1 = new JButton("Actualiser");
        JButton jb2 = new JButton("Exporter en PDF");
        jp2.add(jb1);
        jp2.add(jb2);
        jp2.setBackground( new Color(250, 249, 247));
        jb1.setBackground(Color.CYAN);
        jb2.setBackground(Color.GREEN);
        JP.add(jp2, BorderLayout.SOUTH);
        jf.setContentPane(JP);

        // Action Actualiser
        jb1.addActionListener((ActionEvent e) -> {
            txtNomProjet.setText("");
        });

        // Action Exporter en PDF
        jb2.addActionListener((ActionEvent e) -> {
            StringBuilder content = new StringBuilder();
            content.append("🔹Nom du projet : ").append(txtNomProjet.getText()).append("\n");
            content.append("🔹Énergie à produire par jour : ").append(Database.getEp()).append(" Wh\n");
            content.append("🔹Puissance crête (Pc) : ").append(Database.getPc()).append(" Wc\n");
            content.append("🔹Type de panneaux recommandé : ").append(Database.getTypePn()).append("\n");
            content.append("🔹Type de Consomation : ").append(Database.getTypeConso()).append("\n");
            content.append("🔹Rendement : ").append(Database.getRd()).append("\n");
            content.append("🔹Tension du Module : ").append(Database.getTensionMod()).append(" V\n");
            content.append("🔹Nombre de panneaux : ").append(Database.getNp()).append("\n");
            content.append("🔹Agencement : Nps = ").append(Database.getNps()).append(" | Npp = ").append(Database.getNpp()).append("\n");
            content.append("🔹Surface recommandée : ").append(Database.getSurface()).append(" m²\n\n");
            content.append("Date : ..............................................\n \n");

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Enregistrer le PDF");
            fileChooser.setSelectedFile(new File(txtNomProjet.getText() + ".pdf"));
            int userSelection = fileChooser.showSaveDialog(jf);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
               generatePDF(content.toString(), fileToSave);
            }
        });
    }

// générer le PDF
private static void generatePDF(String content, File outputFile) {

    try {
        PdfWriter writer = new PdfWriter(outputFile.getAbsolutePath());
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);
        try {
            // Charger l'image
            String imagePath = "src/main/java/img/logo.jpg"; // mets le bon chemin
            ImageData imageData = ImageDataFactory.create(imagePath);
            Image logo = new Image(imageData).scaleToFit(180, 180).setHorizontalAlignment(HorizontalAlignment.LEFT);
            document.add(logo);
        } catch (Exception e) {
            System.out.println("Logo non trouvé : " + e.getMessage());
        }
        Paragraph titre1 = new Paragraph("Sunsizer")
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER)
                .setFontSize(34)
                .setBold();
        Paragraph sousTitre = new Paragraph("Développé par Sikasalomon4@gmail.com")
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER)
                .setFontSize(8)
                .setItalic();

        document.add(titre1);
        document.add(sousTitre);
        document.add(new Paragraph("\n"));
        String[] lignes = content.split("\n");
        for (String ligne : lignes) {
            document.add(new Paragraph(ligne));
        }
        Paragraph signature = new Paragraph("\"Signature : ..............................................\"")
                .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.RIGHT)
                .setFontSize(10)
                .setItalic();
        document.add(signature);

        document.close();
        JOptionPane.showMessageDialog(null, "PDF généré avec succès !");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Erreur PDF : " + ex.getMessage());
        ex.printStackTrace();
    }
}
    public static void main(String[] args) {

       new Interface3();
    }
}
