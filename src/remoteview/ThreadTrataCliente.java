package remoteview;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

public class ThreadTrataCliente implements Runnable {

    private Socket cliente;
    
    public ThreadTrataCliente(Socket cliente) {
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
    
}
