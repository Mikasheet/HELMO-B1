package chap8;

import io.Console;

public class Ex3_JoursParMois {

	public static void main(String[] args) {
		
		int mois  = Console.lireInt("Mois (de 1 à 12) ? ");
		int annee = Console.lireInt("Année ? ");

		System.out.printf("Il y a %d jours dans le mois %d/%d.\n", joursParMois(mois, annee), mois, annee);
	}
	
	public static boolean estBissextile(int annee) {
		return (annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0; 
	}
	
	public static int joursParMois(int mois, int annee) {
		//TODO: Utiliser un tableau qui répertorie le nombe de jours dans chaque mois
		//      à la place du switch.
		return switch (mois) {
		case 1, 3, 5, 7, 8, 10, 12 -> 31;
		case 4, 6, 9, 11 -> 30;
		case 2 -> (estBissextile(annee)) ? 29 : 28;
		default -> 0;
		};
	}

}
