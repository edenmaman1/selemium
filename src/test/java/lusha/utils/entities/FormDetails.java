package lusha.utils.entities;

import java.time.Month;
import java.time.Year;

import static lusha.utils.CommonOps.generateRandomString;
import static lusha.utils.CommonOps.getCurrentTimeMillis;

public class FormDetails {

    public static String name;
    public static String country;
    public static String city;
    public static String creditCardNumber;
    public static String month;
    public static String year;

    public FormDetails() {
        this.name =generateRandomString(10);
        this.country = generateRandomString(10);
        this.city = generateRandomString(10);
        this.creditCardNumber = String.valueOf(getCurrentTimeMillis());
        this.month = String.valueOf(Month.AUGUST);//TODO make random function
        this.year= String.valueOf(Year.now());
    }
}
