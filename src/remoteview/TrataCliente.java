package remoteview;

import java.awt.AWTException;
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
    private int tamanhoLista = 3;
    
    private ObjectOutputStream out;
    
    public TrataCliente(Socket cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public void run() {
        try{
            out = new ObjectOutputStream(cliente.getOutputStream());
  
            
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public void Produz() throws AWTException{ // Tira uma print da tela e envia
        if (autorizaProducao - copiaTerminaConsumir < tamanhoLista){
            autorizaProducao = autorizaProducao + 1;
            
            BufferedImage screenCapturedImage = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            enviaObjeto(screenCapturedImage);
        }
    }
    
    public void recebeImcremento(){ // Recebe a mensagem de incremento do cliente
        copiaTerminaConsumir = copiaTerminaConsumir + 1;
    }
    
    // TODO: fazer o envio
    public void enviaObjeto(BufferedImage screenCapturedImage){ // Envia imagem
        try{
            ByteArrayOutputStream baos;
            baos = new ByteArrayOutputStream();
            ImageIO.write(screenCapturedImage, "png", baos);

            //System.out.println("Servidor enviou: " + baos.toByteArray());
            out.writeObject(baos.toByteArray());

            out.flush();
            baos.close();
        } catch(Exception e){
            System.err.println("Erro ao enviar imagem ao cliente");
        }
    }
    
}
