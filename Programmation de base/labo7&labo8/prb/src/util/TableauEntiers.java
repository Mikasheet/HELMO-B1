package util;

public class TableauEntiers {
	
	public static boolean contient(int[] tableau, int a) {
		for (int i = 0; i < tableau.length; i++) {
			if (tableau[i] == a) {
				return true;
			}
		}
		return false;
	}
	
	public static void permuter(int[] tableau, int i, int j) {
		int tmp = tableau[i];
		tableau[i] = tableau[j];
		tableau[j] = tmp;
	}
	
	public static String toString(int[] tableau) {
		if (tableau.length < 1) {
			return null;
		}
		
		String nombres = "";
		
		nombres += tableau[0];
		for (int i = 1; i < tableau.length; i++) {
			nombres += ", " + tableau[i];
		}
		return nombres;
	}
}
