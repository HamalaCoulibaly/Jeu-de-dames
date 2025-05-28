public class Joueur {
    private boolean estBlanc;

    public Joueur(boolean estBlanc) {
        this.estBlanc = estBlanc;
    }

    public boolean estBlanc() {
        return estBlanc;
    }

    public String getNom() {
        return estBlanc ? "Joueur Blanc" : "Joueur Noir";
    }
}

