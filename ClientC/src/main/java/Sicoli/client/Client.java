package Sicoli.client;

import java.io.*;
import java.net.*;

public class Client {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket socket;
    BufferedReader tastiera;
    String LetterUser = " ";
    String StringReply;
    DataInputStream in;
    DataOutputStream out;
    int biglietti;

    protected Socket connetti() {

        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in)); 
            socket = new Socket(nomeServer, portaServer); 
            out = new DataOutputStream(socket.getOutputStream()); 
            in = new DataInputStream(socket.getInputStream());

        } catch (Exception e) {

            System.err.println("Errore creazione Socket o Buffer");
            System.exit(1);
        }
        
        return socket;
    }

    public void comunica() throws IOException {
        
        try {
            for (;;) {
                StringReply = in.readLine();
                System.out.println(""+StringReply+" "+ '\n');
                System.out.println(" Inserisci lettera: " + '\n');
                LetterUser = tastiera.readLine();
                System.out.println(" Invio lettera al server e attendo...");
                out.writeBytes(LetterUser + '\n');
                

                if(StringReply.equals("Biglietti esauriti"+'\n')){
                    System.out.println("Biglietti esauriti"+ '\n');
                    System.exit(1);
                }

            }

        } catch (Exception e) {
            socket.close();
            
        }
    }
}
