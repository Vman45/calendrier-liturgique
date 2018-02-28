package fr.plaisance.calit;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;

public class CalendrierLiturgiqueTest {

	@Test
	public void paques2019() {
		int annee = 2019;
		DateLiturgique paques = CalendrierLiturgique.paques(annee);
		assertThat(paques.date).isEqualTo(LocalDate.of(annee, Month.APRIL, 21));
	}

	@Test
	public void paques2030() {
		int annee = 2030;
		DateLiturgique paques = CalendrierLiturgique.paques(annee);
		assertThat(paques.date).isEqualTo(LocalDate.of(annee, Month.APRIL, 21));
	}

	@Test
	public void paques2100() {
		int annee = 2100;
		DateLiturgique paques = CalendrierLiturgique.paques(annee);
		assertThat(paques.date).isEqualTo(LocalDate.of(annee, Month.MARCH, 28));
	}

	@Test
	public void paques2001() {
		int annee = 2001;
		DateLiturgique paques = CalendrierLiturgique.paques(annee);
		assertThat(paques.date).isEqualTo(LocalDate.of(annee, Month.APRIL, 15));
	}

	@Test
	public void paques2055() {
		int annee = 2055;
		DateLiturgique paques = CalendrierLiturgique.paques(annee);
		assertThat(paques.date).isEqualTo(LocalDate.of(annee, Month.APRIL, 18));
	}

	@Test
	public void paques2076() {
		int annee = 2076;
		DateLiturgique paques = CalendrierLiturgique.paques(annee);
		assertThat(paques.date).isEqualTo(LocalDate.of(annee, Month.APRIL, 19));
	}

	@Test
	public void mercrediDesCendres() {
		int annee = 2018;
		DateLiturgique mercrediDesCendres = CalendrierLiturgique.cendres(annee);
		assertThat(mercrediDesCendres.date).isEqualTo(LocalDate.of(annee, Month.FEBRUARY, 14));
	}

	@Test
	public void invocabit() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.premierDimancheDeCareme(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.FEBRUARY, 18));
		assertThat(date.libelle).containsIgnoringCase("invocabit");
	}

	@Test
	public void reminiscere() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.deuxiemeDimancheDeCareme(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.FEBRUARY, 25));
		assertThat(date.libelle).containsIgnoringCase("reminiscere");
	}

	@Test
	public void oculi() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.troisiemeDimancheDeCareme(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.MARCH, 4));
		assertThat(date.libelle).containsIgnoringCase("oculi");
	}

	@Test
	public void laetare() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.quatriemeDimancheDeCareme(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.MARCH, 11));
		assertThat(date.libelle).containsIgnoringCase("l�tare");
	}

	@Test
	public void judica() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.cinquiemeDimancheDeCareme(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.MARCH, 18));
		assertThat(date.libelle).containsIgnoringCase("judica");
	}
	
	@Test
	public void dimancheDesRameaux() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.rameaux(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.MARCH, 25));
	}
	
	@Test
	public void jeudiSaint() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.jeudiSaint(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.MARCH, 29));
	}
	
	@Test
	public void vendrediSaint() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.vendrediSaint(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.MARCH, 30));
	}
	
	@Test
	public void samediSaint() {
		int annee = 2018;
		DateLiturgique date = CalendrierLiturgique.samediSaint(annee);
		assertThat(date.date).isEqualTo(LocalDate.of(annee, Month.MARCH, 31));
	}
}