package java_rmi;

import java.awt.Image;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

interface ObjetoRemoto_I extends Remote{
    void enviarImagem(Imagem i) throws RemoteException;
}
