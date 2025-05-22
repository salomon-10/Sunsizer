package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Aide {
    JFrame jf= new JFrame("Sunsizer");
    JButton jb1 = new JButton("Retour");
    public Aide(){
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setSize(1920, 787);
        jf.setLocationRelativeTo(null);
        initcomposant();
        jf.setResizable(true);
        jf.setVisible(true);
    }


    public void initcomposant() {
        // Création du panneau principal avec BorderLayout
        JPanel JP = new JPanel(new BorderLayout());
        JP.setBackground(Color.white);

        // Texte d'aide
        String text1 = """
        🟪 Qu'est-ce que SunSizer :

        SunSizer est une application intelligente conçue pour aider à dimensionner un système photovoltaïque (solaire) en fonction de la consommation énergétique quotidienne d’un utilisateur.
        Elle permet, de manière simple et rapide, de :
        ✅ Estimer le nombre de panneaux solaires nécessaires pour répondre à vos besoins énergétiques ;
        ✅ Recommander le type de panneau adapté à votre situation ;
        ✅ Fournir des données précises sur la production attendue selon la localisation et les caractéristiques techniques saisies.

        🟩 Étape 1 : Saisie des données :

        🔹 Entrez la valeur journalière estimée de votre consommation
        🔹 Entrez l'irradiation du milieu
        🔹 Entrez la puissance de référence d’un panneau
        🔹 Cliquez sur le bouton « Calculer »
        🔹 Un type de panneau vous est automatiquement recommandé en fonction de votre consommation
        🔹 Choisissez votre type de consommation : Résidentiel, Commercial, Industriel, Agricole ou Autre (en cas de doute)
        🔹 Cliquez sur « Afficher les données » pour voir les résultats dans la zone d’affichage
        🔹 Utilisez les boutons « Retour », « Continuer » ou « Actualiser » selon vos besoins

        🟩 Étape 2 : Dernières saisies de données :

        🔹 Entrez la tension du chantier étudié
        🔹 Entrez la tension unitaire d’un panneau
        🔹 Entrez la longueur d’un panneau
        🔹 Entrez la largeur d’un panneau
        🔹 Choisissez l’hémisphère dans lequel se trouve le chantier
        🔹 Choisissez l’orientation du toit ou du chantier
        🔹 Entrez la latitude de votre emplacement
        🔹 Vérifiez vos saisies et confirmez, ou cliquez sur « Actualiser » pour recommencer

        🟩 Étape finale : Exportation PDF :
        
        🔹 Saisissez le nom de votre projet
        🔹 Cliquez sur « Exporter en PDF »
        🔹 Choisissez l’emplacement de sauvegarde souhaité sur votre PC et cliquez sur « Enregistrer »

        📬 Contact :
        📩 Email : sikasalomon4@gmail.com
        📞 Téléphone : +228 71372014/91616251
        📸 Instagram : Salomon__10

        ----------------------
        Développé par SIKA K. Salomon
        © 2025 SunSizer — propulsé par Waddle Corporation. Tous droits réservés.
        """;

        // Zone de texte
        JTextArea box1 = new JTextArea(text1);
        box1.setEditable(false);
        box1.setBackground(Color.white);
        JButton jb1 = new JButton("Retour à l'accueil");
        JPanel Bas2Page = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Bas2Page.add(jb1);
        Bas2Page.setBackground(Color.white);
        JP.add(Bas2Page, BorderLayout.SOUTH);
        JP.add(box1);
        JScrollPane scrollPane = new JScrollPane(box1);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        JP.add(scrollPane, BorderLayout.CENTER);
        jf.setContentPane(JP);

        jb1.addActionListener((ActionEvent e) -> {
            new welcome();
            jf.dispose();
        });
}

    public static void main(String[] args) {
        new Aide();
    }
}
