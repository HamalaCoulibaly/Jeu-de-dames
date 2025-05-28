public class Pion extends Piece {
    public Pion(int ligne, int colonne, boolean estBlanche) {
        super(ligne, colonne, estBlanche);
    }

    @Override
    public boolean estDeplacementValide(int nouvelleLigne, int nouvelleColonne, Plateau plateau) {
        // Direction de déplacement selon la couleur
        int direction = estBlanche ? -1 : 1;

        // Déplacement simple d'une case en diagonale
        if (nouvelleLigne == ligne + direction && Math.abs(nouvelleColonne - colonne) == 1) {
            return plateau.getPiece(nouvelleLigne, nouvelleColonne) == null;
        }

        // Capture
        if (nouvelleLigne == ligne + 2 * direction && Math.abs(nouvelleColonne - colonne) == 2) {
            int ligneInter = ligne + direction;
            int colInter = colonne + ((nouvelleColonne - colonne) / 2);
            Piece pieceInter = plateau.getPiece(ligneInter, colInter);

            // Vérifier qu'il y a une pièce adverse à capturer et que la destination est libre
            return pieceInter != null && pieceInter.estBlanche() != estBlanche &&
                    plateau.getPiece(nouvelleLigne, nouvelleColonne) == null;
        }

        return false;
    }


    @Override
    public char getSymbole() {
        return estBlanche ? 'b' : 'n';
    }
}
