package remoteview;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.imageio.ImageIO;

public class Produtor implements Runnable{
    
    private int autorizaProducao = 0;  
    private int copiaTerminaConsumir = 0;
    private final int tamanhoLista = 3;
    
    private ObjectOutputStream out;
    
    public Produtor(){
        
    }
    
    @Override
    public void run() {
        try{
                ServerSocket servidor = new ServerSocket();
                servidor.bind(new InetSocketAddress("0.0.0.0", 9000));
                System.out.println("Produtor Iniciado!");
                
                try {
                    while(true)
                    {
                        Socket cliente = servidor.accept();

                        out = new ObjectOutputStream(cliente.getOutputStream());
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
                
                servidor.close();
                System.out.println("Produtor finalizado!");
                
            }catch (Exception e) {
                System.err.println("Erro no Produtor!");
            }
    }
    
    public void Produz(){ // Tira uma print da tela e envia
        if (autorizaProducao - copiaTerminaConsumir < tamanhoLista){
            autorizaProducao = autorizaProducao + 1;
            
            try{
                BufferedImage screenCapturedImage = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                enviaObjeto(screenCapturedImage);
            } catch(AWTException | HeadlessException e){
                System.err.println(e.getMessage());
            }
        }
    }
    
    public void recebeImcremento(){ // Recebe a mensagem de incremento do consumidor
        copiaTerminaConsumir = copiaTerminaConsumir + 1;
    }
    
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
            System.err.println("Erro ao enviar imagem ao consumidor");
        }
    }
}