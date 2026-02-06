package labo8;

import util.Tableau2D;

public class Motifs {

	public static void main(String[] args) {

		Tableau2D.afficher(creerMotifCarre(7));
		System.out.println();
		Tableau2D.afficher(creerMotifTriangles(7));
		System.out.println();
		Tableau2D.afficher(creerMotifX(7));
		System.out.println();
		Tableau2D.afficher(creerMotifPapillon(7));
		System.out.println();
		Tableau2D.afficher(creerMotifLosange(7));
	}

	private static char[][] creerMotifCarre(int taille) {
		char[][] motif = new char[taille][taille];
		for (int i = 0; i < motif.length; i++) {
			for (int j = 0; j < motif[i].length; j++) {
				motif[i][j] = '*';
			}
		}
		return motif;
	}

	private static char[][] creerMotifTriangles(int taille) {
		char[][] motif = new char[taille][taille];
		for (int i = 0; i < motif.length; i++) {
			for (int j = 0; j < motif[i].length; j++) {
				if (i <= j) {
					motif[i][j] = '*';
				} else {
					motif[i][j] = '.';
				}
			}
		}
		return motif;
	}

	private static char[][] creerMotifX(int taille) {
		char[][] motif = new char[taille][taille];
		for (int i = 0; i < motif.length; i++) {
			for (int j = 0; j < motif[i].length; j++) {
				if (i == j || j == motif.length - 1 - i) {
					motif[i][j] = '*';
				} else {
					motif[i][j] = '.';
				}
			}
		}
		return motif;
	}

//	private static char[][] creerMotifPapillon(int taille) {
//		char[][] motif = new char[taille][taille];
//		for (int i = 0; i < (motif.length + 1) / 2; i++) {
//			for (int j = 0; j < motif[i].length; j++) {
//				if (i >= j || j >= motif.length - 1 - i) {
//					motif[i][j] = '*';
//					motif[motif.length - 1 - i][j] = '*';
//				} else {
//					motif[i][j] = '.';
//					motif[motif.length - 1 - i][j] = '.';
//				}
//			}
//		}
//		return motif;
//	}

	private static char[][] creerMotifPapillon(int taille) {
		char[][] motif = new char[taille][taille];
		for (int i = 0; i < motif.length; i++) {
			for (int j = 0; j < motif[i].length; j++) {
				if ((i <= j && j >= motif.length - 1 - i) || (i >= j && j <= motif.length - 1 - i)) {
					motif[i][j] = '*';
				} else {
					motif[i][j] = '.';
				}
			}
		}
		return motif;
	}

//	private static char[][] creerMotifLosange(int taille) {
//		char[][] motif = new char[taille][taille];
//		for (int i = 0; i < motif.length; i++) {
//			for (int j = 0; j < (motif.length + 1) / 2; j++) {
//				if ((i + j ) >= (motif[i].length - 1) / 2 && i - j <= (motif[i].length) / 2) {
//					motif[i][j] = '*';
//					motif[i][motif[i].length - 1 - j] = '*';
//				} else {
//					motif[i][j] = '.';
//					motif[i][motif[i].length - 1 - j] = '.';
//				}
//			}
//		}
//		return motif;
//	}

//	private static char[][] creerMotifLosange(int taille) {
//		char[][] motif = new char[taille][taille];
//		for (int i = 0; i < motif.length; i++) {
//			for (int j = 0; j < motif.length; j++) {
//				if (j >= Math.abs(i - taille / 2) + -((i < taille / 2 && taille % 2 == 0) ? 1 : 0)
//						&& j < taille - Math.abs(i - taille / 2) + ((i < taille / 2 && taille % 2 == 0) ? 1 : 0)) {
//					motif[i][j] = '*';
//				} else {
//					motif[i][j] = '.';
//				}
//			}
//		}
//		return motif;
//	}

//	private static char[][] creerMotifLosange(int taille) {
//		char[][] motif = new char[taille][taille];
//		for (int i = 0; i < motif.length; i++) {
//			for (int j = 0; j < motif[i].length; j++) {
//				if (j >= (taille - 1) / 2 - i && j <= taille / 2 + i && j >= i - taille / 2
//						&& j < (taille) * 3 / 2 - i) {
//					motif[i][j] = '*';
//				} else {
//					motif[i][j] = '.';
//				}
//			}
//		}
//		return motif;
//	}
	

	private static char[][] creerMotifLosange(int taille) {
		int centre = (taille + 1) / 2 - 1;
		char[][] motif = new char[taille][taille];
		for (int i = 0; i < motif.length; i++) {
			for (int j = 0; j < motif[i].length; j++) {
				if (Math.abs(i - centre) + Math.abs(j - centre) <= centre) {
					motif[i][j] = '*';
				} else {
					motif[i][j] = '.';
				}
			}
		}
		return motif;
	}
}
