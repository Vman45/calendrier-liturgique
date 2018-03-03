package fr.plaisance.calit;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static fr.plaisance.calit.CalendrierLiturgique.*;

public class CalendarBuilderTest {

	@Test
	public void calendar() throws IOException {
		int annee = 2018;
		List<DateLiturgique> solennites = Arrays.asList(
			premierDimancheAvent(annee),
			immaculeeConception(annee),
			deuxiemeDimancheAvent(annee),
			troisiemeDimancheAvent(annee),
			quatriemeDimancheAvent(annee),
			noel(annee),
			sainteMarieMereDeDieu(annee),
			epiphanie(annee),
			premierDimancheCareme(annee),
			deuxiemeDimancheCareme(annee),
			troisiemeDimancheCareme(annee),
			quatriemeDimancheCareme(annee),
			cinquiemeDimancheCareme(annee),
			saintJoseph(annee),
			jeudiSaint(annee),
			vendrediSaint(annee),
			samediSaint(annee),
			paques(annee),
			annonciation(annee),
			ascension(annee),
			pentecote(annee),
			sainteTrinite(annee),
			feteDieu(annee),
			sacreCoeur(annee),

			christRoi(annee)
		);
		CalendarBuilder.writeCalendar("C:/Users/Romain/Desktop/romain.ics", solennites);
	}
}
