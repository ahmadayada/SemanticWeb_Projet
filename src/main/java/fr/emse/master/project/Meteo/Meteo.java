package fr.emse.master.project.Meteo;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Meteo {
    public static final int METEO_CODE_SAINT_ETIENNE = 7475;
    public int code; // equal to code2 of mean the city code
    public int jour; // in numbers of day
    public int mois; // also in number of month
    public int annee; // set year

    public Meteo() {

        this.code = METEO_CODE_SAINT_ETIENNE;
        // Get an instance of LocalTime
        // from date
        Date date = new Date();
        // Choose time zone in which you want to interpret your Date
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        this.annee = cal.get(Calendar.YEAR);
        this.mois = cal.get(Calendar.MONTH) + 1;
        this.jour = cal.get(Calendar.DAY_OF_MONTH);

    }

    public Meteo(int day, int month, int year) {
        Date date = new Date();
        // Choose time zone in which you want to interpret your Date
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
        cal.setTime(date);
        this.code = METEO_CODE_SAINT_ETIENNE;
        if (year >= 1975 && year <= Calendar.YEAR) {
            this.annee = year;
        } else {
            annee = Calendar.YEAR;
        }
        if (month >= 1 && month <= 12) {
            this.mois = month;
        } else {
            mois = Calendar.MONTH + 1;
        }
        if ((mois == 1 || mois == 3 | mois == 5 | mois == 7 | mois == 8 | mois == 10 | mois == 12) && day <= 31
                && day >= 1) {
            this.jour = day;
        } else if ((mois == 2 || mois == 4 | mois == 6 | mois == 9 | mois == 11) && day <= 30
                && day >= 1) {
            this.jour = day;
        } else {
            day = Calendar.DAY_OF_MONTH;
        }
    }

    public Meteo(int code, int day, int month, int year) {
        this.code = code;
        new Meteo(day, month, year);
    }

    public String createQuery() {
        String query = "?code2=" + code + "&jour2=" + jour + "&mois2=" + (mois - 1) + "&annee2=" + annee;
        return query;

    }

}
