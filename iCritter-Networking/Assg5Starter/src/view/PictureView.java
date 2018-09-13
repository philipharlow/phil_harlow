package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
//Written by Nick Forbes
public class PictureView extends BorderPane {
	public PictureView() {
		GridPane pane = new GridPane();
		Image penguinImage = new Image("file:/cs335_assg5_complete 4/images/funny-pictures-penguins-wonder-about-their-offspring.jpg", false);
		pane.add(new ImageView(penguinImage),0,0);
		this.setCenter(pane);
	}
	
}
