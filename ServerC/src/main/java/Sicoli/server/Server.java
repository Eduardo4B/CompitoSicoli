package Sicoli.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{

    ServerSocket server = null;
    Socket client = null;
    String StringRV = null;
    String StringMD = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    int Biglietti=5;
    public Server(){
        
    }
    public Socket attendi(){

        try {
            String ind = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Server partito in esecuzione..."+ind);
            server = new ServerSocket(6789);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream( client.getOutputStream());

        } catch (Exception e) {

            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server");
            System.exit(1);
        }

        return client;
    }

    public void comunica() {

        try{
            
        outVersoClient.writeBytes(" Benvenuto client, scrivi la tua lettera. Attendo... \n");
        for(;;){
            StringRV = inDalClient.readLine();

                if(Biglietti>0){

                if(StringRV.equals("D")){

                StringMD=" Disponibili "  +  Biglietti + " biglietti";
                outVersoClient.writeBytes(StringMD+'\n');
                }   

                else if(StringRV.equals("A")){

                StringMD= " Biglietto acquistato";
                outVersoClient.writeBytes(StringMD +'\n');
                Biglietti--;
            }
        }
                else{
                outVersoClient.writeBytes("Biglietti esauriti"+'\n');  
                client.close();
            }

        }
        
            }       catch(Exception e){
            
             }   

            }

}   
