import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

class ClientHandler extends Thread  {
	final Socket sk;
	private boolean flag = false;
	final DataInputStream is;
	final DataOutputStream os;
 	
	// Constructor
	public ClientHandler(Socket sk, DataInputStream is, DataOutputStream os) {
		this.sk = sk;
		this.is = is;
		this.os = os;
	}
		
	public void run() {
		
		while (true) {
			try {
				
				// Get filename
				String file_name = is.readUTF();
				System.out.println(file_name);
				
				if (file_name.equalsIgnoreCase("Exit")) {
					System.out.println("Client " + this.sk + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.sk.close();
                    System.out.println("Connection closed");
                    break;
				}
				
				File folder = new File("C:\\Users\\Harsha527\\Desktop\\project\\MINI PROJECT");
				flag = false;
				File[] list_of_files = folder.listFiles();
				for (File file : list_of_files){
					if(file.getName().equals(file_name)){
						System.out.println("File requested by client " + sk + " found");
						flag = true;
					}
				}

				if (flag) {
					try {
						
						// Read the contents
						System.out.println("Sending file contents to client" + sk);
						os.writeUTF("sending");
						FileInputStream fin = new FileInputStream(new File("C:\\Users\\Harsha527\\Desktop\\project\\MINI PROJECT\\" + file_name));
						int ch;
						do {
							ch = fin.read();
							os.writeUTF(String.valueOf(ch));
						}while(ch != -1);
						fin.close();
						System.out.println("File Sent to Client:" + sk);
					}catch (IOException e) { 
						e.printStackTrace();
					}
				}
				else {					
						System.out.println("File requested by " + sk + " does not exist at server");	
						os.writeUTF("File does not exist at Server");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}