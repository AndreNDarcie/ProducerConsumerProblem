package java_rmi;

import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjetoRemoto extends UnicastRemoteObject implements ObjetoRemoto_I{
    
    ObjetoRemoto() throws RemoteException{
    
    }
    
    public synchronized Boolean enviarImagem(Imagem img)throws RemoteException{
        
        if (Consumidor.filaImagem.size() < Consumidor.tamanhoLista){
            Consumidor.filaImagem.add(img);
        } else {
            System.out.println("Lista cheia");
           return false;
        } 
        return true;
    }
}
