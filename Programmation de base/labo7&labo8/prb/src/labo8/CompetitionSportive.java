package labo8;

import io.Console;
import util.TableauChaines;

public class CompetitionSportive {
	
	static int NB_CHOIX = 4;

	public static void main(String[] args) {
		
		String[] nomsEquipes = null; 
		int choix;
		do {
			System.out.println("Compétition de football");
			System.out.println("1. Encoder les équipes");
			System.out.println("2. Afficher le calendrier de la compétition");
			System.out.println("3. Afficher le calendrier d'une équipe ");
			System.out.println("4. Quitter ");
			
			choix = choixAction();
			
			System.out.println();
			switch (choix) {
			
			// Encoder les équipes
			case 1:
				//Saisie auto
				Console.simulerSaisies("6","Aische","FC Huy","RES Durbuy","Richelle","RRC Mormont","Tilleur");
				
				//Saisie équipes
				int nbEquipes = Console.lireInt("Nombres d'équipes ? ");
				while (nbEquipes < 1) {
					System.out.println("Nombre incorrect ! Recommence ...");
					nbEquipes = Console.lireInt("Nombres d'équipes ? ");
				}
				nomsEquipes = encoderNomsEquipes(nbEquipes);
				System.out.println();
				break;
				
			//Afficher le calendrier de la compétition
			case 2:
				if (nomsEquipes == null || nomsEquipes.length < 2) {
					System.out.println("Nombre d'équipes insuffisant (minimum 2) !");
					System.out.println();
					break;
				}
				for (int i = 0; i < nomsEquipes.length - 1; i++) {
					String[][] journee = journeeSuivante(nomsEquipes);
					System.out.printf("Journée %d\n",i + 1);
					System.out.println(journeeToString(journee));
				}
				System.out.println();
				break;
				
			//Afficher le calendrier d'une équipe
			case 3:
				if (nomsEquipes == null || nomsEquipes.length < 2) {
					System.out.println("Nombre d'équipe insuffisant (minimum 2) !");
					System.out.println();
					break;
				}
				String equipeChoisi = nomsEquipes[choisirEquipe(nomsEquipes)];
				for (int i = 0; i < nomsEquipes.length - 1; i++) {
					String[][] journee = journeeSuivante(nomsEquipes);
					System.out.printf("Journée %d\n",i + 1);
					System.out.println(rencontreToString(getRencontre(journee, equipeChoisi)));
					System.out.println();
				}
				break;
				
			//Quitter
			case 4:
				break;
				
			default:
				break;
			}
		} while (choix != 4);
		System.out.println("Fin du programme.");
	}

	static String[] encoderNomsEquipes(int nbEquipes) {
		String[] nomsEquipes = new String[nbEquipes];
		for (int i = 0; i < nomsEquipes.length; i++) {
			System.out.printf("Nom Équipe %d ? ", i + 1);
			String nom = Console.lireString().trim();
			while (nom.isEmpty() || TableauChaines.contient(nomsEquipes, nom)) {
				System.out.println("Nom incorrect !");
				System.out.printf("Nom Équipe %d ? ", i + 1);
				nom = Console.lireString().trim();
			}
			nomsEquipes[i] = nom;
		}
		return nomsEquipes;
	}

	static void decalerADroite(String[] nomsEquipes) {
		TableauChaines.permuter(nomsEquipes, 0, nomsEquipes.length - 1);
		for (int i = nomsEquipes.length - 1; i > 1; i--) {
			TableauChaines.permuter(nomsEquipes, i, i - 1);
		}
	}

	static String[][] journeeSuivante(String[] nomsEquipes) {
		if (nomsEquipes.length <2) {
			return null;
		}
		decalerADroite(nomsEquipes);
		TableauChaines.permuter(nomsEquipes, 0, 1);
		
		String[][] journee = new String[nomsEquipes.length / 2][2];
		for (int i = 0; i < journee.length; i++) {
			journee[i][0] = nomsEquipes[i];
			journee[i][1] = nomsEquipes[nomsEquipes.length - 1 - i];
		}
		return journee;
	}

	static String rencontreToString(String[] rencontre) {
		if (rencontre.length <2) {
			return null;
		}
		return String.format("%s - %s", rencontre[0], rencontre[1]);
	}

	static String journeeToString(String[][] journee) {
		String journeeString = "";
		for (String[] strings : journee) {
			journeeString += rencontreToString(strings) + "\n";
		}
		return journeeString;
	}

	static String[] getRencontre(String[][] journee, String nomEquipe) {
		for (int i = 0; i < journee.length; i++) {
			if (TableauChaines.contient(journee[i], nomEquipe)) {
				return journee[i];
			}
		}
		return null;
	}

	static int choisirEquipe(String[] nomsEquipes) {
		for (int i = 0; i < nomsEquipes.length; i++) {
			System.out.printf("%d. %s\n", i + 1, nomsEquipes[i]);
		}
		int choix = Console.lireInt("Choix ? ");
		while (choix < 1 || choix > nomsEquipes.length) {
			System.out.println("Choix incorrect ! Recommence ...");
			choix = Console.lireInt("Choix ? ");
		}
		System.out.println();
		return choix - 1;
	}
	
	static int choixAction() {
		int choix = Console.lireInt("Choix ? ");
		while (choix < 1 || choix > NB_CHOIX) {
			System.out.println("Choix incorrect ! Recommence ...");
			choix = Console.lireInt("Choix ? ");
		}
		System.out.println();
		return choix;
	}
}
