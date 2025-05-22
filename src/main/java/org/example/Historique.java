package org.example;

import javax.swing.*;
import java.awt.*;

public class Historique extends JFrame {

    public Historique() {
        setTitle("Historique des projets");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComposants();
        setVisible(true);
    }

    private void initComposants() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Aucun  projets enregistrés ", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 16));
        panel.add(label, BorderLayout.CENTER);
        setContentPane(panel);
    }
}
