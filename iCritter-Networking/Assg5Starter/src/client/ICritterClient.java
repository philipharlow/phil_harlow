package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;
import java.util.Vector;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Owner;
import server.ICritterServer;
import view.ICritterView;


public class ICritterClient extends Application {
	private Owner clientOwner;

	private Socket socket;
	private int port;
	private static String server;
	public static ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	//GUI variables
	public static final int width = 1100;
	public static final int height = 600;
	private BorderPane borderPane;
	private ICritterView view;

	public ICritterClient() {
		this.clientOwner = new Owner("clientOwn", "clientCrit");
		this.socket = null;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// ask user for port number
		TextInputDialog getPort = new TextInputDialog();
		getPort.setTitle("Port Number");
		getPort.setHeaderText("Please input port number");
		getPort.setContentText("Port:");
		Optional<String> portResult = getPort.showAndWait();
		port = Integer.parseInt(portResult.get());

		connect();
		
		stage.setTitle("ICritter GUI");
		borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, width, height);
		//Pablo the penguin
		view = new ICritterView(clientOwner, outputStream);
		borderPane.setCenter((Node) view);
		stage.setScene(scene);
		stage.show();
	}

	public void connect() {

		try {
			socket = new Socket("localhost", port);
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.flush();
			inputStream = new ObjectInputStream(socket.getInputStream());
			System.out.println("Connected to server at localhost: " + port);
		} catch (IOException e) {
			e.printStackTrace();
			this.cleanUpAndQuit("Couldn't connect to the server");
		}
		ServerListener serverListener = new ServerListener();
		serverListener.start();
	}

	// Closes the sockets and Error Checks
	private void cleanUpAndQuit(String message) {
		try {
			if (socket != null)
				socket.close();
		} catch (IOException e) {
			// Couldn't close the socket, we are in deep trouble. Abandon ship.
			e.printStackTrace();
		}
	}
	
	private class ServerListener extends Thread {
		public void run() {
			try {
				while (true) {
					Owner owner = (Owner) inputStream.readObject();
					clientOwner = owner;
					view = new ICritterView(clientOwner, outputStream);
					Platform.runLater(() -> borderPane.setCenter((Node) view));
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean sendOwner(Owner owner) {
		try {
			outputStream.writeObject(owner);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
}


