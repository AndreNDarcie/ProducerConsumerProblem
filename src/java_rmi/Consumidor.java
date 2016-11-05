package java_rmi;

import java.rmi.registry.Registry;

public class Consumidor {
    
    private static Registry registry;

    public static void main(String[] args) {

        try {
            
            ObjetoRemoto objr = new ObjetoRemoto();
            registry = java.rmi.registry.LocateRegistry.createRegistry(1099);
            registry.bind("servidor_imagens", objr);
            System.out.println("Conexao efetuada");
        } catch (Exception e) {
            
            System.err.println("Cadastro do no no RMIRegistry: "
                    + e.getMessage());
            e.printStackTrace();
        }

    }

}
