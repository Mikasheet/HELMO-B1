package chap8;

import java.util.Arrays;

/**
 * Ce programme permet de calculer et afficher les premiers termes de la suite de
 * Fibonacci : F_n = F_n-1 + F_n-2.
 * 
 * Les premiers termes sont : 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233,
 * 377, 610...
 */
public class Ex2_SuiteFibonacci {

	public static void main(String[] args) {
		//TODO: Acquérir le nombre de termes de la suite à afficher

		//TODO: Créer un tableau permettant d'enregistrer les termes
		int[] t = new int[10];
		
		//TODO: Calculer les termes et les enregistrer dans le tableau
		t[0] = 1;
		for (int i = 1; i < t.length; i++) {
			t[i] = t[i - 1] + 2;
		}
		
		// Afficher le contenu du tableau
		System.out.println(Arrays.toString(t));
	}

}
