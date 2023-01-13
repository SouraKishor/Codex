import java.net.*;
import java.io.*;

public class client {


    Socket socket;
    BufferedReader br;
    PrintWriter out;
    public client (){

        try {
            System.out.println("Sending request to server");
            socket=new Socket("127.0.0.1",7777);
            System.out.println("connection done");

            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        }catch (Exception e){

        }
    }

    public void startReading(){
        Runnable r1=()-> {              //using lambda expression.
            System.out.println("reader started..");
            try {
                while (true) {

                    String msg = br.readLine();
                    if (msg.equals("exit")) {
                        System.out.println("Server terminated the chat");

                        socket.close();

                        break;
                    }

                    System.out.println("server : " + msg);
                }

        }catch(Exception e){
            e.printStackTrace();
        }
        };
        //Program to start thread

        new Thread(r1).start();
    }

    public void  startWriting(){
        Runnable r2=()->{              //using lambda expression.
            System.out.println("writer started ...");
            try{
            while(true && !socket.isClosed()) {

                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

                String content = br1.readLine();

                out.println(content);
                out.flush();
                  //to handel exception.
                }

        }catch (Exception e) {
                e.printStackTrace();
            }
        };

        //coding to start the thread

        new Thread(r2).start();
    }

    public static void main (String[] args){
        System.out.println("this is client");
        new client();
    }
}
