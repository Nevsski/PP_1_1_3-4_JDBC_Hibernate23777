package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Util {
    private static final String PASSWORD = "mypassword";
    private static final String USERNAME = "myuser";
    private static final String URL = "jdbc:mysql://localhost:3308/mydatabase";


    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        configuration.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        configuration.setProperty(Environment.URL, URL);
        configuration.setProperty(Environment.USER, USERNAME);
        configuration.setProperty(Environment.PASS, PASSWORD);
        configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");


        configuration.setProperty(Environment.SHOW_SQL, "false");
        configuration.setProperty(Environment.FORMAT_SQL, "true");
        return configuration;
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration();

        configuration.addAnnotatedClass(User.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}


