/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package br.unisales.alunos.sistemavendas;

import br.unisales.alunos.sistemavendas.Persistence.CRUD;
import java.util.Scanner;

public class SistemaVendas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CRUD operacoes = new CRUD();
        int op = 0;

        //menu de execução
        do {
            System.out.println("""
                               Sistema de Registro de Vendas
                               1 - Inserir venda
                               2 - Listar vendas
                               3 - Corrigir (alterar) venda
                               4 - Excluir venda
                               5 - Pesquisar venda por nome do cliente
                               0 - sair""");
            op = sc.nextInt();
            switch (op) {
                case 1 -> operacoes.insert();

                case 2 -> operacoes.select();

                case 3 -> operacoes.update();
                case 4 -> operacoes.delete();
                case 5 -> operacoes.search();
            }
        } while (op != 0);
    }

}
