package ua.nure.koshova.finalProject.view.filter;

import org.apache.log4j.Logger;
import ua.nure.koshova.finalProject.view.filter.locale.LocaleSessionProvider;
import ua.nure.koshova.finalProject.view.filter.locale.LocaleSessionProviderImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Filter for web app localization
 */
@WebFilter(
        filterName = "LocalizationFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "default-locale", value = "en"),
                @WebInitParam(name = "project-locales", value = "en,ru")
        }
)
public class LocaleFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(LocaleFilter.class);

    private Locale defaultLocale;
    private LocaleSessionProvider localeSessionProvider;
    private List<Locale> projectLocales = new ArrayList<>();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpSession session = httpRequest.getSession();

        Locale locale;
        if (localeSessionProvider.isLocaleSet(session)) {
            locale = localeSessionProvider.getLocale(session);
        } else {
            locale = getLocaleFromRequest(httpRequest);
            localeSessionProvider.setLocale(session, locale);
        }
        chain.doFilter(new LocaleHttpServletRequest(httpRequest, locale), resp);
    }

    public void init(FilterConfig config) throws ServletException {
        // get params from web.xml
        String defaultLocaleString = config.getInitParameter("default-locale");
        String projectLocalesLanguages = config.getInitParameter("project-locales");

        // init fields
        localeSessionProvider = new LocaleSessionProviderImpl();
        defaultLocale = Locale.forLanguageTag(defaultLocaleString);
        String[] languages = projectLocalesLanguages.split(",");
        for (String lang : languages) {
            projectLocales.add(Locale.forLanguageTag(lang));
        }

        config.getServletContext().setAttribute("projectLocales", projectLocales);
        LOGGER.info("All locales have been initialized");
    }

    /**
     * Get locale from request
     *
     * @param httpRequest
     *
     * @return {@link Locale} request locale
     */
    private Locale getLocaleFromRequest(HttpServletRequest httpRequest) {
        Locale locale = getLocaleParamFromRequest(httpRequest);

        if (isLocaleAvailable(locale)) {
            return locale;
        } else {
            return defaultLocale;
        }
    }

    /**
     * Get locale from request parameter
     *
     * @param httpRequest
     *
     * @return {@link Locale} or null value if there wasn't any locale parameter in request
     */
    private Locale getLocaleParamFromRequest(HttpServletRequest httpRequest) {
        String lang = httpRequest.getParameter("lang");

        if (lang != null) {
            return Locale.forLanguageTag(lang);
        } else {
            return null;
        }
    }

    /**
     * Check if locale is available for current project
     *
     * @param locale - locale for checking
     *
     * @return true if locale is available
     */
    private boolean isLocaleAvailable(Locale locale) {
        return projectLocales.contains(locale);
    }

    private class LocaleHttpServletRequest extends HttpServletRequestWrapper {
        private Locale locale;

        public LocaleHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        public LocaleHttpServletRequest(HttpServletRequest request, Locale locale) {
            super(request);
            this.locale = locale;
        }

        @Override
        public Locale getLocale() {
            return locale;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return Collections.enumeration(projectLocales);
        }
    }
}
