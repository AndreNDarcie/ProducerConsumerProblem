package remoteview;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import static remoteview.TelaInicial.intervaloTempo;

public class Servidor implements Runnable{

    private int intervaloTempo;
    
    public Servidor(int intervaloTempo){
        this.intervaloTempo = intervaloTempo;
    }
    
    @Override
    public void run() {
        try{   
                ServerSocket servidor = new ServerSocket();
                servidor.bind(new InetSocketAddress("0.0.0.0", 9000));
                System.out.println("Server Online!");
                
                try {
                    while(true)
                    {
                        Socket cliente = servidor.accept();

                        TrataCliente ttc = new TrataCliente(cliente);
                        Thread t = new Thread(ttc);
                        t.start();
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
                
                servidor.close();
                System.out.println("Servidor finalizado!");
                
            }catch (Exception e) {
                System.err.println("Erro no Servidor!");
            }
    }
    
}
