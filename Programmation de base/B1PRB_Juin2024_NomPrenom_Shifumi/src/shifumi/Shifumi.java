package shifumi;

import io.Console;
import util.TableauChaines;

public class Shifumi {

	private static final String[] ARMES = { "Ciseaux", "Feuille", "Pierre", "Lézard", "Spock" };

	public static void main(String[] args) {

		// Constantes

		final int POINTS_VICTOIRE = 3;
		final String MESSAGE_VICTOIRE = "Bravo ! Vous gagnez la partie.";
		final String MESSAGE_DEFAITE = "Dommage ! Vous perdez la partie.";
		final String[][] INTERACTIONS = {
			{ "ne font rien contre", "coupent", "sont émoussés par", "décapitent", "sont cassés par" },
			{ "est coupée par", "ne fait rien contre", "enveloppe", "est mangée par", "discrédite" },
			{ "émousse", "est enveloppée par", "ne fait rien contre", "écrase", "est vaporisée par" },
			{ "est décapité par", "mange", "est écrasé par", "ne fait rien contre", "empoisonne" },
			{ "casse", "est discrédité par", "vaporise", "est empoisonné par", "ne fait rien contre" }
		};

		// Données

		String[][] historiqueManches = new String[2][0];
		int pointsJoueur = 0, pointsIA = 0;

		// Traitement

		System.out.println("JEU DU SHIFUMI\n");
		do {
			// Acquisition de l'arme du joueur
			String armeJoueur = selectionnerChaine(ARMES);

			// Choix aléatoire de l'arme pour l'IA
			String armeIA = TableauChaines.valeurAleatoire(ARMES);

			// Mise à jour de l'historique des manches
			historiqueManches[0] = TableauChaines.ajouterElement(historiqueManches[0], armeJoueur);
			historiqueManches[1] = TableauChaines.ajouterElement(historiqueManches[1], armeIA);

			// Affichage des armes
			System.out.printf("%s %s %s\n", armeJoueur,
					INTERACTIONS[TableauChaines.commencePar(ARMES, armeJoueur)][TableauChaines.commencePar(ARMES,
							armeIA)],
					armeIA);

			// Mise à jour des scores
			int cmp = comparer(armeJoueur, armeIA);
			if (cmp > 0) {
				pointsJoueur++;
			} else if (cmp < 0) {
				pointsIA++;
			}

			// Affichage des scores
			System.out.printf("Vous %d - %d IA\n\n", pointsJoueur, pointsIA);

		} while (pointsJoueur != POINTS_VICTOIRE && pointsIA != POINTS_VICTOIRE);

		// Affichage du vainqueur
		if (pointsJoueur > pointsIA) {
			System.out.println(MESSAGE_VICTOIRE);
		} else {
			System.out.println(MESSAGE_DEFAITE);
		}

		// Affichage des statistiques
		System.out.println("\nSTATISTIQUES\n");
		for (String arme : ARMES) {
			System.out.printf("%d fois %s\n", TableauChaines.compter(historiqueManches[0], arme)
					+ TableauChaines.compter(historiqueManches[1], arme), arme);
		}
	}

	/**
	 * Demande à l’utilisateur de sélectionner une chaîne de caractères parmi celles
	 * qui lui sont proposées. Pour ce faire, l’utilisateur doit saisir une chaîne
	 * de caractères non vide qui correspond, sans tenir compte de la casse, au
	 * début (ou à l’entièreté) de l’une des chaînes de caractères présentes dans le
	 * tableau spécifié.
	 * 
	 * @param chainesAdmises le tableau contenant les chaînes de caractères
	 *                       proposées
	 * @return l'une des chaînes de caractères présentes dans le tableau spécifié.
	 */
	public static String selectionnerChaine(String[] chainesAdmises) {
		// Création de la question
		String question = chainesAdmises[0];
		for (int i = 1; i < chainesAdmises.length; i++) {
			question += ", " + chainesAdmises[i];
		}
		question += " ? ";

		// Acquisition de la chaîne
		String saisie;
		int position;
		do {
			saisie = Console.lireString(question).trim();
			position = TableauChaines.commencePar(chainesAdmises, saisie);
		} while (position < 0);
		return chainesAdmises[position];
	}

	/**
	 * Compare deux armes entre elles afin de déterminer le résultat de la
	 * confrontation.
	 * 
	 * @param arme1 le nom de la première arme.
	 * @param arme2 le nom de la deuxième arme.
	 * @return 0, dans le cas d’une égalité. 1, si la première arme spécifiée a
	 *         l’ascendant sur la seconde. -1, si la seconde arme spécifiée a
	 *         l’ascendant sur la première.
	 */
	public static int comparer(String arme1, String arme2) {
		if (arme1.equals(arme2)) {
			return 0;
		}

		int posArme1 = TableauChaines.commencePar(ARMES, arme1);
		int posArme2 = TableauChaines.commencePar(ARMES, arme2);

		if (posArme2 == (posArme1 + 1) % 5 || posArme2 == (posArme1 + 3) % 5) {
			return 1;
		}
		return -1;
	}

}