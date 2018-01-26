/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp_tema03_chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author ITP1DAM
 */
public class ClientChat {
    private static final String HOST = "localhost";
    private static final int PUERTO = 6000;
    private Random aleatorio;
    
    public ClientChat(String mensaje, boolean refresh) {
        aleatorio = new Random();
        
        try {
            // Creo el socket cliente que escucha en la
            // máquina localhost y en el puerto 6000
            Socket skCliente = new Socket( HOST , PUERTO );
            
            // Obtengo el flujo de entrada del cliente creado
            // (Mensajes que recibe el cliente del servidor)
            InputStream aux = skCliente.getInputStream();
            DataInputStream flujorecibir = new DataInputStream( aux );
            
            // Obtengo el flujo de salida del cliente creado
            // (Mensajes que envía al servidor)
            OutputStream aux2 = skCliente.getOutputStream();
            DataOutputStream flujoenviar = new DataOutputStream( aux2 );
            
            // Manda un mensaje al servidor
            int numero = aleatorio.nextInt(100)+1;
            System.out.println("Le he enviado al servidor " + numero);
            flujoenviar.writeUTF(String.valueOf(numero));
            
            // Saco por pantalla el mensaje recibido del servidor
            System.out.print("Mensaje recibido del servidor: ");
            System.out.println( flujorecibir.readUTF() );
            
            // Cierro el socket
            skCliente.close();
            System.out.println("Cierra conexion");
        } catch( IOException ex ) {
            System.out.println("Error -> " + ex.toString());
        }
    }
    
    public void enviar() throws IOException {
        
    }
    
    public void cerrar() throws IOException {
        
    }
    
    // Método main del cliente
    public static void main(String[] args) {
        // Creo un cliente
        Interfaz inter = new Interfaz();
        inter.setVisible(true);
        
        ClientChat c = new ClientChat("fuck",false);
    }
}
