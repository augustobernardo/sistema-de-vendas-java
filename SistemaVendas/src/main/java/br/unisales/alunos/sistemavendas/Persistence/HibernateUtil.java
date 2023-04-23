package br.unisales.alunos.sistemavendas.Persistence;

import br.unisales.alunos.sistemavendas.Models.Venda;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author rfdouro
 * 
 * classe de utilidades para gerar Session Hibernate
 * que são gerenciadores de entidades (EntityManager)
 */
public class HibernateUtil {

 private static SessionFactory sessionFactory;
 private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
 private static String urlDB = "//////////////////LINK_BANCO_DADOS/////////////////////";

 static {

  sessionFactory = null;

  Map<String, Object> settings = new HashMap<>();
  settings.put("hibernate.connection.driver_class", "org.mariadb.jdbc.Driver");
  settings.put("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
  settings.put("hibernate.connection.url", urlDB);
  settings.put("hibernate.connection.username", "/////////////////USER_BANCO///////////////////");
  settings.put("hibernate.connection.password", "/////////////////SENHA_BANCO///////////////////");
  settings.put("hibernate.current_session_context_class", "thread");
  settings.put("hibernate.show_sql", "false");
  settings.put("hibernate.format_sql", "true");
  settings.put("hibernate.hbm2ddl.auto", "validate");

  Properties properties = new Properties();
  properties.putAll(settings);

  try {
   Configuration configuration = new Configuration()
           .addAnnotatedClass(Venda.class);
   configuration.setProperties(properties);
   sessionFactory = configuration.buildSessionFactory();

  } catch (Throwable ex) {
   throw new ExceptionInInitializerError(ex);
  }
 }
 public static Session getInstance() {
  Session session = (Session) threadLocal.get();
  session = sessionFactory.openSession();
  threadLocal.set(session);
  return session;
 }
}
