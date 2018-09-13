package view;



import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.ICritterMemoryEvent;
import model.Owner;
import model.Treat;
//Written by Nick Forbes
public class ICritterView extends BorderPane {
	public ICritterView(Owner owner, ObjectOutputStream outputStream) {
		Text header = new Text(0,0, "Owner: " + owner.getName()+"\t\tCritter: " + owner.getCritter().getName());
		BorderPane.setAlignment(header, Pos.TOP_LEFT);
		this.setTop(header);
		MemoryView memView = new MemoryView(owner);
		this.setRight(memView);
		Image penguinImage = new Image("images/funny-pictures-penguins-wonder-about-their-offspring.jpg");
		this.setCenter(new ImageView(penguinImage));
		owner.addObserver(memView);
		
		TreatView treatView = new TreatView(owner, outputStream);
		owner.addObserver(treatView);
		
		this.setBottom(treatView); 


	}
}
