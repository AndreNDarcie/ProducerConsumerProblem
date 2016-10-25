package remoteview;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

public class TrataCliente implements Runnable {

    private Socket cliente;
    
    private int autorizaProducao = 0;  
    private int copiaTerminaConsumir = 0;
    private int tamanhoLista = 0;
    
    public TrataCliente(Socket cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public void run() {
        
        //String mensagem = "Envia imagem";
        
        try{
            ObjectOutputStream out = new ObjectOutputStream(cliente.getOutputStream());
            ByteArrayOutputStream baos;
            BufferedImage screenCapturedImage;
            
            while(true) {
                baos = new ByteArrayOutputStream();
                screenCapturedImage = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(screenCapturedImage, "png", baos);
                
                //System.out.println("Servidor enviou: " + baos.toByteArray());
                out.writeObject(baos.toByteArray());
                
                out.flush();
                baos.close();
                
                Thread.sleep(TelaInicial.intervaloTempo);
            }
            
        } catch (Exception e){
            System.err.println("Erro ao enviar imagem ao cliente: " + cliente.getInetAddress().getHostAddress());
        }
        
    }
    
    public void Produz(){ // Tira uma print da tela e envia
        if (autorizaProducao - copiaTerminaConsumir < tamanhoLista){
            autorizaProducao = autorizaProducao + 1;
            enviaObjeto();
        }
    }
    
    public void recebeImcremento(){ // Recebe a mensagem de incremento do cliente
        copiaTerminaConsumir = copiaTerminaConsumir + 1;
    }
    
    // TODO: fazer o envio
    public void enviaObjeto(){ // Envia imagem
        
    }
    
}
