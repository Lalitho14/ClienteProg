import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class EnviarMensajes extends Thread {
  Socket socket;
  DataOutputStream datos;
  String nickname;

  public EnviarMensajes(Socket socket, DataOutputStream datos) {
    this.socket = socket;
    this.datos = datos;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public void enviarMensajes() {
    try {
      String mensaje = "";
      BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));

      while (!mensaje.equals("Salir")) {
        mensaje = leer.readLine();
        datos.writeUTF(nickname + " : " + mensaje);
      }
      socket.close();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  @Override
  public void run() {
    enviarMensajes();
  }
}
