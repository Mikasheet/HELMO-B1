package util;

import java.util.Arrays;

public class TableauChaines {

	public static void permuter(String[] tableau, int i, int j) {
		String tmp = tableau[i];
		tableau[i] = tableau[j];
		tableau[j] = tmp;
	}

	public static void melanger(String[] tableau) {
		for (int i = 0; i < tableau.length; i++) {
			permuter(tableau, i, Aleatoire.aleatoire(tableau.length - 1));
		}
	}

	public static String toString(String[] tableau, int nbElements) {
		if (tableau.length < nbElements) {
			return null;
		}

		String elements;

		elements = tableau[0];
		for (int i = 1; i < nbElements; i++) {
			elements += ", " + tableau[i];
		}
		return elements;
	}

	public static boolean contient(String[] t, String chaine) {
		for (int i = 0; i < t.length; i++) {
			if (chaine.equals(t[i])) {
				return true;
			}
		}
		return false;
	}

	public static String[] ajouterElement(String[] t, String element) {
		if (t == null || t.length < 1) {
			t = new String[1];
		} else {
			t = Arrays.copyOf(t, t.length + 1);
		}

		t[t.length - 1] = element;
		return t;
	}
}
