package Sicoli.client;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Client client1 = new Client();
        client1.connetti();
        client1.comunica();
    }
}
