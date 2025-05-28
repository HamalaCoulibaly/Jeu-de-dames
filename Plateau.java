public class Plateau {
    private Piece[][] plateau;

    public Plateau() {
        plateau = new Piece[8][8];
        initialiserPieces();
    }

    public void initialiserPieces() {
        // Initialisation des pions noirs
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    plateau[i][j] = new Pion(i, j, false); // noir
                }
            }
        }

        // Initialisation des pions blancs
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 1) {
                    plateau[i][j] = new Pion(i, j, true); // blanc
                }
            }
        }
    }

    public Piece getPiece(int ligne, int colonne) {
        if (ligne >= 0 && ligne < 8 && colonne >= 0 && colonne < 8) {
            return plateau[ligne][colonne];
        }
        return null;
    }

    //Déplace une pièce d'une position à une autre
    public void deplacerPiece(int ligneDepart, int colDepart, int ligneArrivee, int colArrivee) {
        Piece piece = getPiece(ligneDepart, colDepart);
        // Effectuer le déplacement
        plateau[ligneArrivee][colArrivee] = piece;
        plateau[ligneDepart][colDepart] = null;
        piece.deplacer(ligneArrivee, colArrivee);

        // Suppression de la pièce capturée
        if (Math.abs(ligneArrivee - ligneDepart) == 2 && piece instanceof Pion) {
            // capture simple (pion)
            int ligneCapture = (ligneDepart + ligneArrivee) / 2;
            int colCapture = (colDepart + colArrivee) / 2;
            plateau[ligneCapture][colCapture] = null;
        }

        if (piece instanceof Dame) {
            // capture diagonale longue (dame)
            int pasLigne = Integer.signum(ligneArrivee - ligneDepart);
            int pasCol = Integer.signum(colArrivee - colDepart);
            int x = ligneDepart + pasLigne;
            int y = colDepart + pasCol;

            while (x != ligneArrivee && y != colArrivee) {
                Piece cible = getPiece(x, y);
                if (cible != null && cible.estBlanche() != piece.estBlanche()) {
                    plateau[x][y] = null;  // suppression
                    break;
                }
                x += pasLigne;
                y += pasCol;
            }
        }

        // Promotion du pion s'il arrive au bout
        if (piece instanceof Pion) {
            if ((piece.estBlanche() && ligneArrivee == 0) || (!piece.estBlanche() && ligneArrivee == 7)) {
                plateau[ligneArrivee][colArrivee] = new Dame(ligneArrivee, colArrivee, piece.estBlanche());
            }
        }
    }

    //vérifier si un mouvement est une capture
    public boolean estCapture(int ligneDep, int colDep, int ligneArr, int colArr) {
        return Math.abs(ligneDep - ligneArr) > 1;
    }

    //vérifier si une pièce peut effectuer une capture depuis sa position
    public boolean capturePossibleDepuis(int ligne, int colonne) {
        Piece piece = getPiece(ligne, colonne);
        if (piece == null) return false;

        int[][] directions = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};

        for (int[] d : directions) {
            int midL = ligne + d[0];
            int midC = colonne + d[1];
            int destL = ligne + 2 * d[0];
            int destC = colonne + 2 * d[1];

            if (destL >= 0 && destL < 8 && destC >= 0 && destC < 8) {
                Piece entre = getPiece(midL, midC);
                Piece dest = getPiece(destL, destC);
                if (entre != null && dest == null && entre.estBlanche() != piece.estBlanche()) {
                    return true;
                }
            }
        }

        //Vérification pour les dames (captures longue diagonale)
        if (piece instanceof Dame) {
            for (int[] d : directions) {
                int x = ligne + d[0], y = colonne + d[1];
                boolean ennemiRencontree = false;

                while (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    Piece p = getPiece(x, y);
                    if (p != null) {
                        if (p.estBlanche() == piece.estBlanche() || ennemiRencontree) break;
                        ennemiRencontree = true;
                    } else if (ennemiRencontree) {
                        return true;
                    }
                    x += d[0]; y += d[1];
                }
            }
        }

        return false;
    }

    //Vérifie si un joueur peut encore jouer (a des mouvements possibles)
    public boolean joueurNABesoinDeJouer(boolean estBlanc) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = getPiece(i, j);
                if (p != null && p.estBlanche() == estBlanc) {
                    if (capturePossibleDepuis(i, j)) return true;

                    //Vérifie si un déplacement simple est possible pour une pièce?
                    int[][] directions = {{1,1}, {1,-1}, {-1,1}, {-1,-1}};
                    for (int[] d : directions) {
                        int x = i + d[0];
                        int y = j + d[1];
                        if (x >= 0 && x < 8 && y >= 0 && y < 8 && getPiece(x, y) == null) {
                            if (p instanceof Pion) {
                                int dir = p.estBlanche() ? -1 : 1;
                                if (x == i + dir) return true;
                            } else return true; // dame libre
                        }
                    }
                }
            }
        }
        return false;
    }


    //Pour afficher le plateau dans la console
    public void afficher() {
        System.out.println("  A B C D E F G H");
        for (int i = 0; i < 8; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < 8; j++) {
                Piece p = plateau[i][j];
                if (p == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(p.getSymbole() + " ");
                }
            }
            System.out.println(8 - i);
        }
        System.out.println("  A B C D E F G H\n");
    }
}

