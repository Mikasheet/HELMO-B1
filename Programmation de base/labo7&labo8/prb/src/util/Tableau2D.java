package util;

public class Tableau2D {
	
	public static void afficher(char[][] t) {
		for (int i = 0; i < t.length; i++) {
			for (int j = 0; j < t[i].length; j++) {
				System.out.printf("%c%s", t[i][j], j == t[i].length - 1 ? "" : " ");
			}
			System.out.println();
		}
	}
}
