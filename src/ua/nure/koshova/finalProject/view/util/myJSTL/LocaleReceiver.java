package ua.nure.koshova.finalProject.view.util.myJSTL;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocaleReceiver extends TagSupport {

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            Object localesObject = servletContext.getAttribute("projectLocales");
            List<Locale> localeList = (List<Locale>) localesObject;
            for (Locale locale : localeList) {
                String otherLang = "<option value=\"http://localhost:8080/?lang=" + locale.getLanguage() + "\">" +
                        locale.getLanguage().toUpperCase() + "</option>";
                JspWriter out = pageContext.getOut();
                out.print(otherLang);
            }
        } catch (IOException e) {
            throw new JspException("Can't execute custom tag", e);
        }
        return SKIP_BODY;
    }
}
