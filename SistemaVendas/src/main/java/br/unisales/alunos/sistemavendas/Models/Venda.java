/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unisales.alunos.sistemavendas.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author enrico.eu
 */
@Entity
@Table(name = "vendas")
public class Venda {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;

    private String nomeProduto;

    private Double precoProduto;

    private int quantidadeVendida;

    private Double totalVenda;
    
    public Venda(){
    };

    public Venda(String nomeCliente, String nomeProduto, int quantidadeVendida, Double precoProduto) {
        super();
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.precoProduto = precoProduto;
        this.totalVenda = Double.valueOf(quantidadeVendida) * precoProduto;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public Double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(int quantidadeVendida, Double precoProduto) {
        this.totalVenda = Double.valueOf(quantidadeVendida) * precoProduto;
    }
}
