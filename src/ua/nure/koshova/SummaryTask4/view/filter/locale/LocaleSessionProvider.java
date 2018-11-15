package ua.nure.koshova.SummaryTask4.view.filter.locale;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Interface for locale editing
 */
public interface LocaleSessionProvider {

    /**
     * Get locale from the session by the 'locale' key
     *
     * @param session
     * @return {@link Locale}
     */
    Locale getLocale(HttpSession session);

    /**
     * Set new locale to the session by the 'locale' key
     *
     * @param session
     * @param locale
     */
    void setLocale(HttpSession session, Locale locale);

    /**
     * Checks if locale is already set into the session
     *
     * @param session
     * @return
     */
    boolean isLocaleSet(HttpSession session);
}
