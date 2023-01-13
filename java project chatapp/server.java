import java.net.*;
import java.io.*;
class server {
    ServerSocket server;
    Socket socket;

    BufferedReader br;
    PrintWriter out;

    //constructor..
    public server()
    {
        try{
            server=new ServerSocket(7777);
            System.out.println("server is ready to accept connection");
            System.out.println("waiting...");
            socket=server.accept();

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out= new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   //multithreding
    public void startReading(){
          Runnable r1=()-> {              //using lambda expression.
              System.out.println("reader started..");
              try {
                  while (true) {

                      String msg = br.readLine();
                      if (msg.equals("exit")) {
                          System.out.println("Client terminated the chat");
                          break;
                      }

                      System.out.println("Client : " + msg);

              }
          }catch(Exception e){
              e.printStackTrace();
        }
          };

          //Program to start thread

          new Thread(r1).start();
    }

    public void  startWriting(){
         Runnable r2=()-> {              //using lambda expression.
             System.out.println("writer started ...");
             try {
                 while (true && !socket.isClosed() ) {

                     BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

                     String content = br1.readLine();

                     out.println(content);
                     out.flush();
                 }
                 }    catch(Exception e){
                     e.printStackTrace();
                 }
         };

         //coding to start the thread

        new Thread(r2).start();
    }





    public static void main(String[] args) {
        System.out.println("This is server... going to lunch...");
        new server();
    }
}