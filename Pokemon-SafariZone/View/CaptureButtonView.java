package View;

import java.util.concurrent.TimeUnit;

import Controller.PokemonController;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.Pokemon.Pokemon;
import model.Trainer.Trainer;



public class CaptureButtonView extends BorderPane{
	private static final int HGAP = 2;
	private static final int VGAP = 2;
	private static final int BUTTON_WIDTH = 397;
	private static final int BUTTON_HEIGHT = 90;
	
	GridPane buttonView;
	private Trainer trainer;
	Pokemon wild;
	PokemonController cap;
	private MediaPlayer mpCatch;
	private MediaPlayer mpRunAway;
	
	public CaptureButtonView(Trainer trainer /*PokemonController control*/, PokemonController pokemonController, Pokemon wild) {
		this.trainer = trainer;
		this.cap = pokemonController;
		final String resource = getClass().getResource("/Sounds/captureSound.wav").toString();
		final Media media = new Media(resource);
	    mpCatch = new MediaPlayer(media);
	    final String resource2 = getClass().getResource("/Sounds/ranAway.wav").toString();
		final Media media2 = new Media(resource2);
	    mpRunAway = new MediaPlayer(media2);
	    
	    //this.control = control;
		this.wild = wild;
		buttonView = new GridPane();
		buttonView.setHgap(HGAP);
		buttonView.setVgap(VGAP);
		Font buttonFont = new Font("Arial", 12);
		Button[][] buttons = new Button[2][2];
		ButtonListener buttonListener = new ButtonListener();
		for(int row=0; row<2; row++) {
			for(int col=0; col<2; col++) {
				buttons[row][col] = new Button();
				buttons[row][col].setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
				buttons[row][col].setFont(buttonFont);
				buttons[row][col].setOnAction(buttonListener);
			}
		}
		buttons[0][0].setText("Throw Bait");
		buttons[0][1].setText("Throw Safari Ball");
		buttons[1][0].setText("Throw Rock");
		buttons[1][1].setText("Run Away");
		buttonView.add(buttons[0][0], 1, 1);
		buttonView.add(buttons[0][1], 0, 1);
		buttonView.add(buttons[1][0], 0, 2);
		buttonView.add(buttons[1][1], 1, 2);
		this.setBottom(buttonView);
	}
private class ButtonListener implements EventHandler<ActionEvent> {
		GraphicsContext pokemonView;
		@Override
		public void handle(ActionEvent e) {
			String text = ((Button) e.getSource()).getText();
			if (text.equals("Throw Bait")) {
				
				Image ball = new Image("File:Images/bait.png");
				throwStuff(ball);
				
			}
			else if (text.equals("Throw Safari Ball")) {
				
				Image ball = new Image("File:Images/pokeball.png");
				throwStuff(ball);
			}
			else if (text.equals("Throw Rock")) {
				
				Image ball = new Image("Images/rock.png");
				throwStuff(ball);
				
			}
			else if (text.equals("Run Away")) {
				cap.setToMapScene();
			}
		}
		private void throwStuff(Image item) {
			trainer.throwRock(wild);
			Canvas canvasAnimate = new Canvas(800, 500);
			Canvas canvasMain = new Canvas(800, 500);
			pokemonView = canvasMain .getGraphicsContext2D();
			Image pokemonImage = new Image("Images/Pokemon/" + (wild.getName()) + ".png");
			//Image pokemonImage = new Image("File:Images/Pokemon/Growlithe.gif");
			Image backgroundImage = new Image("Images/background.png");
			Image trainerImage = new Image("Images/trainer2.png");
			pokemonView.drawImage(backgroundImage, 0, 0);
			pokemonView.drawImage(pokemonImage, 600, 180);
			//pokemonView.drawImage(trainerImage, 50, 350);
			pokemonView.drawImage(trainerImage, 60, 355, 74, 125);
			GraphicsContext gc = canvasAnimate.getGraphicsContext2D();
			Image ball = item;
			gc.drawImage(ball, -20, 350);
			ImageView img = new ImageView(ball);
			Pane root = new Pane(canvasAnimate);
			TranslateTransition tt = new TranslateTransition(Duration.millis(1000), img);
			tt.setFromX(-20);
			tt.setFromY(350);
			tt.setByX(670);
			tt.setByY(-120);
			tt.setCycleCount(1);
			// Set what to do after animation
			if(item.getHeight() == 24) {  // 24 is the height of pokeball.png
				tt.setOnFinished(e -> {
					if(trainer.throwPokeball(wild)) {
						System.out.println(wild.getName() + " was caught!");
					    cap.playSound(mpCatch);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						cap.setToMapScene();
					}
					else {
						if(trainer.getSafariBalls().size() == 0) {
							System.out.println("You ran out of SafariBalls!");
							cap.setSceneToEnd();    // Change to end screen once implemented
						}
					}
				});
			}
			
			else if(item.getHeight() == 128) {  // 128 is the height of rock.png
				tt.setOnFinished(e -> {
					if(!trainer.throwRock(wild)) {
						System.out.println(wild.getName() + " ran away!");
						cap.playSound(mpRunAway);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						cap.setToMapScene();
					}
				});
			}
			tt.play();
			Pane noBall = new Pane(canvasMain);
			Pane main = new Pane(canvasMain, img);
			BorderPane capture = new BorderPane();
			capture.setCenter(main);
			
			//cap.setCenter(main);
			CaptureButtonView buttonView = new CaptureButtonView(trainer, cap, wild);
			capture.setBottom(buttonView);
			cap.setSceneToCapture(capture);
			//cap.setBottom(buttonView);
		}
	}
}

