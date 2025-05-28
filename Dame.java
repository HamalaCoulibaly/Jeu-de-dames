public class Dame extends Piece {
    public Dame(int ligne, int colonne, boolean estBlanche) {
        super(ligne, colonne, estBlanche);
    }

    @Override
    //pour valider un déplacement selon les règles de la Dame
    public boolean estDeplacementValide(int nouvelleLigne, int nouvelleColonne, Plateau plateau) {
        int deltaLigne = Math.abs(nouvelleLigne - ligne);
        int deltaColonne = Math.abs(nouvelleColonne - colonne);
        return deltaLigne == deltaColonne;
    }

    @Override
    public char getSymbole() {
        return estBlanche ? 'B' : 'N';
    }
}
