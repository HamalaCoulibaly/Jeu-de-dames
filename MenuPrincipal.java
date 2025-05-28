import javax.swing.*;

public class MenuPrincipal {
    public static void main(String[] args) {
        String[] options = {"Nouvelle Partie", "Quitter"};
        int choix = JOptionPane.showOptionDialog(
                null,
                "Bienvenue dans le jeu de dames !",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choix == 0) {
            SwingUtilities.invokeLater(JeuGUI::new);
        } else {
            System.exit(0);
        }
    }
}