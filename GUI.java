import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
	static Client c;

	public static void main(String args[])
	{
		JFrame f = new JFrame();
		JLabel ipL = new JLabel("IP of server:");
		JLabel portL = new JLabel("Port no:");
		final JTextField ip = new JTextField(15);
		final JTextField port = new JTextField(5);
		final JButton connect = new JButton("Connect");
		JLabel fileL = new JLabel("File name:");
		final JTextField file = new JTextField();
		JButton download = new JButton("Download");
		JLabel logL = new JLabel("File log:");
		final JTextArea log = new JTextArea();
		final JButton exit = new JButton("Disconnect");
		Color red = new Color(250, 75, 70);
		
		f.setSize(550,400);
		f.setVisible(true);
		f.setLayout(null);
		f.setTitle("FILE TRANSFER APPLICATION");
		
		ip.setToolTipText("127.0.0.1");
		port.setToolTipText("9999");
		exit.setBackground(red);
		log.setEditable(true);
		
		ipL.setBounds(20, 20, 100, 10);
		portL.setBounds(250, 20, 100, 10);
		ip.setBounds(120, 16, 100, 20);
		port.setBounds(320, 16, 52, 20);
		connect.setBounds(400, 13, 100, 25);
		fileL.setBounds(20, 60, 100, 10);
		file.setBounds(120, 56, 252, 20);
		download.setBounds(400, 52, 100, 25);
		logL.setBounds(20,90, 100, 13);
		log.setBounds(20,110,500,210);
		exit.setBounds(420,327,100,25);
		
		
		f.add(ipL);f.add(portL);f.add(ip);f.add(port);
		f.add(connect);f.add(fileL);f.add(file);f.add(download);
		f.add(logL);f.add(log);f.add(exit);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		log.setEditable(true);
		
		//When connect button is pressed
		connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//assign port and ip address and check for connection
				String ip_addr = ip.getText();
				int PORT = Integer.parseInt(port.getText());
				c = new Client(ip_addr, PORT);
				String msg = c.validConnection();
				if (msg.equalsIgnoreCase("Failed")) {
					log.setText("Failed to Connect to server");
					ip.setText(null);
					port.setText(null);
				}
				connect.setText("Connected");
				ip.setEditable(false);
				port.setEditable(false);
				
				//When Disconnect button is pressed
				exit.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String msg = c.validConnection();
						if (msg.compareToIgnoreCase("Connected") == 0) {
							String disconnect = "Exit";
							c.exitSocket(disconnect);
							connect.setText("Connect");
							ip.setText(null);
							log.setText(null);
							port.setText(null);
							file.setText(null);
							ip.setEditable(true);
							port.setEditable(true);
						}
					}
				});
			}
			
		});
		
		//When download button is pressed
		download.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent a) {
				String filename = file.getText();
				String msg = c.sendFilename(filename);
				if (msg.compareToIgnoreCase("File does not exist at Server") == 0) {
					log.setText("\n\n" + "  " + filename + "  " + msg);
					file.setText(null);
				}
				if (msg.compareToIgnoreCase("sending") == 0) {
					file.setEditable(false);
					file.setText(null);
					msg = c.recieveFile();
					if (msg.compareToIgnoreCase("file downloaded") == 0) {
						file.setEditable(true);
						log.setText("\n\n" + "  " + filename + "  " + msg);
					}
				}
			}
		});
		
		
		
	}
}
