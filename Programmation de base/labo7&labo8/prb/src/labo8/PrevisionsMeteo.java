package labo8;

import java.util.Arrays;

import io.Console;
import util.Date;
import util.TableauChaines;
import util.TableauReels;

public class PrevisionsMeteo {

	static final int NB_JOURS = 5;

	public static void main(String[] args) {

		final int NB_CHOIX = 4;
		final int NB_JOURS = 5;

		String[] nomsLocalites = new String[0];
		double[][] temperatures = new double[0][];
		int choix;

		// Saisie auto
		Console.simulerSaisies("1", "Bruxelles", "4", "6", "8", "6", "4", "1", "Eupen", "3", "2", "5", "3", "2", "1",
				"Liège", "4", "5", "7", "5", "4", "1", "Ostende", "5", "7", "9", "7", "4");
		do {
			System.out.println("Prévisions météo");
			System.out.println("1. Ajouter une localité et ses prévisions");
			System.out.println("2. Afficher les prévisions");
			System.out.println("3. Analyser les prévisions");
			System.out.println("4. Quitter ");
			System.out.println();

			choix = choixAction(NB_CHOIX);
			switch (choix) {

			// Ajouter une localité et ses prévisions
			case 1:

				// Saisie localités
				String nomLocalite = Console.lireString("Nom de la localité ? ");
				while (nomLocalite == null || nomLocalite.isEmpty()) {
					System.out.println("Nom invalide ! Recommence ...");
					nomLocalite = Console.lireString("Nom de la localité ? ");
				}
				nomsLocalites = TableauChaines.ajouterElement(nomsLocalites, nomLocalite);

				if (temperatures == null || temperatures.length < 1) {
					temperatures = new double[1][NB_JOURS];
				} else {
					temperatures = Arrays.copyOf(temperatures, temperatures.length + 1);
					temperatures[nomsLocalites.length - 1] = new double[NB_JOURS];
				}
				for (int i = 0; i < NB_JOURS; i++) {
					int[] date= Date.ajouterJours(Date.aujourdhui(), i);
					String nomJour = Date.nomJourSemaine(date);
					System.out.printf("%s %d ? ", nomJour, date[0]);
					String temperature = Console.lireString();
					while (temperature.matches("\\D")) {
						System.out.println("Valeur incorrect");
						System.out.printf("%d %s", date[0], nomJour);
						temperature = Console.lireString();
					}
					temperatures[nomsLocalites.length - 1][i] = Double.parseDouble(temperature);
				}
				System.out.println();
				break;

			// Afficher les prévisions
			case 2:
				System.out.printf("%12s","");
				for (int i = 0; i < NB_JOURS; i++) {
					int[] date= Date.ajouterJours(Date.aujourdhui(), i);
					String nomJour = Date.nomJourSemaine(date).substring(0,3).toUpperCase();
					System.out.printf(" %6s", nomJour + " " + date[0]);
				}
				System.out.println();
				for (int i = 0; i < nomsLocalites.length; i++) {
					System.out.printf("%-12s", nomsLocalites[i]);
					for (int j = 0; j < temperatures[i].length; j++) {
						System.out.printf(" %6.1f", temperatures[i][j]);
					}
					System.out.println();
				}
				System.out.println();
				break;

			// Analyser les prévisions
			case 3:
				double[] moyennesLignes = TableauReels.moyennesParLigne(temperatures);
				int maximunLignes = TableauReels.maximum(moyennesLignes);
				System.out.printf("La localité où il fera le plus chaud est %s avec %.1f°C\n", nomsLocalites[maximunLignes], moyennesLignes[maximunLignes]);
				int minimumLignes = TableauReels.minimum(moyennesLignes);
				System.out.printf("La localité où il fera le plus froid est %s avec %.1f°C\n", nomsLocalites[minimumLignes], moyennesLignes[minimumLignes]);
				double[] moyennesColonnes = TableauReels.moyennesParColonne(temperatures);
				int maximunColonnes = TableauReels.maximum(moyennesColonnes);
				int[] date= Date.ajouterJours(Date.aujourdhui(), maximunColonnes);
				String nomJour = Date.nomJourSemaine(date);
				System.out.printf("Le jour le plus chaud est le %s avec %.1f°C\n",nomJour, moyennesColonnes[maximunColonnes]);
				int minimumColonnes = TableauReels.minimum(moyennesColonnes);
				date= Date.ajouterJours(Date.aujourdhui(), minimumColonnes);
				nomJour = Date.nomJourSemaine(date);
				System.out.printf("Le jour le plus froid est le %s avec %.1f°C\n",nomJour, moyennesColonnes[minimumColonnes]);
				System.out.println();
				break;

			// Quitter
			case 4:
				break;

			default:
				break;
			}
		} while (choix != 4);
		System.out.println("Fin du programme.");
	}

	static int choixAction(int nbChoix) {
		String choixStr = Console.lireString("Choix ? ");

		while (choixStr.matches("\\D") || Integer.parseInt(choixStr) < 1 || Integer.parseInt(choixStr) > nbChoix) {
			System.out.println("Choix incorrect ! Recommence ...");
			choixStr = Console.lireString("Choix ? ");
		}
		System.out.println();
		return Integer.parseInt(choixStr);
	}

}
