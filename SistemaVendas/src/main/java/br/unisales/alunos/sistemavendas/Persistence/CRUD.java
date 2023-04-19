/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unisales.alunos.sistemavendas.Persistence;

import br.unisales.alunos.sistemavendas.Models.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Scanner;
import org.checkerframework.checker.units.qual.s;
import org.hibernate.Session;

/**
 *
 * @author enrico.eu
 */
public class CRUD {

    public void insert(Session s, Scanner sc) {
        EntityManager em = HibernateUtil.getInstance(); // recupera um gerenciador de entidades
        try {
            System.out.println("Digite o nome do cliente:");
            String nomeCliente = sc.next();

            System.out.println("Digite o nome do produto:");
            String nomeProduto = sc.next();

            System.out.println("Preço do " + nomeProduto + " ?");
            String preco = sc.next();
            double precoProduto = Double.parseDouble(preco.replace(",", "."));

            System.out.println("Quantos " + nomeProduto + " foram vendidos:");
            int quantidadeVendida = sc.nextInt();

            em.getTransaction().begin(); // inicializa a transação
            Venda v = new Venda(nomeCliente, nomeProduto, quantidadeVendida, precoProduto); // cria uma entidade

            em.persist(v); // insere no banco
            em.getTransaction().commit(); // comita a transação

            System.out.println("Adicionado com sucesso!");

        } catch (Exception ex) {
            em.getTransaction().rollback(); // invalida a transação
            System.out.println("Erro ao adicionar no banco de dados: " + ex.getLocalizedMessage());
        }
        em.close(); // fecha o gerenciador
    }

    public void select(Session s) {
        //lista pessoas usando query JPQL
        List<Venda> listaVenda = s.createQuery("select V from Venda V order by V.nomeCliente", Venda.class).getResultList();
        //percorre a lista de pesoas
        for (Venda vl : listaVenda) {
            System.out.println("(Id: " + vl.getId() + ") " + vl.getNomeProduto() + " vendido para " + vl.getNomeCliente() + " pelo total de: R$" + vl.getTotalVenda());
        }
    }

    public void update(Session s, Scanner sc) {
        System.out.println("Digite o id da venda:");
        Long id = sc.nextLong();
        Venda venda = s.find(Venda.class, id);

        int optUpdate = 0;

        do {
            String label = "";

            if (optUpdate > 0) {
                label = "\nDeseja alterar mais algo?";

            } else {
                label = "\nO que deseja alterar na venda?";
            }

            System.out.println(label
                    + "\n1 - Nome do cliente"
                    + "\n2 - Nome do produto"
                    + "\n3 - Preço do produto"
                    + "\n4 - Quantidade vendida do produto"
                    + "\n0 - Ir para o menu inicial");
            optUpdate = sc.nextInt();

            s.getTransaction().begin();  //inicia a transação

            switch (optUpdate) {
                case 1:
                    System.out.println("Digite o novo nome do cliente:");
                    String nomeClienteUpdate = sc.next();

                    venda.setNomeCliente(nomeClienteUpdate);
                    break;
                case 2:
                    System.out.println("Digite o novo produto:");
                    String nomeProdutoUpdate = sc.next();

                    venda.setNomeProduto(nomeProdutoUpdate);
                    break;
                case 3:
                    System.out.println("Novo preço para " + venda.getPrecoProduto() + ": ");
                    String p = sc.next();
                    double precoProdutoUpdate = Double.parseDouble(p.replace(",", "."));

                    venda.setPrecoProduto(precoProdutoUpdate);
                    break;
                case 4:
                    System.out.println("Para qual quantidade vendida deseja alterar?");
                    int quantidadeVendidaUpdate = sc.nextInt();

                    venda.setQuantidadeVendida(quantidadeVendidaUpdate);
                    break;
            }

            s.persist(venda);
            s.getTransaction().commit();//confirma a transação
        } while (optUpdate != 0);
    }
    
    
    public void delete(Session s, Scanner sc) {
        s.getTransaction().begin();//inicia a transação
     System.out.println("digite o id da pessoa");
     Long ide = sc.nextLong();
     //seleciona uma pessoa pelo id
     //indicada a classe para pesquisa - Pessoa.class
     Venda vd = s.find(Venda.class, ide);
     s.remove(vd);//exclui no banco
     s.getTransaction().commit();//confirma a transação
    }
    
    
    public void search(Session s, Scanner sc) {
        System.out.println("Digite do cliente para listar suas vendas para o mesmo:");
                    String inicio = sc.next();
                    //cria uma Query com parâmetro
                    //observe que o parâmetro é iniciado com dois pontos ':'
                    Query q = s.createQuery("select V from Venda V where V.nomeCliente like :nome order by V.nomeProduto", Venda.class);
                    //indica nominalmente o valor do parâmetro antes da execução da instrução SQL
                    q.setParameter("nome", inicio + "%");
                    //executa a instrução SQL
                    List<Venda> listaVendas = q.getResultList();
                    //percorre a lista de pessoas
                    System.out.println("Abaixo encontra-se a lista de produtos vendidos para " + listaVendas.get(0).getNomeCliente() + " :");
                    for (Venda vl : listaVendas) {
                        System.out.println("Id(" + vl.getId() + ") - "
                                + vl.getNomeProduto()
                                + "(quantidade: " + vl.getQuantidadeVendida() + ") "
                                + " - Total da compra: R$" + vl.getTotalVenda());
                    }
    }

}
