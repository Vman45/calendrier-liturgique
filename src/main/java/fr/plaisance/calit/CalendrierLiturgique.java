package fr.plaisance.calit;

import fr.plaisance.calit.DateLiturgique.Couleur;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Properties;

public class CalendrierLiturgique {

	private static final Properties resourceBundle = readProperties();

	private static Properties readProperties() {
		String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String propertiesPath = rootPath + "app.properties";
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(new FileInputStream(propertiesPath), StandardCharsets.UTF_8));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	private static boolean isBetween(LocalDate date, LocalDate start, LocalDate end) {
		return !start.isAfter(date) && !end.isBefore(date);
	}

	private static boolean durantTriduumPascal(LocalDate date) {
		LocalDate paques = datePaques(date.getYear());
		return isBetween(date, paques.minusDays(2), paques);
	}

	private static boolean durantOctaveDePaques(LocalDate date) {
		LocalDate paques = datePaques(date.getYear());
		return isBetween(date, paques, paques.plusWeeks(1));
	}

	private static boolean durantSemaineSainte(LocalDate date) {
		LocalDate paques = datePaques(date.getYear());
		return isBetween(date, paques.minusWeeks(1), paques);
	}

	public static DateLiturgique paques(int annee) {
		LocalDate date = datePaques(annee);
		return DateLiturgique.of(date, resourceBundle.getProperty("paques"), Couleur.BLANC);
	}

	private static LocalDate datePaques(int annee) {
		int joursDeMars = algorithmeDeGauss(annee);
		return LocalDate.of(annee, Month.MARCH, 1).plusDays(joursDeMars - 1);
	}

	public static DateLiturgique cendres(int annee) {
		LocalDate date = datePaques(annee).minusWeeks(6).minusDays(4);
		return DateLiturgique.of(date, resourceBundle.getProperty("cendres"), Couleur.VIOLET);
	}

	public static DateLiturgique premierDimancheCareme(int annee) {
		LocalDate date = datePaques(annee).minusWeeks(6);
		return DateLiturgique.of(date, resourceBundle.getProperty("careme.1"), Couleur.VIOLET);
	}

	public static DateLiturgique deuxiemeDimancheCareme(int annee) {
		LocalDate date = datePaques(annee).minusWeeks(5);
		return DateLiturgique.of(date, resourceBundle.getProperty("careme.2"), Couleur.VIOLET);
	}

	public static DateLiturgique troisiemeDimancheCareme(int annee) {
		LocalDate date = datePaques(annee).minusWeeks(4);
		return DateLiturgique.of(date, resourceBundle.getProperty("careme.3"), Couleur.VIOLET);
	}

	public static DateLiturgique quatriemeDimancheCareme(int annee) {
		LocalDate date = datePaques(annee).minusWeeks(3);
		return DateLiturgique.of(date, resourceBundle.getProperty("careme.4"), Couleur.ROSE);
	}

	public static DateLiturgique cinquiemeDimancheCareme(int annee) {
		LocalDate date = datePaques(annee).minusWeeks(2);
		return DateLiturgique.of(date, resourceBundle.getProperty("careme.5"), Couleur.VIOLET);
	}

	public static DateLiturgique rameaux(int annee) {
		LocalDate date = datePaques(annee).minusWeeks(1);
		return DateLiturgique.of(date, resourceBundle.getProperty("rameaux"), Couleur.ROUGE);
	}


	public static DateLiturgique jeudiSaint(int annee) {
		LocalDate date = datePaques(annee).minusDays(3);
		return DateLiturgique.of(date, resourceBundle.getProperty("jeudi.saint"), Couleur.BLANC);
	}


	public static DateLiturgique vendrediSaint(int annee) {
		LocalDate date = datePaques(annee).minusDays(2);
		return DateLiturgique.of(date, resourceBundle.getProperty("vendredi.saint"), Couleur.ROUGE);
	}


	public static DateLiturgique samediSaint(int annee) {
		LocalDate date = datePaques(annee).minusDays(1);
		return DateLiturgique.of(date, resourceBundle.getProperty("samedi.saint"), Couleur.VIOLET);
	}

	public static DateLiturgique divineMisericorde(int annee) {
		LocalDate date = datePaques(annee).plusWeeks(1);
		return DateLiturgique.of(date, resourceBundle.getProperty("divine.misericorde"), Couleur.BLANC);
	}

	public static DateLiturgique ascension(int annee) {
		LocalDate date = datePaques(annee).plusDays(39);
		return DateLiturgique.of(date, resourceBundle.getProperty("ascension"), Couleur.BLANC);
	}

	public static DateLiturgique pentecote(int annee) {
		LocalDate date = datePaques(annee).plusWeeks(7);
		return DateLiturgique.of(date, resourceBundle.getProperty("pentecote"), Couleur.ROUGE);
	}

	public static DateLiturgique sainteTrinite(int annee) {
		LocalDate date = datePaques(annee).plusWeeks(8);
		return DateLiturgique.of(date, resourceBundle.getProperty("sainte.trinite"), Couleur.BLANC);
	}

	public static DateLiturgique feteDieu(int annee) {
		LocalDate date = datePaques(annee).plusWeeks(9);
		return DateLiturgique.of(date, resourceBundle.getProperty("fete.dieu"), Couleur.BLANC);
	}

	public static DateLiturgique sacreCoeur(int annee) {
		LocalDate date = datePaques(annee).plusWeeks(9).plusDays(5);
		return DateLiturgique.of(date, resourceBundle.getProperty("sacre.coeur"), Couleur.BLANC);
	}

//	public static DateLiturgique christRoi(int annee) {
//		LocalDate toussaint = toussaint(annee).date;
//		LocalDate date = toussaint.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
//		return DateLiturgique.of(date, "Christ Roi", Couleur.BLANC);
//	}

	public static DateLiturgique christRoi(int annee) {
		LocalDate date = dateQuatriemeDimancheAvent(annee).minusWeeks(4);
		return DateLiturgique.of(date, resourceBundle.getProperty("christ.roi"), Couleur.BLANC);
	}

	private static LocalDate dateQuatriemeDimancheAvent(int annee) {
		LocalDate noel = noel(annee).date;
		return noel.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
	}

	public static DateLiturgique premierDimancheAvent(int annee) {
		LocalDate date = dateQuatriemeDimancheAvent(annee).minusWeeks(3);
		return DateLiturgique.of(date, resourceBundle.getProperty("avent.1"), Couleur.VIOLET);
	}

	public static DateLiturgique deuxiemeDimancheAvent(int annee) {
		LocalDate date = dateQuatriemeDimancheAvent(annee).minusWeeks(2);
		return DateLiturgique.of(date, resourceBundle.getProperty("avent.2"), Couleur.VIOLET);
	}

	public static DateLiturgique troisiemeDimancheAvent(int annee) {
		LocalDate date = dateQuatriemeDimancheAvent(annee).minusWeeks(1);
		return DateLiturgique.of(date, resourceBundle.getProperty("avent.3"), Couleur.ROSE);
	}

	public static DateLiturgique quatriemeDimancheAvent(int annee) {
		LocalDate date = dateQuatriemeDimancheAvent(annee);
		return DateLiturgique.of(date, resourceBundle.getProperty("avent.4"), Couleur.VIOLET);
	}

	public static DateLiturgique immaculeeConception(int annee) {
		LocalDate date = LocalDate.of(annee, Month.DECEMBER, 8);
		if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			date = date.plusDays(1);
		}
		return DateLiturgique.of(date, resourceBundle.getProperty("immaculee.conception"), Couleur.BLANC);
	}

	public static DateLiturgique saintJoseph(int annee) {
		LocalDate date = LocalDate.of(annee, Month.MARCH, 19);
		if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			date = date.plusDays(1);
		}
		return DateLiturgique.of(date, resourceBundle.getProperty("saint.joseph"), Couleur.BLANC);
	}

	public static DateLiturgique annonciation(int annee) {
		LocalDate date = LocalDate.of(annee, Month.MARCH, 25);
		LocalDate paques = datePaques(annee);
		if(!paques.minusWeeks(1).isAfter(date) && !paques.isBefore(date)) {
			date = paques.plusWeeks(1).plusDays(1);
		}
		else if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			date = date.plusDays(1);
		}
		return DateLiturgique.of(date, resourceBundle.getProperty("annonciation"), Couleur.BLANC);
	}


	public static DateLiturgique epiphanie(int annee) {
		LocalDate date = LocalDate.of(annee, Month.JANUARY, 1).with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		return DateLiturgique.of(date, resourceBundle.getProperty("epiphanie"), Couleur.BLANC);
	}


    public static DateLiturgique nativiteStJeanBaptiste(int annee) {
        MonthDay date = MonthDay.of(Month.JUNE, 24);
        return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("nativite.de.saint.jean.baptiste"), Couleur.BLANC);
    }


	public static DateLiturgique sainteMarieMereDeDieu(int annee) {
		MonthDay date = MonthDay.of(Month.JANUARY, 1);
		return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("sainte.marie"), Couleur.BLANC);
	}

	public static DateLiturgique assomption(int annee) {
		MonthDay date = MonthDay.of(Month.AUGUST, 15);
		return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("assomption"), Couleur.BLANC);
	}

	public static DateLiturgique toussaint(int annee) {
		MonthDay date = MonthDay.of(Month.NOVEMBER, 1);
		return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("toussaint"), Couleur.BLANC);

	}

	public static DateLiturgique jourDesMorts(int annee) {
		MonthDay date = MonthDay.of(Month.NOVEMBER, 2);
		return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("jour.des.morts"), Couleur.VIOLET);
	}

	public static DateLiturgique noel(int annee) {
		MonthDay date = MonthDay.of(Month.DECEMBER, 25);
		return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("noel"), Couleur.BLANC);
	}

    public static DateLiturgique saintsPierreEtPaul(int annee) {
        MonthDay date = MonthDay.of(Month.JUNE, 29);
        return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("pierre.paul"), Couleur.ROUGE);
    }

    // Fêtes

	// Fêtes mobiles
	public static DateLiturgique sainteFamille(int annee) {
		LocalDate noel = noel(annee).date;
		LocalDate dernierDimanche = LocalDate.of(annee, Month.DECEMBER, 1).with(TemporalAdjusters.lastInMonth(DayOfWeek.SUNDAY));
		LocalDate date = noel.equals(dernierDimanche) ? MonthDay.of(Month.DECEMBER, 30).atYear(annee) : dernierDimanche;
		return DateLiturgique.of(date, resourceBundle.getProperty("sainte.famille"), Couleur.BLANC);
	}

	public static DateLiturgique baptemeDuSeigneur(int annee) {
		LocalDate date = epiphanie(annee).date.plusDays(1);
		return DateLiturgique.of(date, resourceBundle.getProperty("bapteme.du.seigneur"), Couleur.BLANC);
	}

	// Fêtes fixes
	// du Seigneur
	public static DateLiturgique presentationAuTemple(int annee) {
		MonthDay date = MonthDay.of(Month.FEBRUARY, 2);
		return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("presentation.au.temple"), Couleur.BLANC);
	}

	public static DateLiturgique transfiguration(int annee) {
		MonthDay date = MonthDay.of(Month.AUGUST, 6);
		return DateLiturgique.fixe(date, annee, resourceBundle.getProperty("transfiguration"), Couleur.BLANC);
	}

	// de la Vierge Marie
	public static DateLiturgique visitation(int annee) {
		MonthDay date = MonthDay.of(Month.MAY, 31);
		DateLiturgique visitation = DateLiturgique.fixe(date, annee, resourceBundle.getProperty("visitation"), Couleur.BLANC);
		if(visitation.date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return null;
		}
		return visitation;
	}

	public static DateLiturgique nativiteViergeMarie(int annee) {
		MonthDay date = MonthDay.of(Month.SEPTEMBER, 8);
		DateLiturgique nativiteViergeMarie = DateLiturgique.fixe(date, annee, resourceBundle.getProperty("nativite.marie"), Couleur.BLANC);
		if(nativiteViergeMarie.date.getDayOfWeek() == DayOfWeek.SUNDAY) {
			return null;
		}
		return nativiteViergeMarie;
	}

	private static int algorithmeDeGauss(int annee) {
		final int a = annee % 19;
		final int b = annee % 4;
		final int c = annee % 7;
		final int k = annee / 100;
		final int p = (13 + 8 * k) / 25;
		final int q = k / 4;
		final int M = (15 - p + k - q) % 30;
		final int N = (4 + k - q) % 7;
		final int d = (19 * a + M) % 30;
		final int e = (2 * b + 4 * c + 6 * d + N) % 7;

		if(d == 29 && e == 6) {
			return 19 + 31;
		}
		if(d == 28 && e == 6 && (11 * M + 11) / 30 < 19) {
			return 18 + 31;
		}
		return 22 + d + e;
	}

}
