package java_rmi;

import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ObjetoRemoto extends UnicastRemoteObject implements ObjetoRemoto_I{
    
    ObjetoRemoto() throws RemoteException{
    
    }
    
    public Imagem enviarImagem(Imagem img)throws RemoteException{
        return img;
    }
}
