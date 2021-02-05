
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.*;

/**
 * A simple Swing-based client for the chat server. Graphically it is a frame
 * with a text field for entering messages and a textarea to see the whole
 * dialog.
 *
 * The client follows the Chat Protocol which is as follows. When the server
 * sends "SUBMITNAME" the client replies with the desired screen name. The
 * server will keep sending "SUBMITNAME" requests as long as the client submits
 * screen names that are already in use. When the server sends a line beginning
 * with "NAMEACCEPTED" the client is now allowed to start sending the server
 * arbitrary strings to be broadcast to all chatters connected to the server.
 * When the server sends a line beginning with "MESSAGE " then all characters
 * following this string should be displayed in its message area.
 */

/*
 * When the user login with a unique user name the system will display the logged in user name at
 * the bottom of the screen.(Usernames that contain MULTICAST and #### are prohibited since they are used to do some 
 * tasks in the system
 * When login in initially the broadcast checkbox is ticked. If the user needs to send messages to
 * single or multiple logged in users the user can untick the checkbox and select them from the logged in users list.
 * 
 */
public class ChatClient {

	BufferedReader in;
	PrintWriter out;
	JFrame frame = new JFrame("Chatter");
	JTextField textField = new JTextField(40);
	JTextArea messageArea = new JTextArea(8, 40);

	JScrollPane scrollPane = new JScrollPane();

	JLabel Label = new JLabel();
	DefaultListModel<String> model = new DefaultListModel();
	JList<String> list = new JList(model);
	JCheckBox CheckBox = new JCheckBox("Broadcast Message", true);
	JPanel bottom_panel = new JPanel();
	// TODO: Add a list box

	/**
	 * Constructs the client by laying out the GUI and registering a listener with
	 * the textfield so that pressing Return in the listener sends the textfield
	 * contents to the server. Note however that the textfield is initially NOT
	 * editable, and only becomes editable AFTER the client receives the
	 * NAMEACCEPTED message from the server.
	 */
	public ChatClient() {

		scrollPane.setPreferredSize(new Dimension(200, 50));
		scrollPane.setViewportView(list);
		// Layout GUI
		
		textField.setEditable(false);
		messageArea.setEditable(false);
		frame.getContentPane().add(scrollPane, BorderLayout.LINE_START);
		
		frame.getContentPane().add(bottom_panel,BorderLayout.PAGE_END);
		bottom_panel.add(CheckBox, BorderLayout.PAGE_START);
		bottom_panel.add(Label, BorderLayout.PAGE_END);
		
		frame.getContentPane().add(textField, BorderLayout.PAGE_START);
		
		frame.getContentPane().add(new JScrollPane(messageArea), BorderLayout.CENTER);
		
		frame.pack();

		// TODO: You may have to edit this event handler to handle point to point
		// messaging,
		// where one client can send a message to a specific client. You can add some
		// header to
		// the message to identify the recipient. You can get the receipient name from
		// the listbox.
		textField.addActionListener(new ActionListener() {
			/**
			 * Responds to pressing the enter key in the textfield by sending the contents
			 * of the text field to the server. Then clear the text area in preparation for
			 * the next message.
			 */
			public void actionPerformed(ActionEvent e) {
				// if broadcast option is selected send message to all
				// if broadcast option is not selected send the message to selected clients

				//you can use the >> pattern to send one to one messages in broadcasting mode.
				if (!CheckBox.isSelected()) {
					if (list.getSelectedValuesList().size() > 0) {
						List listNames = list.getSelectedValuesList();
						String stringNames = null;
						
			

						if (listNames.size() == 1) {
							out.println(((String) listNames.get(0)).concat(">>" + textField.getText()));
							textField.setText("");
						} else {
							stringNames = String.join("####", listNames);
							out.println("MULTICAST " + stringNames.concat(">>") + textField.getText());
							textField.setText("");
						}
					}else {
						JOptionPane.showMessageDialog(frame, "Select Client","Error",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					out.println(textField.getText());
					textField.setText("");
				}
			}
		});

	}

	/**
	 * Prompt for and return the address of the server.
	 */
	private String getServerAddress() {
		return JOptionPane.showInputDialog(frame, "Enter IP Address of the Server:", "Welcome to the Chatter",
				JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * Prompt for and return the desired screen name.
	 */
	private String getName() {
		String name;
		while (true) {
			name = JOptionPane.showInputDialog(frame, "Choose a screen name:", "Screen name selection",
					JOptionPane.PLAIN_MESSAGE);
			if (!name.isBlank()) {
				break;
			}
		}
		return name;
	}

	/**
	 * Connects to the server then enters the processing loop.
	 */
	private void run() throws IOException {

		// Make connection and initialize streams
		String serverAddress = getServerAddress();
		Socket socket = new Socket(serverAddress, 9001);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		// Process all messages from server, according to the protocol.

		// TODO: You may have to extend this protocol to achieve task 9 in the lab sheet
		while (true) {
			String line = in.readLine();
			if (line.startsWith("SUBMITNAME")) {
				out.println(getName());
			} else if (line.startsWith("NAMEACCEPTED")) {
				textField.setEditable(true);
				Label.setText(line.substring(8));
			} else if (line.startsWith("MESSAGE")) {
				messageArea.append(line.substring(8) + "\n");
			} else if (line.startsWith("NEWUSER")) {
				// update list function and its use is described at the end of the file.
				updateList((line.split("/////")[1]), model);
			} else if (line.startsWith("LOGGEDOUT")) {
				model.removeElement(line.split("////")[1]);
			}
		}
	}

	/**
	 * Runs the client as an application with a closeable frame.
	 */
	public static void main(String[] args) throws Exception {
		ChatClient client = new ChatClient();
		client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		client.frame.setVisible(true);
		client.run();
	}

	// these functions are used to to avoid an error
	// the Jlist glitches when elements are added continuously. so to avoid this
	// this functions make
	// the jlist wait 0.1 seconds before updating the list.
	public static void updateList(String entries, DefaultListModel model) {
		try {
			AddElement t = new AddElement(entries, model);
			t.sleep(100);
			t.stop();
		} catch (InterruptedException ex) {
			System.out.println(ex);
		}
	}

	static class AddElement extends Thread {

		public AddElement(String entries, DefaultListModel model) {
			model.addElement(entries);
		}

	}
}