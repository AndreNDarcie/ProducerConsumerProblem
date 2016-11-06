package java_rmi;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Imagem implements Serializable{
    
    private JLabel imagem;
    
    public Imagem(){
        imagem = new JLabel();
    }
    
    public void setImagem(ImageIcon imagem){
        this.imagem.setIcon(imagem);
    }
    
    public JLabel getImagem(){
        return this.imagem;
    }
    
}
