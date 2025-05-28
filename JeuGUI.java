import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Interface graphique pour le jeu de dames
public class JeuGUI extends JFrame {
    private Plateau plateau;
    private JButton[][] boutons;
    private Joueur joueur1, joueur2, joueurActuel;
    private int[] caseSelectionnee = null;

    public JeuGUI() {
        //Initialise les composants du jeu
        plateau = new Plateau();
        joueur1 = new Joueur(true);
        joueur2 = new Joueur(false);
        joueurActuel = joueur1;

        //interface utilisateur
        setTitle("Jeu de Dames - Tour du " + joueurActuel.getNom());
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 8));
        boutons = new JButton[8][8];
        initialiserPlateauGraphique();
        setVisible(true);
    }

    //Initialiser le plateau graphique
    private void initialiserPlateauGraphique() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton bouton = new JButton();
                bouton.setFont(new Font("Arial", Font.BOLD, 35));
                int ligne = i;
                int colonne = j;

                bouton.addActionListener(e -> gererClic(ligne, colonne));
                boutons[i][j] = bouton;
                add(bouton);
            }
        }
        mettreAJourAffichage();
    }

    //methode pour gerer le clic sur une case du plateau
    private void gererClic(int ligne, int colonne) {
        if (caseSelectionnee == null) {
            //Sélectionne une case comme point de départ
            Piece piece = plateau.getPiece(ligne, colonne);
            if (piece != null && piece.estBlanche() == joueurActuel.estBlanc()) {
                caseSelectionnee = new int[]{ligne, colonne};
                boutons[ligne][colonne].setBackground(Color.YELLOW);//Background jaune pour une case selectionnée
            }
        } else {
            //Tenter un déplacement vers la case cliquée
            int[] dep = caseSelectionnee;
            Piece piece = plateau.getPiece(dep[0], dep[1]);
            if (piece != null && piece.estDeplacementValide(ligne, colonne, plateau)) {
                boolean capture = plateau.estCapture(dep[0], dep[1], ligne, colonne);
                plateau.deplacerPiece(dep[0], dep[1], ligne, colonne);
                mettreAJourAffichage();

                // Vérifier si le joueur peut capturer à nouveau
                if (capture && plateau.capturePossibleDepuis(ligne, colonne)) {
                    System.out.println("Vous devez continuer à capturer avec cette pièce !");
                    caseSelectionnee = new int[]{ligne, colonne}; // on rejoue avec la même pièce
                    boutons[ligne][colonne].setBackground(Color.YELLOW);//Background reste en jaune si le joueur peut capturer à nouveau
                    return;
                }

                changerJoueur(); // tour suivant

                //Terminer la partie et affiche le résultat
                if (!plateau.joueurNABesoinDeJouer(joueurActuel.estBlanc())) {
                    String gagnant = joueurActuel.estBlanc() ? "Noir" : "Blanc";
                    JOptionPane.showMessageDialog(this, "Partie terminée ! Le joueur " + gagnant + " a gagné !");
                    System.exit(0); // ferme la fenêtre
                }

            }
            caseSelectionnee = null;
            mettreAJourAffichage();
        }
    }


    //Changer le joueur actuel
    private void changerJoueur() {
        joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
        setTitle("Jeu de Dames - Tour de " + joueurActuel.getNom());
    }

    //Mettre à jour l'affichage du plateau
    private void mettreAJourAffichage() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = plateau.getPiece(i, j);
                JButton bouton = boutons[i][j];

                // Couleur de la case
                if ((i + j) % 2 == 0) {
                    bouton.setBackground(new Color(245, 222, 179));
                } else {
                    bouton.setBackground(new Color(139, 69, 19));
                }

                // Affichage de la pièce
                if (piece == null) {
                    bouton.setText("");
                } else {
                    bouton.setText(String.valueOf(piece.getSymbole()));
                    bouton.setForeground(piece.estBlanche() ? Color.WHITE : Color.BLACK);
                }
            }
        }
    }

    //Point d'entrée pour l'interface graphique
    public static void main(String[] args) {
        SwingUtilities.invokeLater(JeuGUI::new);
    }
}
