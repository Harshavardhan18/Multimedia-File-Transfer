import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {	
	
	String ip_addr = null;
	protected Socket s = null;
	protected DataInputStream is = null;
	protected DataOutputStream os = null;
	protected String filename;
	
	//Constructors
	public Client() {
	}

	public Client(String ip_addr2, int pORT2) {
		// TODO Auto-generated constructor stub
		try {
			InetAddress ip = InetAddress.getByName(ip_addr2);
			s = new Socket(ip, pORT2);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
        // Checking if socket is connected
	public String validConnection() {
		if(!s.isConnected())
			return "Failed";
		try {
			is = new DataInputStream(s.getInputStream());
			os = new DataOutputStream(s.getOutputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "Connected";
	}
	//Sending file name to server
	public String sendFilename(String file_name) {
		String str = null;
		filename = file_name;
		try {
			os.writeUTF(file_name);
			str = is.readUTF();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	//Recieving file contents from server
	public String recieveFile() {
		String str;
		try {
			FileOutputStream fout = new FileOutputStream(new File(filename));
			int ch;
			do{
				str = is.readUTF();
				ch = Integer.parseInt(str);
				if (ch != -1)
					fout.write(ch);
			}while(ch != -1);
			fout.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "File Downloaded";
	}
        //Exit the connection
	public void exitSocket(String exit) {
		try {
			os.writeUTF(exit);
			s.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}

