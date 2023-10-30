import java.io.DataInputStream;

public class RecibirMensajes extends Thread {
  DataInputStream recibir;

  public RecibirMensajes(DataInputStream recibir) {
    this.recibir = recibir;
  }

  public void escucharMensajes() {
    try {
      while (true) {
        String mensaje;
        mensaje = recibir.readUTF();
        System.out.println(mensaje);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void run(){
    escucharMensajes();
  }
}
