public abstract class Piece {
    protected int ligne;
    protected int colonne;
    protected boolean estBlanche;

    public Piece(int ligne, int colonne, boolean estBlanche) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.estBlanche = estBlanche;
    }

    public boolean estBlanche() {
        return estBlanche;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    //Pour mettre à jour la position de la pièce après un déplacement
    public void deplacer(int nouvelleLigne, int nouvelleColonne) {
        this.ligne = nouvelleLigne;
        this.colonne = nouvelleColonne;
    }

    //Une méthode abstraite pour valider un déplacement selon les règles de la pièce
    public abstract boolean estDeplacementValide(int nouvelleLigne, int nouvelleColonne, Plateau plateau);

    //Une méthode abstraite pour obtenir le symbole d'affichage de la pièce
    public abstract char getSymbole();
}
