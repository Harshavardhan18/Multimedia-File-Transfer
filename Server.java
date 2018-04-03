import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// Server listening to port 
		ServerSocket ss = new ServerSocket(9898);
		System.out.println("Waiting for connections");
		
		// Process client request
		while (true) {
			Socket sk = null;
			try {
				
				sk = ss.accept();
				System.out.println(sk + " Client Connected");

				DataInputStream is = new DataInputStream(sk.getInputStream());
				DataOutputStream os = new DataOutputStream(sk.getOutputStream());

				// Creating a thread object 
				Thread t = new ClientHandler(sk, is, os);
				t.start();
			}
			catch (Exception e) {
				sk.close();
				ss.close();
				e.printStackTrace();
			}
		}
	}	
}
