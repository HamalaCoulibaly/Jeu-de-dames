import java.util.Scanner;

public class Jeu {
    private Plateau plateau;
    private Joueur joueur1;
    private Joueur joueur2;
    private Joueur joueurActuel;
    private Scanner scanner;

    public Jeu() {
        plateau = new Plateau();
        joueur1 = new Joueur(true);//Joueur Blanc
        joueur2 = new Joueur(false);//Joueur Noir
        joueurActuel = joueur1;//Les joueurs blancs commencent le jeu
        scanner = new Scanner(System.in);
    }

    //pour lancer une partie en mode console
    public void lancer() {
        boolean jeuActif = true;

        while (jeuActif) {
            plateau.afficher();
            System.out.println("Tour de " + joueurActuel.getNom());

            //pour traiter un tour de jeu
            System.out.print("Entrez la position de départ (ex: D6) : ");
            String dep = scanner.nextLine();
            int ligneDep = 8 - Character.getNumericValue(dep.charAt(1));
            int colDep = dep.charAt(0) - 'A';

            System.out.print("Entrez la position d’arrivée (ex: E5) : ");
            String arr = scanner.nextLine();
            int ligneArr = 8 - Character.getNumericValue(arr.charAt(1));
            int colArr = arr.charAt(0) - 'A';

            //effectuer un déplacement si celui-ci est valide
            Piece piece = plateau.getPiece(ligneDep, colDep);

            if (piece != null && piece.estBlanche() == joueurActuel.estBlanc()) {
                if (piece.estDeplacementValide(ligneArr, colArr, plateau)) {
                    plateau.deplacerPiece(ligneDep, colDep, ligneArr, colArr);
                    changerJoueur();
                } else {
                    System.out.println("Déplacement invalide !");
                }
            } else {
                System.out.println("Pièce invalide !");
            }
        }
    }

    //Changer de joueur actuel
    private void changerJoueur() {
        joueurActuel = (joueurActuel == joueur1) ? joueur2 : joueur1;
    }

    // Point d'entrée principal
    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.lancer();
    }
}
