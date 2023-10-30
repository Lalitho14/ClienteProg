import java.io.*;
import java.net.Socket;

public class Cliente extends Thread {
  private Socket socket;
  private DataOutputStream datos;
  private DataInputStream recibir;
  EnviarMensajes send;
  RecibirMensajes recive;
  private String nickname;

  Cliente() {
    try {
      socket = new Socket("127.0.0.1", 9999);
      datos = new DataOutputStream(socket.getOutputStream());
      recibir = new DataInputStream(socket.getInputStream());
      send = new EnviarMensajes(socket, datos);
      recive = new RecibirMensajes(recibir);
      nickname = "Invitado";
      send.setNickname(nickname);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void run() {
    try {
//      registrar();
      recive.start();
      send.start();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void registrar() {
    try {
      String mensaje, usr;
      BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("Ingresa nombre de Usuario : ");
      mensaje = leer.readLine();
      datos.writeUTF(mensaje);
      usr = mensaje;
      mensaje = recibir.readUTF();
      nickname = mensaje;
      System.out.println(mensaje);
      send.setNickname(usr);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

}
