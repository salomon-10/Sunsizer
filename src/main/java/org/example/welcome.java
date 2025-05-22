package org.example;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class welcome {
    JFrame jf = new JFrame("Sunsizer");
    JPanel JP = new JPanel(new BorderLayout());
    JButton jb1 = new JButton("Historique");
    JButton jb2 = new JButton("Nouveau Projet");
    JButton jb3 = new JButton("Aide");
    JLabel jlim;
    public welcome() {
        jf.setSize(800, 500);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComposants();
        Action();
        jf.setVisible(true);
    }
    public void initComposants() {
        // Chargement de l'image
        try {
            ImageIcon icon = new ImageIcon("src/main/java/img/logo.jpg");
            Image img = icon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            jlim = new JLabel(new ImageIcon(img), JLabel.CENTER);
        } catch (Exception e) {
            jlim = new JLabel("Logo manquant", JLabel.CENTER);
            jlim.setFont(new Font("SansSerif", Font.BOLD, 18));
        }
            jb3.setBackground(Color.lightGray);
        jb1.setBackground(new Color(255, 120, 120));
        jb2.setBackground(new Color(161, 253, 208));
        // Panel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(250, 249, 247));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        jlim.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(jlim);

        JPanel haut2Page = new JPanel(new FlowLayout(FlowLayout.LEFT));
        haut2Page.setBackground(new Color(250, 250, 248));
        haut2Page.add(jb3);
        JPanel Bas2Page = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Bas2Page.setBackground(new Color(250, 250, 248));
        Bas2Page.add(jb1);
        Bas2Page.add(jb2);

        JPanel basPanel = new JPanel(new BorderLayout());
        basPanel.setBackground(new Color(250, 250, 248));
        basPanel.add(haut2Page, BorderLayout.WEST);
        basPanel.add(Bas2Page, BorderLayout.EAST);


        JP.setBackground(new Color(250, 249, 247));
        JP.add(centerPanel, BorderLayout.CENTER);
        JP.add(basPanel, BorderLayout.SOUTH);
        jf.setContentPane(JP);
    }

    public void Action() {
        jb2.addActionListener((ActionEvent e) -> {
            new Interface1();
            jf.dispose();
        });
        jb1.addActionListener((ActionEvent e) -> {
            new Historique();
        });
        jb3.addActionListener((ActionEvent e) -> {
           new Aide();
            jf.dispose();
        });
    }
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        SwingUtilities.invokeLater(welcome::new);
    }
}
