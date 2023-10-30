import java.io.*;
import java.net.Socket;

public class Cliente {
  private Socket socket;
  private DataOutputStream datos;
  private DataInputStream recibir;
  private ChatCliente chat;
  private String nickname;

  Cliente() {
    try {
      socket = new Socket("127.0.0.1", 9999);
      datos = new DataOutputStream(socket.getOutputStream());
      recibir = new DataInputStream(socket.getInputStream());
      nickname = "Invitado";
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  public void IniciarCliente() {
    BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
    String usr;
    System.out.print("Ingrese usuario : ");
    try {
      usr = leer.readLine();
      this.nickname = usr;
      chat = new ChatCliente(socket);
      chat.start();

      String mensaje;
      while (true) {
        System.out.print(nickname + " : ");
        mensaje = leer.readLine();
        datos.writeUTF(mensaje);
      }

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }


  private void registrar() {
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
//      send.setNickname(usr);
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

}
