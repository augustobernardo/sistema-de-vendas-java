/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.unisales.alunos.sistemavendas;

import br.unisales.alunos.sistemavendas.Models.Venda;
import br.unisales.alunos.sistemavendas.Persistence.CRUD;
import br.unisales.alunos.sistemavendas.Persistence.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Session;

public class SistemaVendas {

    public static void main(String[] args) {
        Session s = HibernateUtil.getInstance();
        Scanner sc = new Scanner(System.in);

        CRUD operacoes = new CRUD();
        int op = 0;

        //menu de execução
        do {
            System.out.println("\nSistema de Registro de Vendas"
                    + "\n1 - Inserir venda"
                    + "\n2 - Listar vendas"
                    + "\n3 - Corrigir (alterar) venda"
                    + "\n4 - Excluir venda"
                    + "\n5 - Pesquisar venda por nome do cliente"
                    + "\n0 - sair");
            op = sc.nextInt();
            switch (op) {
                case 1:
                    operacoes.insert(s, sc);
                    break;

                case 2:
                    operacoes.select(s);
                    break;

                case 3:
                    operacoes.update(s, sc);
                    break;
                case 4:
                    operacoes.delete(s, sc);
                    break;
                case 5:
                    operacoes.search(s, sc);
                    break;
            }
        } while (op != 0);
    }

}
