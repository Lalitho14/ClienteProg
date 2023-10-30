import java.io.DataInputStream;
import java.net.Socket;

public class ChatCliente extends Thread {
  private DataInputStream leer;

  public ChatCliente(Socket socket){
    try{
      this.leer=new DataInputStream(socket.getInputStream());
    }catch (Exception e){
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void run(){
    try{
      String mensaje;
      while ((mensaje=leer.readUTF()) != null){
        System.out.println(mensaje);
      }
    }catch (Exception e){
      System.err.println(e.getMessage());
    }
  }
}
