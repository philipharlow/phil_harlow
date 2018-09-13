package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Vector;


import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.Owner;


public class ICritterServer extends Application implements Serializable {
	
	public static int portNum;
	private static ServerSocket server;
	public static Owner ownerMaster;
	private Label label;
	private static List<ObjectOutputStream> clientList = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		ownerMaster = new Owner("Ash", "Pikachu");
		
		
		TextInputDialog d = new TextInputDialog();
		d.setTitle("Port Number");
		d.setHeaderText("Please input port number");
		d.setContentText("Port:");

		Optional<String> result = d.showAndWait();
		portNum = Integer.parseInt(result.get());

		if (result.isPresent()) {
			try {
				server = new ServerSocket(portNum);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println("ICritter server started on port num " + portNum);
		}
		while (true) {
			try {
				Socket client = server.accept();
				System.out.println("Adding Client");
				// Make both connection streams available
				ObjectOutputStream outputToClient = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream inputFromClient = new ObjectInputStream(client.getInputStream());
				clientList.add(outputToClient);
				outputToClient.writeObject(ownerMaster);
				ClientHandler handler = new ClientHandler(inputFromClient,
						clientList);
				handler.start();

			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("did not add client");
			}
		}
		
	}
}

//A class that handles the state of each client.
//Has duty of writing out to each client and closing dead clients
class ClientHandler extends Thread {
	private ObjectInputStream inputStream;
	private List<ObjectOutputStream> clients;

	public ClientHandler(ObjectInputStream inputStream,
			List<ObjectOutputStream> clientList) {
		this.clients = clientList;
		this.inputStream = inputStream;

	}

	@Override
	// Start a new thread
	// Read from the client update the server and write back out
	public void run() {
		while (true) {
			try {
				ICritterServer.ownerMaster = (Owner) inputStream.readObject();
			} catch (IOException e) {
				this.cleanUp();
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				this.cleanUp();
				return;
			}

			this.writeOwnerToClients(ICritterServer.ownerMaster);
		}
	}

	// Write out to every client
	// if a client is done remove the client from the list and don't write to it
	// anymore.
	private void writeOwnerToClients(Owner owner) {
		synchronized (clients) {
			ObjectOutputStream toRemove = null;

			for (ObjectOutputStream client : clients) {
				try {

					client.writeObject(owner);
					client.reset();

				} catch (IOException e) {
					toRemove = client;

				}
			}
			clients.remove(toRemove);

		}
	}

	// Close the input stream
	private void cleanUp() {
		try {
			this.inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




