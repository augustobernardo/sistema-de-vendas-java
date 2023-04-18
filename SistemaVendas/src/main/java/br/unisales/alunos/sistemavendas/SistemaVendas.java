/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.unisales.alunos.sistemavendas;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Cliente;

public class SistemaVendas {

    public static void main(String[] args) {
        
        Cliente p1 = new Cliente(null, "Carlos", "199.934.432-23", "3312-4322");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema-de-venda-jpa");
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(p1);
        
        em.getTransaction().commit();
        System.out.println("Pronto!");
    }
}
