/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp_tema03_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ITP1DAM
 */
public class ServerChat {
    
    private Mensaje msg;
    private ArrayList<Mensaje> Mensajes;
    private static final int PUERTO = 6000;
    private static final int MAX_CLIENTES = 99;
    
    public ServerChat() {
        try {
            // Creo el socket servidor que escucha
            // en el puerto 6000
            ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO );
            
            //Establece mensaje de bienvenida
            Mensajes = new ArrayList<Mensaje>();
            Mensajes.add(new Mensaje("¡Bienvenido al chat!"));
            
            // Escucho a un cierto número de clientes
            for ( int numCli = 0; numCli < MAX_CLIENTES; numCli++ ) {
                // Escucho a un cliente
                Socket skCliente = skServidor.accept();
                
                // Cuando escucha un cliente da un aviso
                System.out.println("Sirvo al cliente " + numCli);
                
                // Obtengo el flujo de entrada del cliente
                // (Mensajes que recibe del cliente)
                InputStream aux2 = skCliente.getInputStream();
                DataInputStream flujorecibir = new DataInputStream( aux2 );
                String msgTemp = flujorecibir.readUTF();
                System.out.println("El cliente " + numCli + " envía: " + msgTemp);
                System.out.println(Mensajes);
                Mensajes.add(new Mensaje(msgTemp));
                
                
                // Obtengo el flujo de salida del cliente
                // (Mensajes que envía al cliente)
                OutputStream aux = skCliente.getOutputStream();
                DataOutputStream flujoenviar = new DataOutputStream( aux );
                
                // Manda un mensaje al cliente
                flujoenviar.writeUTF( "Hola cliente " + numCli );
                
                // Cierro el socket servidor
                skCliente.close();
            }
            System.out.println("Demasiados clientes por hoy.");
        } catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
    }

    // Método main del servidor
    public static void main(String[] args) {
        // Creo un servidor
        new ServerChat();
    }
    
}
