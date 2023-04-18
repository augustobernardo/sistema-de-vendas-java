/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unisales.alunos.sistemavendas.Persistence;

import br.unisales.alunos.sistemavendas.Models.Cliente;
import br.unisales.alunos.sistemavendas.Models.Produto;
import br.unisales.alunos.sistemavendas.Models.Venda;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtilH2 {

 //fábrica de sessões do hibernate
 private static SessionFactory sessionFactory;
 //sessão do hibernate usada na manipulação de dados
 //usa com ThreadLocal para poder habilitar concorrência
 //essa estratégia é comum em arquiteturas com ampla concorrência a recursos
 private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
 //url do banco de dados
 private static String urlDB = "jdbc:h2:file:./testdb;AUTO_SERVER=TRUE";

 //execução estática - é executada no momento em que o programa é carregado
 static {

  sessionFactory = null;

  //propriedades de defininção para o JPA
  Map<String, Object> settings = new HashMap<>();
  //drive do banco
  settings.put("hibernate.connection.driver_class", "org.h2.Driver");
  //dialeto do banco
  settings.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
  //url do banco
  settings.put("hibernate.connection.url", urlDB);
  //usuário do banco
  settings.put("hibernate.connection.username", "root");
  //senha do usuário
  settings.put("hibernate.connection.password", "secret");
  //contexto da sessão
  settings.put("hibernate.current_session_context_class", "thread");
  //mostra os comandos SQL ?
  settings.put("hibernate.show_sql", "false");
  //formata os comandos SQL exibidos ?
  settings.put("hibernate.format_sql", "true");
  /*
  hbm2ddl é a chave que indica como serão executadas as instruções DDL para
  definição do modelo de dados da base em questão
  os possíveis valores são os que seguem abaixo:
  
   validate: valida o esquema sem fazer mudanças no banco.
   update: atualiza o esquema do banco.
   create: destrói dados anteriores e cria o esquema do banco.
   create-drop: exclui o esquema ao término da sessão.
  
  esses valores podem desde criar todas as tabelas em relação às classes modelo ou apenas verificar a existência das mesmas
  é imprescindível que as classes definidas na aplicação estejam em concordância com o estabelecido no banco de dados
   */
  settings.put("hibernate.hbm2ddl.auto", "update");

  Properties properties = new Properties();
  properties.putAll(settings);

  try {
   //adiciona as classes do modelo para verificação do Hibernate / JPA
   //devem estar indicadas aqui todas as classes do sistema que são entidades
   Configuration configuration = new Configuration()
           .addAnnotatedClass(Cliente.class)
           .addAnnotatedClass(Produto.class)
           .addAnnotatedClass(Venda.class);
   //indica as propriedades utilizadas
   configuration.setProperties(properties);
   //cria a fábrica de sessões
   sessionFactory = configuration.buildSessionFactory();

  } catch (Throwable ex) {
   throw new ExceptionInInitializerError(ex);
  }
 }

 /**
  * método que retorna uma Session de acesso ao banco de dados
  */
 public static Session getInstance() {
  Session session = (Session) threadLocal.get();
  //cria uma sessão numa thread e a abre para ser utilizada
  session = sessionFactory.openSession();
  threadLocal.set(session);
  return session;
 }
}