/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unisales.logic;


import br.unisales.models.Cliente;
import br.unisales.models.Produto;
import br.unisales.models.Venda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String nomeArquivo = "dados.dat";
    
    private static List<Venda> vendas = new ArrayList<>();
    
    private static List<Cliente> clientes = new ArrayList<>();
    
    private static List<Produto> produtos = new ArrayList<>();


    /*
 public static void recuperaDados() {
  try {
   File f = new File(nomeArquivo);
   if(!f.exists()){
    f.createNewFile();
   }
   FileInputStream fis = new FileInputStream(f);
   ObjectInputStream ois = new ObjectInputStream(fis);
   lista = (List) ois.readObject();
  } catch (Exception ex) {
   System.out.println("ocorreu erro");
  }
 }
 
 public static void salvaDados(){
  try{
   File f = new File(nomeArquivo);
   FileOutputStream fos = new FileOutputStream(f);
   ObjectOutputStream oos = new ObjectOutputStream(fos);
   oos.writeObject(lista);
  }catch(Exception ex){
   System.out.println("erro de gravação");
  }
 } */
}
