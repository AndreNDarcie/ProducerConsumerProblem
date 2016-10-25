/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteview;

import java.awt.Image;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class Cliente implements Runnable{

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
                    
                    //System.out.println("Cliente recebeu: " + buffer);
                    imageIcon = new ImageIcon(new ImageIcon(buffer).getImage().getScaledInstance(745, 385, Image.SCALE_SMOOTH));
                    TelaInicial.atualizaImagem(imageIcon);
                }
                
            } catch (Exception e) {
                System.err.println("Erro ao receber imagem!");
            }
    }
    
}
