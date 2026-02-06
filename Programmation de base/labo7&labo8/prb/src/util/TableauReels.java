package util;

import java.util.Arrays;

public class TableauReels {
	
	public static int minimum(double[] t) {
		if (t == null || t.length < 1) {
			return -1;
		}
		int minimum = 0;
		for (int i = 1; i < t.length; i++) {
			if (t[minimum] > t[i]) {
				minimum = i;
			}
		}
		return minimum;
	}
	
	public static int maximum(double[] t) {
		if (t == null || t.length < 1) {
			return -1;
		}
		int maximum = 0;
		for (int i = 1; i < t.length; i++) {
			if (t[maximum] < t[i]) {
				maximum = i;
			}
		}
		return maximum;
	}
	
	public static double moyenne(double[] t) {
		if (t == null || t.length < 1) {
			return Double.NaN;
		}
		double moyenne = 0;
		for (int i = 0; i < t.length; i++) {
			moyenne += t[i];
		}
		return moyenne / t.length;
	}
	
	public static double[][] ajouterLigne(double[][] t, double[] ligne) {
		if (t == null || t.length < 1) {
			t = new double[1][ligne.length];
		} else {
			t = Arrays.copyOf(t, t.length + 1);
		}

		t[t.length - 1] = ligne;
		return t;
	}
	
	public static double[] moyennesParLigne(double[][] t) {
		double[] moyennes = new double[t.length];
		for (int i = 0; i < moyennes.length; i++) {
			moyennes[i] = moyenne(t[i]);
		}
		return moyennes;
	}
	
	public static double[] extraireColonne(double[][] t, int j) {
		if (t == null || t.length < 1 || t.length < 1) {
			return null;
		}
		double[] colonne = new double[t.length];
		for (int i = 0; i < colonne.length; i++) {
			colonne[i] = t[i][j];
		}
		return colonne;
	}
	
	public static double[] moyennesParColonne(double[][] t) {
		double[] moyennes = new double[t[0].length];
		for (int i = 0; i < moyennes.length; i++) {
			moyennes[i] = moyenne(extraireColonne(t, i));
		}
		return moyennes;
	}
}
