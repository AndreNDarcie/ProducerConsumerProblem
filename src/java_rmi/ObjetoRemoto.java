package java_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

class ObjetoRemoto extends UnicastRemoteObject implements ObjetoRemoto_I{
    
    ObjetoRemoto() throws RemoteException{
    
    }
    
    @Override
    public void enviarImagem(Imagem i)throws RemoteException{
        
    }
}
