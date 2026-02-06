package chap8;

import io.Console;

/**
 * Programme qui traduit un message en code morse.
 * 
 * Par exemple, le message "CODE MORSE" est traduit en "-.-. --- -.. . / -- --- .-. ... ."
 */
public class Ex6_CodeMorse {

	private static final String[] ALPHABET_MORSE = {
	    ".-",    // A
	    "-...",  // B
	    "-.-.",  // C
	    "-..",   // D
	    ".",     // E
	    "..-.",  // F
	    "--.",   // G
	    "....",  // H
	    "..",    // I
	    ".---",  // J
	    "-.-",   // K
	    ".-..",  // L
	    "--",    // M
	    "-.",    // N
	    "---",   // O
	    ".--.",  // P
	    "--.-",  // Q
	    ".-.",   // R
	    "...",   // S
	    "-",     // T
	    "..-",   // U
	    "...-",  // V
	    ".--",   // W
	    "-..-",  // X
	    "-.--",  // Y
	    "--.."   // Z
	};
	
	public static void main(String[] args) {
		// Acquérir un texte à traduire en morse
		String message = Console.lireString("Message à traduire ? ");
		
		// Traduire le message en morse
		String codeMorse = texteEnMorse(message);
		
		// Afficher le code morse correspondant
		System.out.println(codeMorse);
	}
	
	public static String texteEnMorse(String texte) {
		// Remplacer plusieurs caractères blancs consécutifs par un espace
		texte = texte.replaceAll("\\s+", " ");
		
		//TODO: Parcourir le texte et traduire chaque lettre en morse
		
		return null;
	}
	
	public static String lettreEnMorse(char lettre) {
		//TODO: Si le caractère n'est pas une lettre alphabétique non accentuée ou un espace, retourner une chaîne vide
		
		//TODO: Sinon, retourner le code morse correspondant à la lettre spécifiée (/ pour un espace)
		
		return null;
	}
	
}
