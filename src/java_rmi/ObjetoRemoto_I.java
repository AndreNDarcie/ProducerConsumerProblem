package java_rmi;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

interface ObjetoRemoto_I extends Remote{
    Boolean enviarImagem(Imagem i) throws RemoteException;
}
