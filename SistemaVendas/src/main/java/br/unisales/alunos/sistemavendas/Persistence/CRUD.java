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
import org.hibernate.Session;

/**
 *
 * @author enrico.eu
 */
public class CRUD {
    Session session = HibernateUtil.getInstance();
    Scanner scanner = new Scanner(System.in);

    public void insert() {
        EntityManager em = HibernateUtil.getInstance();
        try {
            System.out.println("Digite o nome do cliente:");
            String nomeCliente = scanner.nextLine();

            System.out.println("Digite o nome do produto:");
            String nomeProduto = scanner.nextLine();

            System.out.println("Preço do " + nomeProduto + " ?");
            String preco = scanner.next();
            double precoProduto = Double.parseDouble(preco.replace(",", "."));

            System.out.println("Quantos " + nomeProduto + " foram vendidos:");
            int quantidadeVendida = scanner.nextInt();

            em.getTransaction().begin();
            Venda v = new Venda(nomeCliente, nomeProduto, quantidadeVendida, precoProduto);

            em.persist(v);
            em.getTransaction().commit();
            System.out.println("""
                               \n------------------------------
                               Venda adicionada com sucesso
                               ------------------------------\n""");

        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Erro ao adicionar no banco de dados: " + ex.getLocalizedMessage());
        }
        em.close();
    }

    public void select() {
        List<Venda> consultaVendas = session.createQuery("select V from Venda V", Venda.class).getResultList();
        consultaVendas.forEach(venda -> {
            System.out.println("Id: " + venda.getId() + " | " + venda.getNomeProduto() + " vendido para " + venda.getNomeCliente() + " pelo total de: R$" + venda.getTotalVenda());
        });
    }

    public void update() {
        System.out.println("Digite o id da venda:");
        Long id = scanner.nextLong(); 
        Venda venda = session.find(Venda.class, id);
        if (venda != null){
            int optUpdate = 0; 
            do {
                String label = "";

                if (optUpdate > 0) {
                    label = "\nDeseja alterar mais algo?\n";

                } else {
                    label = "\nO que deseja alterar na venda?\n";
                } 

                System.out.println(label + """ 
                                   1 - Nome do cliente
                                   2 - Nome do produto
                                   3 - Preço do produto
                                   4 - Quantidade vendida do produto 
                                   0 - Ir para o menu inicial""");
                optUpdate = scanner.nextInt();
                try {
                    session.getTransaction().begin();

                switch (optUpdate) {
                    case 1 -> {
                        System.out.println("Digite o novo nome do cliente:");
                        String nomeCliente = scanner.nextLine(); 
                        venda.setNomeCliente(nomeCliente);
                    }
                    case 2 -> {
                        System.out.println("Digite o nome do produto para o qual deseja alterar:");
                        String nomeProduto = scanner.nextLine(); 
                        venda.setNomeProduto(nomeProduto);
                    }
                    case 3 -> {
                        System.out.println("Novo preço para " + venda.getPrecoProduto() + ": ");
                        String p = scanner.next();
                        double precoProduto = Double.parseDouble(p.replace(",", ".")); 
                        venda.setPrecoProduto(precoProduto);
                    }
                    case 4 -> {
                        System.out.println("Para qual quantidade vendida deseja alterar?");
                        int quantidadeVendida = scanner.nextInt(); 
                        venda.setQuantidadeVendida(quantidadeVendida);
                    } 
                } 
                session.persist(venda);
                session.getTransaction().commit();
                System.out.println("""
                                   ------------------------------
                                    Venda atualizada com sucesso
                                   ------------------------------""");
                } catch (Exception e) {
                    session.getTransaction().rollback();
                    System.out.println("""
                                ------------------------------
                                    Erro ao alterar venda
                                ------------------------------""");
                    System.out.println(e.getLocalizedMessage()); 
                } 
            } while (optUpdate != 0);
        } else System.out.println("\nVenda não encontrada!\n");
    }

    public void delete() {
        session.getTransaction().begin();
        System.out.println("Digite o id da venda");
        try {
            Long idVenda = scanner.nextLong();
            Venda venda = session.find(Venda.class, idVenda);
            session.remove(venda);
            session.getTransaction().commit(); 
            System.out.println("""
                                   \n------------------------------
                                        Venda removida com sucesso
                                   ------------------------------\n""");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("""
                               \n------------------------------
                                    Erro ao excluir venda
                               ------------------------------\n""");
            System.out.println(e.getLocalizedMessage()); 
        }
       
    }

    public void search() {
        System.out.println("Digite o nome do cliente para listar as vendas efetuadas para o mesmo:");
        String nomeCliente = scanner.nextLine();
        Query q = session.createQuery("select V from Venda V where V.nomeCliente like :NOME order by V.id", Venda.class);
        q.setParameter("NOME", nomeCliente + "%");
        List<Venda> listaVendas = q.getResultList();
        System.out.println("Abaixo encontra-se a lista de produtos vendidos para " + listaVendas.get(0).getNomeCliente() + " :");
        for (Venda venda : listaVendas) {
            System.out.println("Id(" + venda.getId() + ") - "
                    + venda.getNomeProduto()
                    + "(quantidade: " + venda.getQuantidadeVendida() + ") "
                    + " - Total da compra: R$" + venda.getTotalVenda());
        }
    }

}
