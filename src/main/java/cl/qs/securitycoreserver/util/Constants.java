package cl.qs.securitycoreserver.util;


public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String APPLICATION_NOT_FOUND = "No encontramos Applicationes registradas en la DB";
    public static final String APPLICATION_NOT_FOUND_BY_ID = "No encontramos Application con este ID en la DB";
    public static final String APPLICATION_FOUND = "Ya existe una aplication con este NAME en la DB";
    public static final String LEVEL_NOT_FOUND = "No encontramos Level con este ID en la DB";
    public static final String USER_NOT_FOUND = "No encontramos User con este ID en la DB";
    public static final String USER_FOUND = "Ya existe un User con este DNI en la DB";
    public static final String ERROR = "ERROR";
}
