package labo7;

import java.util.Arrays;

import io.Console;
import util.Aleatoire;
import util.TableauEntiers;

public class Lotto {

	static int NOMBRE_TIRAGE = 7;
	static int NOMBRE_MAX = 45;
	static double[] GAIN = new double[] {500000, 75000, 1500, 250, 30, 10, 5, 3 };

	public static void main(String[] args) {

		int[] tirage;
		int[] grille = null;
		int choix;

		do {
			choix = choixAction(grille == null);
			switch (choix) {
			case 1:
				grille = encoderGrille(NOMBRE_TIRAGE - 1, NOMBRE_MAX);
				System.out.printf("Votre grille est %s\n\n", TableauEntiers.toString(grille));
				break;
			case 2:
				tirage = genererTirage(NOMBRE_TIRAGE, NOMBRE_MAX);
				System.out.printf("Le tirage est %s\n", TableauEntiers.toString(tirage));
				if (grille == null) {
					System.out.println("Vous n'avez pas rempli de grille");
				} else {
					int[] numerosGagnants = compterNumerosGagnants(tirage, grille);
					System.out.printf("Vous avez %d numéros gagnants%s\n", numerosGagnants[0],
							numerosGagnants[1] != 1 ? "" : " et le numéro bonus");
					double gain = obtenirGain(determinerRang(numerosGagnants));
					System.out.printf("Vous gagnez %.2f euros\n\n", gain);
				}
				grille = null;
				break;
			case 3:
				break;
			default:
				break;
			}
		} while (choix != 3);

		System.out.println("Fin du programme.");
	}

	static int[] genererTirage(int nbTirages, int numeroMax) {

		int[] tableauNombre = new int[numeroMax];
		Arrays.setAll(tableauNombre, operand -> ++operand);

		for (int i = 0; i < tableauNombre.length; i++) {
			TableauEntiers.permuter(tableauNombre, i, Aleatoire.aleatoire(tableauNombre.length - 1));
		}

		int[] nbTires = new int[nbTirages];
		for (int i = 0; i < nbTires.length; i++) {
			nbTires[i] = tableauNombre[i];
		}
		Arrays.sort(nbTires, 0, nbTirages - 1);

		return nbTires;
	}

	static int[] encoderGrille(int nbNumeros, int numeroMax) {
		int[] grille = new int[nbNumeros];
		for (int i = 0; i < grille.length; i++) {
			int nombre;
			do {
				System.out.printf("Numéro %d ? ", i + 1);
				nombre = Console.lireInt();
			} while (1 > nombre || numeroMax < nombre || TableauEntiers.contient(grille, nombre));
			grille[i] = nombre;
		}
		System.out.println();
		Arrays.sort(grille);
		return grille;
	}

	static int[] compterNumerosGagnants(int[] tirage, int[] grille) {
		int[] numerosGagnants = new int[2];
		for (int i = 0; i < tirage.length - 1; i++) {
			if (TableauEntiers.contient(grille, tirage[i])) {
				numerosGagnants[0]++;
			}
		}
		if (TableauEntiers.contient(grille, tirage[tirage.length - 1])) {
			numerosGagnants[1]++;
		}
		return numerosGagnants;
	}

	static int determinerRang(int[] numerosGagnants) {
		int rang = (NOMBRE_TIRAGE - numerosGagnants[0]) * 2 - 1 - numerosGagnants[1];
		return rang;
	}

	static double obtenirGain(int rang) {
		if (rang <= GAIN.length) {
			return GAIN[rang - 1];
		} else {
			return 0;
		}
	}

	public static int choixAction(boolean existeGrille) {
		System.out.println("Loterie National");
		if (existeGrille) {
			System.out.println("1. Remplir une grille de Lotto");
		} else {
			System.out.println("1. Modifier ma grille de Lotto");
		}
		System.out.println("2. Consulter mon gain pour le dernier tirage");
		System.out.println("3. Quitter");
		int choix = Console.lireInt("Choix ? ");
		System.out.println();
		while (1 > choix || 3 < choix) {
			System.out.println("Choix incorrect !");
			choix = Console.lireInt("Choix ? ");
			System.out.println();
		}
		return choix;
	}
}