package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(OrderAnnotation.class)
class TableauChainesTest {

	// public static String[] ajouterElement(String[] t, String element)

	@Test
	@Order(1)
	void ajouterElement_TableauVide() {
		assertArrayEquals(new String[] { "Neque" }, TableauChaines.ajouterElement(new String[0], "Neque"));
	}

	@Test
	@Order(2)
	void ajouterElement_TableauLongueur1() {
		String[] t = { "Neque" };
		final String[] COPIE = t.clone();
		assertArrayEquals(new String[] { "Neque", "porro quisquam est" },
				TableauChaines.ajouterElement(t, "porro quisquam est"));
		assertArrayEquals(COPIE, t);
	}

	@Test
	@Order(3)
	void ajouterElement_TableauLongueur2() {
		String[] t = { "Neque", "porro quisquam est" };
		final String[] COPIE = t.clone();
		assertArrayEquals(new String[] { "Neque", "porro quisquam est", "qui dolorem" },
				TableauChaines.ajouterElement(t, "qui dolorem"));
		assertArrayEquals(COPIE, t);
	}

	@Test
	@Order(4)
	void ajouterElement_TableauLongueur5() {
		String[] t = { "Neque", "porro quisquam est", "", "ipsum", "quia dolor sit amet" };
		final String[] COPIE = t.clone();
		assertArrayEquals(
				new String[] { "Neque", "porro quisquam est", "", "ipsum", "quia dolor sit amet", "consectetur" },
				TableauChaines.ajouterElement(t, "consectetur"));
		assertArrayEquals(COPIE, t);
	}

	@Test
	@Order(5)
	void ajouterElement_GrandTableau() {
		final String NOUVEL_ELEMENT = "De finibus bonorum et malorum";
		String[] t = bigArray();
		final String[] COPIE = t.clone();
		String[] tAttendu = Arrays.copyOf(t, t.length + 1);
		tAttendu[t.length] = NOUVEL_ELEMENT;
		assertArrayEquals(tAttendu, TableauChaines.ajouterElement(t, NOUVEL_ELEMENT));
		assertArrayEquals(COPIE, t);
	}

	// public static String valeurAleatoire(String[] t)

	@Test
	@Order(6)
	void valeurAleatoire_TableauLongueur1() {
		String[] t = { "Neque" };
		final String[] COPIE = t.clone();
		assertEquals("Neque", TableauChaines.valeurAleatoire(t));
		assertArrayEquals(COPIE, t);
	}

	@Test
	@Order(7)
	void valeurAleatoire_TableauLongueur2() {
		String[] t = { "Neque", "porro quisquam est" };
		final String[] COPIE = t.clone();
		final int[] POSITIONS_ALEATOIRES = { 1, 0, 0, 1, 0, 1 };
		Aleatoire.effacerNombres();
		Aleatoire.specifierNombres(POSITIONS_ALEATOIRES);
		for (int posAleat : POSITIONS_ALEATOIRES) {
			assertEquals(t[posAleat], TableauChaines.valeurAleatoire(t));
			assertArrayEquals(COPIE, t);
		}
	}

	@Test
	@Order(8)
	void valeurAleatoire_TableauLongueur5() {
		String[] t = { "Neque", "porro quisquam est", "", "ipsum", "quia dolor sit amet" };
		final String[] COPIE = t.clone();
		final int[] POSITIONS_ALEATOIRES = { 3, 0, 1, 4, 0, 1, 3, 4, 2, 2 };
		Aleatoire.effacerNombres();
		Aleatoire.specifierNombres(POSITIONS_ALEATOIRES);
		for (int posAleat : POSITIONS_ALEATOIRES) {
			assertEquals(t[posAleat], TableauChaines.valeurAleatoire(t));
			assertArrayEquals(COPIE, t);
		}
	}

	// public static int commencePar(String[] t, String prefixe)

	@Test
	@Order(10)
	void commencePar_TableauVide() {
		assertEquals(-1, TableauChaines.commencePar(new String[0], "ipsum"));
	}

	@ParameterizedTest
	@Order(11)
	@MethodSource("stringArrayProvider")
	void commencePar_ChainePresenteUneFoisAvecCasseIdentique(String[] t) {
		final String[] COPIE = t.clone();
		for (int i = 0; i < t.length; i++) {
			assertEquals(i, TableauChaines.commencePar(t, new String(t[i])));
			assertArrayEquals(COPIE, t);
		}
	}

	@ParameterizedTest
	@Order(12)
	@MethodSource("stringArrayProvider")
	void commencePar_ChainePresenteUneFoisAvecCasseDifferente(String[] t) {
		final String[] COPIE = t.clone();
		for (int i = 0; i < t.length; i++) {
			assertEquals(i, TableauChaines.commencePar(t, changeStringCase(t[i])));
			assertArrayEquals(COPIE, t);
		}
	}

	@Test
	@Order(13)
	void commencePar_RechercheAvecPrefixe() {
		String[] t = { "Neque", "porro", "quisquam est", "ipsum", "quia dolor sit amet", "porro" };
		final String[] COPIE = t.clone();
		assertEquals(0, TableauChaines.commencePar(t, "N"));
		assertEquals(0, TableauChaines.commencePar(t, "Neq"));
		assertEquals(1, TableauChaines.commencePar(t, "porr"));
		assertEquals(2, TableauChaines.commencePar(t, "q"));
		assertEquals(2, TableauChaines.commencePar(t, "quis"));
		assertEquals(3, TableauChaines.commencePar(t, "i"));
		assertEquals(4, TableauChaines.commencePar(t, "quia"));
		assertArrayEquals(COPIE, t);
	}

	@ParameterizedTest
	@Order(14)
	@MethodSource("stringArrayProvider")
	void commencePar_ChaineNonPresente(String[] t) {
		final String[] COPIE = t.clone();
		assertEquals(-1, TableauChaines.commencePar(t, "finibus"));
		assertArrayEquals(COPIE, t);
	}

	@Test
	@Order(15)
	void commencePar_ReferenceNullPresente() {
		String[] t = { "Neque", null, "porro", "quisquam est", null, null, "ipsum", "quia dolor sit amet" };
		final String[] COPIE = t.clone();
		for (int i = 2; i < t.length; i++) {
			if (t[i] != null) {
				assertEquals(i, TableauChaines.commencePar(t, changeStringCase(t[i])));
				assertArrayEquals(COPIE, t);
			}
		}
	}

	// public static int compter(String[] t, String chaine)

	@Test
	@Order(16)
	void compter_TableauVide() {
		assertEquals(0, TableauChaines.compter(new String[0], "ipsum"));
	}

	@ParameterizedTest
	@Order(17)
	@MethodSource("stringArrayProvider")
	void compter_ChainePresenteUneFoisAvecCasseIdentique(String[] t) {
		final String[] COPIE = t.clone();
		for (int i = 0; i < t.length; i++) {
			assertEquals(1, TableauChaines.compter(t, new String(t[i])));
			assertArrayEquals(COPIE, t);
		}
	}

	@ParameterizedTest
	@Order(18)
	@MethodSource("stringArrayProvider")
	void compter_ChainePresenteUneFoisAvecCasseDifferente(String[] t) {
		final String[] COPIE = t.clone();
		for (int i = 0; i < t.length; i++) {
			assertEquals(0, TableauChaines.compter(t, changeStringCase(t[i])));
			assertArrayEquals(COPIE, t);
		}
	}

	@ParameterizedTest
	@Order(19)
	@MethodSource("stringArrayProvider")
	void compter_ChaineNonPresente(String[] t) {
		final String[] COPIE = t.clone();
		assertEquals(0, TableauChaines.compter(t, "finibus"));
		assertArrayEquals(COPIE, t);
	}

	@Test
	@Order(20)
	void compter_ChainePresentePlusieursFois() {
		String[] t = { "Neque", "", "ipsum", "porro", "quisquam est", "Neque", "Neque", "ipsum", "ipsum", "quisquam est", "ipsum" };
		final String[] COPIE = t.clone();
		final String[] CHAINES_RECHERCHEES = { "Neque", "", "ipsum", "porro", "quisquam est" };
		final int[] OCCURRENCES_ATTENDUES = { 3, 1, 4, 1, 2 };
		for (int i = 0; i < CHAINES_RECHERCHEES.length; i++) {
			assertEquals(OCCURRENCES_ATTENDUES[i], TableauChaines.compter(t, new String(CHAINES_RECHERCHEES[i])), "Counting " + t[i]);
			assertArrayEquals(COPIE, t);
		}
	}

	@Test
	@Order(21)
	void compter_ReferenceNullPresente() {
		String[] t = { "Neque", "", null, "porro", "quisquam est", null, null, "ipsum", "ipsum", "quisquam est", "ipsum" };
		final String[] COPIE = t.clone();
		final String[] CHAINES_RECHERCHEES = { "Neque", "", "ipsum", "porro", "quisquam est" };
		final int[] OCCURRENCES_ATTENDUES = { 1, 1, 3, 1, 2 };
		for (int i = 0; i < CHAINES_RECHERCHEES.length; i++) {
			assertEquals(OCCURRENCES_ATTENDUES[i], TableauChaines.compter(t, new String(CHAINES_RECHERCHEES[i])), "Counting " + t[i]);
			assertArrayEquals(COPIE, t);
		}
	}

	// Utilitaires

	static Stream<Arguments> stringArrayProvider() {
		return Stream.of(Arguments.of((Object) new String[] { "Neque" }),
				Arguments.of((Object) new String[] { "Neque", "porro quisquam est" }),
				Arguments.of((Object) new String[] { "Neque", "porro quisquam est", "ipsum", "quia dolor sit amet" }),
				Arguments.of((Object) bigArray(23)));
	}

	static String[] LOT_OF_WORDS = "Non eram nescius Brute cum quae summis ingeniis exquisitaque doctrina philosophi Graeco sermone tractavissent ea Latinis litteris mandaremus fore ut hic noster labor in varias reprehensiones incurreret nam quibusdam et iis quidem non admodum indoctis totum hoc displicet philosophari quidam autem non tam id reprehendunt si remissius agatur sed tantum studium tamque multam operam ponendam in eo non arbitrantur erunt etiam et ii quidem eruditi Graecis litteris contemnentes Latinas qui se dicant in Graecis legendis operam malle consumere postremo aliquos futuros suspicor qui me ad alias litteras vocent genus hoc scribendi etsi sit elegans personae tamen et dignitatis esse negent Contra quos omnis dicendum breviter existimo Quamquam philosophiae quidem vituperatoribus satis responsum est eo libro quo a nobis philosophia defensa et collaudata est cum esset accusata et vituperata ab Hortensio qui liber cum et tibi probatus videretur et iis quos ego posse iudicare arbitrarer plura suscepi veritus ne movere hominum studia viderer retinere non posse Qui autem si maxime hoc placeat moderatius tamen id volunt fieri difficilem quandam temperantiam postulant in eo quod semel admissum coerceri reprimique non potest ut propemodum iustioribus utamur illis qui omnino avocent a philosophia quam his qui rebus infinitis modum constituant in reque eo meliore quo maior sit mediocritatem desiderent Sive enim ad sapientiam perveniri potest non paranda nobis solum ea sed fruenda etiam sapientia est sive hoc difficile est tamen nec modus est ullus investigandi veri nisi inveneris et quaerendi defatigatio turpis est cum id quod quaeritur sit pulcherrimum etenim si delectamur cum scribimus quis est tam invidus qui ab eo nos abducat sin laboramus quis est qui alienae modum statuat industriae nam ut Terentianus Chremes non inhumanus qui novum vicinum non vult fodere aut arare aut aliquid ferre denique non enim illum ab industria sed ab inliberali labore deterret sic isti curiosi quos offendit noster minime nobis iniucundus labor"
			.split(" ");

	static String[] bigArray() {
		return bigArray(LOT_OF_WORDS.length);
	}

	static String[] bigArray(int length) {
		return Arrays.copyOf(LOT_OF_WORDS, Math.min(LOT_OF_WORDS.length, length));
	}

	static String changeStringCase(String string) {
		return (!string.isEmpty() && Character.isUpperCase(string.charAt(0))) ? string.toLowerCase()
				: string.toUpperCase();
	}

	static String[][] deepCopy(String[][] t) {
		final int LENGTH = t.length;
		String[][] copy = new String[LENGTH][];
		for (int i = 0; i < LENGTH; i++) {
			if (t[i] != null) {
				copy[i] = Arrays.copyOf(t[i], t[i].length);
			}
		}
		return copy;
	}

}