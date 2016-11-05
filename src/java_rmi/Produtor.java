/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author udo
 */
public class Produtor {
    public static void main(String[] args) {
        ObjetoRemoto_I objr;
        try {
            objr = (ObjetoRemoto_I) Naming.lookup("servidor_imagens");
            //objr.imprimeMsg("ola!");
            //System.out.println("soma:"+objr.soma(10, 20));
            //Parametro p = new Parametro("Mensagem de sd");
            //objr.recebeObjeto(p);
            
        } catch (RemoteException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
