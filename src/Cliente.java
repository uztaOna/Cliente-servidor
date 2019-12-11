import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	static final String IP= "localhost";
	static final int PUERTO= 5000;
	
	public void hasi() {
		Persona p;
		Socket cliente=null;
		ObjectInputStream entrada=null;
		ObjectOutputStream salida=null;
		
		try {
			cliente=new Socket(IP,PUERTO);
			System.out.println("Conexión realizada con servidor");
			salida = new ObjectOutputStream (cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());
			
			p = (Persona) entrada.readObject();
			System.out.println(p.getNombre());
			
			//Modificar datos Persona
			System.out.println("Modificando datos de cliente...");
			p.setNombre("Carmen");
			p.setFechaNac("1983,1,1");
			salida.writeObject(p);
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Cliente c1=new Cliente();
		c1.hasi();
	}
}
