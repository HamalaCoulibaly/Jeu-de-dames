# Jeu de Dames en Java (POO + Interface Graphique)

Ce projet est une implémentation complète du **jeu de dames** en Java, respectant les règles classiques :
- capture obligatoire,
- promotion en dame,
- captures enchaînées,
- détection automatique de fin de partie.

Il est développé selon les principes de la **programmation orientée objet (POO)** et intègre une **interface graphique interactive** avec **Swing**.

---

## 🖥️ Fonctionnalités principales

- ✅ Déplacement en diagonale (pions et dames)
- ✅ Capture par saut (pion et dame)
- ✅ Promotion automatique en dame
- ✅ Captures multiples automatiques
- ✅ Fin de partie automatique avec annonce du gagnant
- ✅ Interface graphique
- ✅ Menu de démarrage (Nouvelle partie / Quitter)

---

## 🧱 Structure du projet

```bash
.
├── MenuPrincipal.java     # Menu de lancement (interface) 
├── JeuGUI.java            # Interface graphique Swing
├── Jeu.java               # Version console du jeu (textuelle)
├── Plateau.java           # Logique du plateau de jeu
├── Piece.java             # Classe abstraite pour les pièces
├── Pion.java              # Comportement du pion
├── Dame.java              # Comportement de la dame
├── Joueur.java            # Représente un joueur avec ces informations (blanc ou noir)

#Lancer l’interface graphique :
javac *.java
java MenuPrincipal

#Lancer le jeu en mode console (optionnel) :
javac *.java
java Jeu



