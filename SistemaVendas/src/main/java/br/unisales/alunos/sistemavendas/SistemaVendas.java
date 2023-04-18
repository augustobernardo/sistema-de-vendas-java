/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unisales.alunos.sistemavendas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import br.unisales.alunos.sistemavendas.Models.Cliente;

public class SistemaVendas {

    public static void main(String[] args) {
        
        Cliente p1 = new Cliente(null, "Carlos", "199.934.432-23", "3312-4322");
        
        
        
        System.out.println("Pronto!");
        
    }
}
