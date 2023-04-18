/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unisales.alunos.sistemavendas.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

/**
 *
 * @author alunos.unisales
 */

@Entity
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idCliente;
    private String nome;
    private String cpf;
    private String telefone;

    public Cliente(Integer idCliente, String nome, String cpf, String telefone) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente{" + "idCliente=" + idCliente + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone + '}';
    }

}
