package labo7;

import io.Console;
import util.TableauChaines;

public class Blackjack {

	public static void main(String[] args) {

		// Initialisation jeu de cartes
		String[] cartes = getJeuDeCartes();

		// Mélange du jeu de cartes
		TableauChaines.melanger(cartes);

		// Début du jeu
		int indiceCartes = 0;
		String[] cartesJoueur = new String[11], cartesCroupier = new String[11];
		for (int i = 0; i < 2; i++) {
			cartesJoueur[i] = cartes[indiceCartes];
			indiceCartes++;
			cartesCroupier[i] = cartes[indiceCartes];
			indiceCartes++;
		}

		// Déclarations des variables pour le jeu
		int indiceCartesJoueur = 2;
		int indiceCartesCroupier = 2;
		int pointsJoueur = getValeurCartes(cartesJoueur, 2);
		int pointsCroupier = getValeurCartes(cartesCroupier, 2);

		// Afficher points joueur
		afficherPoints(pointsJoueur, cartesJoueur, 2, true);

		// Afficher points croupier
		if (!(pointsJoueur == 21 && indiceCartesJoueur == 2)) {
			afficherPoints(getValeurCarte(cartesCroupier[0]), cartesCroupier, 1, false);
		} else {
			afficherPoints(pointsCroupier, cartesCroupier, 2, false);
		}

		// Tour du joueur
		while (pointsJoueur < 21 && choixTirage(pointsJoueur)) {

			// Tirage de carte
			cartesJoueur[indiceCartesJoueur] = cartes[indiceCartes];
			indiceCartesJoueur++;
			pointsJoueur = getValeurCartes(cartesJoueur, indiceCartesJoueur);
			indiceCartes++;

			// Afficher points joueur
			afficherPoints(pointsJoueur, cartesJoueur, indiceCartesJoueur, true);
		}

		// Tour du croupier
		while ((pointsCroupier <= pointsJoueur && pointsCroupier < 21 && pointsJoueur < 22)
				&& !(pointsJoueur == 21 && indiceCartesJoueur == 2)) {

			// Tirage de carte
			cartesCroupier[indiceCartesCroupier] = cartes[indiceCartes];
			indiceCartesCroupier++;
			pointsCroupier = getValeurCartes(cartesCroupier, indiceCartesCroupier);
			indiceCartes++;
		}

		// Afficher points croupier
		if (indiceCartesJoueur > 2 || indiceCartesCroupier > 2) {
			afficherPoints(pointsCroupier, cartesCroupier, indiceCartesCroupier, false);
		}

		// Afficher résultat
		if (pointsJoueur == pointsCroupier) {
			System.out.println("Égalité");
		} else if ((pointsJoueur > pointsCroupier && pointsJoueur < 22) || pointsCroupier > 21) {
			System.out.println("Vous gagnez !");
		} else {
			System.out.println("Vous perdez !");
		}
	}

	private static String[] getJeuDeCartes() {

		// Constantes
		final String[] COULEURS = { "coeur", "carreau", "trèfle", "pique" };
		final String[] CARTES = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi", "As" };

		// Variable pour le résultat
		String[] jeuDeCartes;

		// Créer un jeu de cartes
		jeuDeCartes = new String[COULEURS.length * CARTES.length];
		for (int i = 0; i < COULEURS.length; i++) {
			for (int j = 0; j < CARTES.length; j++) {
				jeuDeCartes[CARTES.length * i + j] = CARTES[j] + " de " + COULEURS[i];
			}
		}

		// Retourner le jeu de cartes
		return jeuDeCartes;
	}

	public static int getValeurCarte(String carte) {

		int valeur;

		if (!carte.matches("[2-9].*")) {
			if (carte.startsWith("As")) {
				valeur = 11;
			} else {
				valeur = 10;
			}
		} else {
			valeur = Integer.parseInt(carte.replaceAll("\\D", ""));
		}

		return valeur;
	}

	public static int getValeurCartes(String[] cartes, int nbCartes) {

		// Déclaration variables
		int valeurTotal = 0;
		int valeurCarte;
		int nbAs = 0;

		// Calcule points
		for (int i = 0; i < nbCartes; i++) {
			valeurCarte = getValeurCarte(cartes[i]);
			if (valeurCarte == 11) {
				nbAs++;
			}
			valeurTotal += valeurCarte;
		}

		// Choix de la valeur de l'As
		while (nbAs > 0) {
			if (valeurTotal > 21) {
				valeurTotal -= 10;
			}
			nbAs--;
		}

		// Retourner résultat
		return valeurTotal;
	}

	public static boolean choixTirage(int pointsJoueur) {
		String choix = null;

		choix = Console.lireString("Tirer une carte : (O)ui/(N)on ? ").trim().toLowerCase();
		while (!choix.matches("o|oui|n|non")) {
			System.out.println("Choix invalide ! Recommencez ...");
			choix = Console.lireString("Tirer une carte : (O)ui/(N)on ? ").trim().toLowerCase();
		}
		System.out.println();

		return choix.matches("o|oui");
	}

	public static void afficherPoints(int points, String[] cartes, int indiceCartes, boolean estJoueur) {
		if (estJoueur) {
			System.out.print("Vous avez ");
		} else {
			System.out.print("Le croupier a ");
		}
		System.out.printf("%d points :\n", points);
		System.out.printf("%s\n\n", TableauChaines.toString(cartes, indiceCartes));
	}
}