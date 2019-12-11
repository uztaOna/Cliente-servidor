import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	static final int PUERTO=5000;
	
	public void hasi() throws IOException {
		ServerSocket servidor=null;
		Socket cliente=null;
		ObjectInputStream entrada=null;
		ObjectOutputStream salida=null;
		Persona p;
		
		try {
			servidor=new ServerSocket(PUERTO);
			System.out.println("Esperando conexiones de cliente...");
			cliente=servidor.accept();
			System.out.println("Cliente conectado");
			salida=new ObjectOutputStream(cliente.getOutputStream());
			entrada=new ObjectInputStream(cliente.getInputStream());
			
			
			p=new Persona();
			p.setNif("12345678X");
			p.setNombre("Garcilaso");
			p.setApellido("De la Vega");
			p.setFechaNac("1990,12,16");
			p.setGen('X');
			salida.writeObject(p);
			
			//Recibir datos modificados
			p = (Persona) entrada.readObject();
			System.out.println("Datos modificados: " + p.getNombre());
			
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if(servidor!=null)
				servidor.close();
			if(cliente!=null)
				cliente.close();
			if(entrada!=null)
				entrada.close();
			if(salida!=null)
				salida.close();
		}
	}

	public static void main(String[] args) {
		Servidor s1=new Servidor();	
		try {
			s1.hasi();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
