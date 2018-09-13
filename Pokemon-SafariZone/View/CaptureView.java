package View;

import java.awt.Label;
import java.io.ObjectOutputStream;

import Controller.PokemonController;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Pokemon.Pokemon;
import model.Trainer.Trainer;
import View.CaptureButtonView;

public class CaptureView extends BorderPane{
	
	GraphicsContext pokemonView;
	Canvas canvasMain = new Canvas(800, 500);
	Canvas canvasAnimate = new Canvas(800,500);
	Trainer trainer;
	PokemonController cap;
	Pokemon wild;
	
	public CaptureView(Trainer trainer, PokemonController pokemonController, Pokemon wild) {
		this.trainer = trainer;
		cap = pokemonController;
		this.wild = wild;
		//Pokemon wild = trainer.generatePokemon();
		pokemonView = canvasMain.getGraphicsContext2D();
		pokemonView.setFill(Color.BLUE);
		Image pokemonImage = new Image("File:Images/Pokemon/" + (wild.getName()) + ".png");
		//Image pokemonImage = new Image("File:Images/Pokemon/Bulbasaur.gif");
		Image backgroundImage = new Image("File:Images/background.png");
		Image trainerImage = new Image("File:Images/trainer2.png");
		pokemonView.drawImage(backgroundImage, 0, 0);
		pokemonView.drawImage(pokemonImage, 600, 180);
		//pokemonView.drawImage(trainerImage, 50, 350);
		pokemonView.drawImage(trainerImage, 60, 355, 74, 125);
		CaptureButtonView buttonView = new CaptureButtonView(trainer, pokemonController, wild);
		this.setBottom(buttonView); 
		
		setCenter();
	}
	
	public void animate() {
			
			GraphicsContext gc = canvasAnimate.getGraphicsContext2D();
			Image ball = new Image("Images/pokeball.png");
			ImageView img = new ImageView(ball);
			gc.drawImage(ball, 50, 350);
			gc.clearRect(0, 0, 800, 800);
			TranslateTransition tt = new TranslateTransition(Duration.millis(2000), img);
			tt.setByX(600);
			tt.setByY(-180);
			tt.setCycleCount(1);
			tt.play();
	}

	private void setCenter() {
		Pane root = new Pane(canvasMain, canvasAnimate);
		
		this.setCenter(root);
		
	}
}
