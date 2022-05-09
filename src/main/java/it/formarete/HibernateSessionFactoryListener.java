package it.formarete;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class HibernateSessionFactoryListener implements ServletContextListener {

    public final Logger logger = Logger.getLogger(HibernateSessionFactoryListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        StandardServiceRegistryBuilder standardRegistryBuilder
                = new StandardServiceRegistryBuilder();

        // configure the registry from a resource lookup for a cfg.xml config file
        StandardServiceRegistry standardRegistry = standardRegistryBuilder
                .configure("hibernate.cfg.xml")
                .build();

        //        Building the Metadata
        //        The org.hibernate.boot.Metadata object contains the parsed representations
        //        of an application’s domain model and its mapping to a database
        MetadataSources sources = new MetadataSources(standardRegistry)
                .addAnnotatedClass(it.formarete.Employee.class);

        Metadata metadata = sources.getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
                .build();
        logger.info("SessionFactory created successfully");
        servletContextEvent.getServletContext().setAttribute("SessionFactory",
                sessionFactory);
        logger.info("Hibernate SessionFactory Configured successfully");

    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("SessionFactory");
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            logger.info("Closing sessionFactory");
            sessionFactory.close();
        }
        logger.info("Released Hibernate sessionFactory resource");
    }
}
