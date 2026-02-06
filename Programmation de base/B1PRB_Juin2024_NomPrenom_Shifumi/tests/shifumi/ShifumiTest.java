package shifumi;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import fake.FakePrintStream;
import fake.FakePrintStreamRule;
import io.Console;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@ExtendWith(FakePrintStreamRule.class)
@TestMethodOrder(OrderAnnotation.class)
class ShifumiTest {

	@BeforeAll
	static void resetBeforeAllTests() {
		Console.nettoyerSaisies();
		FakePrintStream.clearOutput();
	}

	@AfterEach
	void resetAfterEachTest() {
		Console.nettoyerSaisies();
		FakePrintStream.clearOutput();
	}

	// public static String selectionnerChaine(String[] chainesAdmises)

	@Test
	@Order(1)
	void selectionnerChaine_QuestionValide() throws Exception {
		final String[] CHAINES_ADMISES = { "Pierre", "Feuille", "Ciseaux", "Galaxie", "Acarien", "Barack Obama" };
		final String[] COPIE_CHAINES_ADMISES = CHAINES_ADMISES.clone();
		Console.simulerSaisies("Pierre");
		assertTimeoutPreemptively(Duration.ofMillis(500), () -> Shifumi.selectionnerChaine(CHAINES_ADMISES));
		final String SORTIE_CONSOLE = FakePrintStream.getOutput();
		checkIfContainsInOrder(SORTIE_CONSOLE, "Pierre", ",", "Feuille", ",", "Ciseaux", ",", "Galaxie", ",", "Acarien", ",", "Barack Obama", "?", "Pierre");
		assertArrayEquals(COPIE_CHAINES_ADMISES, CHAINES_ADMISES);
	}

	@Test
	@Order(2)
	void selectionnerChaine_UneChaineAdmiseEtPremiereSaisieValide() throws Exception {
		final String[] CHAINES_ADMISES = { "Pierre" };
		final String[] COPIE_CHAINES_ADMISES = CHAINES_ADMISES.clone();
		Console.simulerSaisies("Pierre");
		assertTimeoutPreemptively(Duration.ofMillis(500), () -> assertEquals(new String("Pierre") , Shifumi.selectionnerChaine(CHAINES_ADMISES)));
		assertArrayEquals(COPIE_CHAINES_ADMISES, CHAINES_ADMISES);
	}

	@Test
	@Order(3)
	void selectionnerChaine_CinqsChainesAdmisesEtPremiereSaisieValide() throws Exception {
		final String[] CHAINES_ADMISES = { "Pierre", "Feuille", "Ciseaux", "Galaxie", "Acarien", "Barack Obama" };
		final String[] COPIE_CHAINES_ADMISES = CHAINES_ADMISES.clone();
		final String[] SAISIES = { "Galaxie", "Galax", "Ga", "G", "galaxie", "galax", "ga", "g" };
		for (String saisie : SAISIES) {
			Console.simulerSaisies(saisie);
			assertTimeoutPreemptively(Duration.ofMillis(500), () -> assertEquals(new String("Galaxie") , Shifumi.selectionnerChaine(CHAINES_ADMISES)));
			assertArrayEquals(COPIE_CHAINES_ADMISES, CHAINES_ADMISES);
		}
	}

	@Test
	@Order(4)
	void selectionnerChaine_CinqsChainesAdmisesEtPlusieursSaisiesInvalides() throws Exception {
		final String[] CHAINES_ADMISES = { "Pierre", "Feuille", "Ciseaux", "Galaxie", "Acarien", "Barack Obama" };
		final String[] COPIE_CHAINES_ADMISES = CHAINES_ADMISES.clone();
		Console.simulerSaisies("Spock", "Lézard", "Abc", "Galaxie");
		assertTimeoutPreemptively(Duration.ofMillis(500), () -> assertEquals(new String("Galaxie") , Shifumi.selectionnerChaine(CHAINES_ADMISES)));
		assertArrayEquals(COPIE_CHAINES_ADMISES, CHAINES_ADMISES);
	}

	// public static int comparer(String arme1, String arme2)

	@Test
	@Order(5)
	void comparer_TableauArmesValide() throws Exception {
		final String[] ARMES_ATTENDUES = new String[] { "Ciseaux", "Feuille", "Pierre", "Lézard", "Spock" };
		Field field = assertDoesNotThrow(() -> Shifumi.class.getDeclaredField("ARMES"),
				"Attribut nommé 'ARMES' non trouvé !");
		field.setAccessible(true);
		assertArrayEquals(ARMES_ATTENDUES, (String[]) field.get(null));
	}

	@Test
	@Order(6)
	void comparer_ArmesEgales() throws Exception {
		assertEquals(0, Shifumi.comparer("Ciseaux", "Ciseaux"));
		assertEquals(0, Shifumi.comparer("Feuille", "Feuille"));
		assertEquals(0, Shifumi.comparer("Pierre", "Pierre"));
		assertEquals(0, Shifumi.comparer("Lézard", "Lézard"));
		assertEquals(0, Shifumi.comparer("Spock", "Spock"));
	}

	@Test
	@Order(7)
	void comparer_ArmesConsecutives() throws Exception {
		assertEquals(1, Shifumi.comparer("Ciseaux", "Feuille"));
		assertEquals(-1, Shifumi.comparer("Feuille", "Ciseaux"));
		assertEquals(1, Shifumi.comparer("Feuille", "Pierre"));
		assertEquals(-1, Shifumi.comparer("Pierre", "Feuille"));
		assertEquals(1, Shifumi.comparer("Pierre", "Lézard"));
		assertEquals(-1, Shifumi.comparer("Lézard", "Pierre"));
		assertEquals(1, Shifumi.comparer("Lézard", "Spock"));
		assertEquals(-1, Shifumi.comparer("Spock", "Lézard"));
		assertEquals(1, Shifumi.comparer("Spock", "Ciseaux"));
		assertEquals(-1, Shifumi.comparer("Ciseaux", "Spock"));
	}

	@Test
	@Order(8)
	void comparer_ArmesAvecEcart3() throws Exception {
		assertEquals(-1, Shifumi.comparer("Ciseaux", "Pierre"));
		assertEquals(1, Shifumi.comparer("Pierre", "Ciseaux"));
		assertEquals(-1, Shifumi.comparer("Feuille", "Lézard"));
		assertEquals(1, Shifumi.comparer("Lézard", "Feuille"));
		assertEquals(-1, Shifumi.comparer("Pierre", "Spock"));
		assertEquals(1, Shifumi.comparer("Spock", "Pierre"));
		assertEquals(-1, Shifumi.comparer("Lézard", "Ciseaux"));
		assertEquals(1, Shifumi.comparer("Ciseaux", "Lézard"));
		assertEquals(-1, Shifumi.comparer("Spock", "Feuille"));
		assertEquals(1, Shifumi.comparer("Feuille", "Spock"));
	}
	
	// Utilitaires
	
	static void checkIfContainsInOrder(String text, String... strings) {
		text = text.toLowerCase();
		int index = 0;
		for (String string : strings) {
			index = text.indexOf(string.toLowerCase(), index);
			assertTrue(index >= 0, "The string \"" + string + "\" was not displayed correctly or in the wrong place!");
			index++;
		}
	}

}
