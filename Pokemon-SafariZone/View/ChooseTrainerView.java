package View;

import java.util.Optional;

//import com.sun.prism.image.ViewPort;

import Controller.PokemonController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ChooseTrainerView extends BorderPane{
	
	private String trainerName;
	private String trainerGender;
	private GridPane panel;
	private GridPane loadPanel;
	private Button maleTrainer;
	private Button femaleTrainer;
	private Button loadButton;
	private PokemonController controller;
	
	public ChooseTrainerView(PokemonController controller) {
		this.controller = controller;
		panel = new GridPane();
		panel.setPadding(new Insets(30,30,30,210));
		panel.setVgap(50);
		
		loadPanel = new GridPane();
		loadPanel.setPadding(new Insets(30,30,30,30));
		loadPanel.setVgap(50);
		
		Image maleImage = new Image("Images/maleTrainer.png");
		ImageView male = new ImageView(maleImage);
		male.setFitHeight(300);
		male.setFitWidth(200);
		//male.setPreserveRatio(true);
		
		Image femaleImage = new Image("Images/femaleTrainer.png");
		ImageView female = new ImageView(femaleImage);
		female.setFitHeight(300);
		female.setPreserveRatio(true);
		
		ButtonListener buttonListener = new ButtonListener();
		
		maleTrainer = new Button();
		maleTrainer.setGraphic(male);
		maleTrainer.setOnAction(buttonListener);
		
		femaleTrainer = new Button();
		femaleTrainer.setPrefHeight(300);
		femaleTrainer.setPrefWidth(200);
		femaleTrainer.setGraphic(female);
		femaleTrainer.setOnAction(buttonListener);
		
		Text t = new Text();
		t.setText("Choose your trainer");
		t.setFont(Font.font(20));
		
		panel.add(maleTrainer, 0, 1);
		panel.add(femaleTrainer, 2, 1);
		panel.add(t, 1, 2);
		
		
		loadButton = new Button();
		loadButton.setText("Load Game");
		loadButton.setOnAction(buttonListener);
		
		loadPanel.add(loadButton, 0, 0);
		
		this.setCenter(panel);
		this.setBottom(loadPanel);
	}
	
	private class ButtonListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent e) {
			
			if(e.getSource() == maleTrainer) {
				trainerGender = "male";
				controller.setSceneToAskName("");
			}
			
			if(e.getSource() == femaleTrainer) {
				trainerGender = "female";
				controller.setSceneToAskName("");
			}
			
			if(e.getSource() == loadButton) {
				controller.setToMapSceneInit(true);
			}
		}
		
	}
	
	public String getTrainerName() {
		return trainerName;
	}
	
	public String getTrainerGender() {
		return trainerGender;
	}
	
}
