package ua.nure.koshova.finalProject.view.filter.locale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Interface for locale editing
 */
public interface ILocale {

    /**
     * Get the locale from request
     *
     * @param request
     * @return {@link Locale}
     */
    Locale getLocale(HttpServletRequest request);

    /**
     * Set new locale to request
     *
     * @param request
     * @param locale
     */
    void setLocale(HttpServletRequest request, Locale locale);
}
