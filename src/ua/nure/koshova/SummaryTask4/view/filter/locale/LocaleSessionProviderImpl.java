package ua.nure.koshova.SummaryTask4.view.filter.locale;

import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LocaleSessionProviderImpl implements LocaleSessionProvider {

    public static final String LOCALE_SESSION_KEY = "locale";

    @Override
    public Locale getLocale(HttpSession session) {
        String locale = (String) session.getAttribute(LOCALE_SESSION_KEY);
        if (locale != null){
            return Locale.forLanguageTag(locale);
        } else {
            return null;
        }
    }

    @Override
    public void setLocale(HttpSession session, Locale locale) {
        session.setAttribute(LOCALE_SESSION_KEY, locale.getLanguage());
    }

    @Override
    public boolean isLocaleSet(HttpSession session) {
        return session.getAttribute(LOCALE_SESSION_KEY) != null;
    }
}
