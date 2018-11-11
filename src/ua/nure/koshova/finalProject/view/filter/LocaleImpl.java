package ua.nure.koshova.finalProject.view.filter;

import ua.nure.koshova.finalProject.view.filter.locale.ILocale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LocaleImpl implements ILocale {

    @Override
    public Locale getLocale(HttpServletRequest request) {
        String locale = (String) request.getSession().getAttribute("locale");
        if (locale != null){
            return Locale.forLanguageTag(locale);
        }
        return null;
    }

    @Override
    public void setLocale(HttpServletRequest request, Locale locale) {
        request.getSession().setAttribute("locale", locale.getLanguage());
    }
}
