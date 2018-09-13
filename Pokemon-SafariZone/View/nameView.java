package View;

import java.util.Optional;

import Controller.PokemonController;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

public class nameView extends BorderPane{

	private PokemonController controller;
	
	public nameView(PokemonController controller, String load) {
		TextInputDialog nameBox = new TextInputDialog();
		
		if(load == "load") {
			nameBox.setContentText("Enter the name of the trainer you wish to load");
		}
		else {
			nameBox.setContentText("Enter your trainer's name");
		}
		
		Optional<String> name = nameBox.showAndWait();
		
		if(name.isPresent()) {
			controller.setToMapSceneInit(name.get());
		}
		
	}
}
