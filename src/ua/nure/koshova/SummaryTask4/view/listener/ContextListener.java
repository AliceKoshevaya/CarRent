package ua.nure.koshova.SummaryTask4.view.listener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ua.nure.koshova.SummaryTask4.db.dao.util.DatabaseUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getLogger(ContextListener.class);

    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        initLog4J();
        initDataSource();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    /**
     * Log4J initialization
     */
    private void initLog4J() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            PropertyConfigurator.configure(classLoader.getResourceAsStream("log4j.properties"));
            LOGGER.info("Log4j has been initialized");
        } catch (Exception ex) {
            throw new RuntimeException("Can't configure Log4j", ex);
        }
    }

    private void initDataSource(){
        DatabaseUtils.initDataSource();
    }
}
