/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteview;

import java.awt.Image;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class Consumidor implements Runnable{
    
    //Lista de imagens: lista
    private int autorizaConsumir = 0;
    private int terminaProducao = 0;
    
    @Override
    public void run() {
        try {
                Socket cliente = new Socket("127.0.0.1", 9000);
                ObjectInputStream in = new ObjectInputStream(cliente.getInputStream());
                byte[] buffer = new byte[2000000];
                //ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ImageIcon imageIcon;
                
                while(true) {
                    buffer = (byte[]) in.readObject();
                    
                    System.out.println("Consumidor recebeu imagem e guardou na lista");
                    imageIcon = new ImageIcon(new ImageIcon(buffer).getImage().getScaledInstance(745, 385, Image.SCALE_SMOOTH));
                    TelaInicial.atualizaImagem(imageIcon);
                }
                
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao receber imagem do Produtor!");
            }
    }
    
    public void Consome(){ // Recebe a imagem por parametro
        if (autorizaConsumir - terminaProducao < 0){
            autorizaConsumir = autorizaConsumir + 1;
            
            // Pega imagem da lista e exibe na tela
            
            // Envia mensagem de incremento para servidor
        }
    }
    
    public void recebeObjeto(){ // Recebe a mensagem
        // Coloca a imagem na lista
        terminaProducao = terminaProducao + 1;
    }
    
}
