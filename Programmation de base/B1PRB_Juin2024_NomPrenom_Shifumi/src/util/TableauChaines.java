package util;

import java.util.Arrays;

public class TableauChaines {

	/**
	 * Retourne une copie des éléments d'un tableau de chaînes de caractères à une
	 * dimension augmentée d’un nouvel élément.
	 * 
	 * @param t       le tableau dont les éléments doivent être copiés.
	 * @param element l’élément à ajouter aux éléments du tableau t.
	 * @return un nouveau tableau contenant tous les éléments de t ainsi que le
	 *         nouvel élément, ce dernier étant placé à la dernière position.
	 */
	public static String[] ajouterElement(String[] t, String element) {
		String[] nouveau = Arrays.copyOf(t, t.length + 1);
		nouveau[t.length] = element;
		return nouveau;
	}

	/**
	 * Retourne une chaîne de caractères choisie aléatoirement parmi toutes celles
	 * présentes dans un tableau de chaînes de caractères à une dimension.
	 * 
	 * @param t Le tableau dans lequel la valeur doit être choisie.
	 * @return l'une des chaînes de caractères présentes dans le tableau spécifié.
	 */
	public static String valeurAleatoire(String[] t) {
		return t[Aleatoire.aleatoire(t.length - 1)];
	}

	/**
	 * Détermine la position d’une chaîne de caractères au sein d’un tableau de
	 * chaînes de caractères à une dimension, et ce, sans tenir compte de la casse
	 * (par exemple, la chaîne "ciseaux" est considérée comme équivalente à la
	 * chaîne "Ciseaux").
	 * 
	 * @param t       le tableau contenant les éléments parmi lesquels la recherche
	 *                doit être effectuée.
	 * @param prefixe le début (ou l’entièreté) de la chaîne de caractères
	 *                recherchée.
	 * @return la position de la première occurrence trouvée au sein du tableau (en
	 *         parcourant ce dernier à partir de la position 0), -1 si aucune
	 *         occurrence n'est trouvée.
	 */
	public static int commencePar(String[] t, String prefixe) {
		prefixe = prefixe.toLowerCase();
		for (int i = 0; i < t.length; i++) {
			if (t[i] != null && t[i].toLowerCase().startsWith(prefixe)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Détermine le nombre d’apparitions d’une chaîne de caractères au sein d’un
	 * tableau de chaînes de caractères à une dimension, et ce, en tenant compte de
	 * la casse (par exemple, la chaîne "ciseaux" est considérée comme différente de
	 * la chaîne "Ciseaux").
	 * 
	 * @param t      le tableau contenant les éléments parmi lesquels le
	 *               dénombrement doit être effectuée.
	 * @param chaine la chaîne de caractères à dénombrer.
	 * @return le nombre d'occurrences de la chaîne spécifiée.
	 */
	public static int compter(String[] t, String chaine) {
		int nbFois = 0;
		for (int i = 0; i < t.length; i++) {
			if (t[i] == chaine || (t[i] != null && t[i].equals(chaine))) {
				nbFois++;
			}
		}
		return nbFois;
	}

}
