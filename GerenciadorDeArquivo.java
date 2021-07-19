/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bodega;

import java.io.*;
import java.util.ArrayList;
import bodega.GUI.ExceptionWindow;

/**
 *
 * @author Renato Marques
 */
public class GerenciadorDeArquivo {
      
    private static Produto[] carregarProdutos(File arquivo) {
        ArrayList<String> linhas = lerArquivo(arquivo);
        ArrayList<Produto> produtos = new ArrayList();
        for (String linha : linhas) {
            String[] dados = linha.trim().split(";");
            Produto produto;
            if (Integer.valueOf(dados[4]) == 1) {
                produto = new ProdutoPerecivel(dados[1]);
                ((ProdutoPerecivel) produto).setValidade(dados[5]);
            } else {
               produto = new Produto(dados[1]);                
            }
            try {
                produto.setPreco(dados[2]);
            } catch (bodega.exception.FIexception e) {
                ExceptionWindow aviso = new ExceptionWindow(e, null, true);
                aviso.setVisible(true);
                produto.setPreco(-1.);
            }
            try {
                produto.setQuantidade(dados[3]);
            } catch (bodega.exception.FIexception e) {
                ExceptionWindow aviso = new ExceptionWindow(e, null, true);
                aviso.setVisible(true);
                produto.setQuantidade(-1);
            }
            produto.setNome(dados[0]);
            produtos.add(produto);
        }
        return produtos.toArray(new Produto[produtos.size()]);
    }
    
    private static String salvarProdutos(Bodega bodega) {
        StringBuilder string = new StringBuilder();
        concatenarString(string, bodega.listarProdutos());
        concatenarString(string, bodega.listarProdutosEmFalta());
        return string.toString();
    }
    
    private static void concatenarString(StringBuilder string, Produto[] produtos) {
        for (Produto produto : produtos) {
            string.append(produto.getNome());
            string.append(";");
            string.append(produto.getCodigo());
            string.append(";");
            string.append(produto.getPreco());
            string.append(";");
            string.append(produto.getQuantidade());
            string.append(";");
            if (produto instanceof ProdutoPerecivel) {
                string.append(1);
            string.append(";");
                string.append(((ProdutoPerecivel) produto).getValidade());
            } else {
                string.append(0);
            }
            string.append(System.getProperty("line.separator"));
        }
    }
    
    public static void carregarBodega(File arquivo, Bodega bodega) {
        Produto[] produtos = carregarProdutos(arquivo);
        for (Produto produto : produtos) {
            try {
                bodega.adicionarProduto(produto);
            } catch (bodega.exception.PJCexception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void salvarBodega(File arquivo, Bodega bodega) {
        try {
            escreverArquivo(arquivo, salvarProdutos(bodega));            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static ArrayList<String> lerArquivo(File arquivo) {
        ArrayList<String> linhas = new ArrayList();
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();  
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            BufferedReader input = new BufferedReader(new FileReader(arquivo));
            try {
                String linha;
                while ((linha = input.readLine()) != null) {
                    linhas.add(linha);
                }
            } finally {
                input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linhas;
    }
    
    private static void escreverArquivo(File arquivo, String string) 
    throws FileNotFoundException, IOException {
//        if (!arquivo.exists()) {
//            arquivo = new File("dados.txt");
//        }
        Writer saida = new BufferedWriter(new FileWriter(arquivo));
        try {
            saida.write(string);
        } finally {
            saida.close();
        }
    }
}
