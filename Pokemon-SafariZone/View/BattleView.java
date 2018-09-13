package View;

import java.util.concurrent.TimeUnit;

import Controller.PokemonController;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.Pokemon.Pokemon;
import model.Trainer.Trainer;

public class BattleView extends BorderPane {
	
	GraphicsContext pokemonView;
	Canvas canvasMain = new Canvas(800, 500);
	Canvas canvasAnimate = new Canvas(800,500);
	Canvas canvasAnimate2 = new Canvas(800, 500);
	Trainer trainer;
	Trainer npc;
	PokemonController cap;
	Pokemon wild;
	ImageView img;
	ImageView img2;
	Image pokemonImage;
	Image enemyPokemonImage;
	Image bossImage;
	Image backgroundImage;
	Image trainerImage;
	
	public BattleView(Trainer trainer, Trainer npc, PokemonController pokemonController) {
		this.trainer = trainer;
		this.npc = npc;
		cap = pokemonController;
		this.wild = wild;
		pokemonView = canvasMain.getGraphicsContext2D();
		pokemonView.setFill(Color.GREENYELLOW);
		backgroundImage = new Image("File:Images/background.png");
		pokemonView.drawImage(backgroundImage, 0, 0);
		pokemonView.fillRect(100, 0, 400, 30);
		pokemonView.fillRect(350, 450, 400, 30);
		pokemonView.setFill(Color.BLACK);
		pokemonView.fillText("Health Points", 200, 28);
		pokemonView.fillText("Health Points", 450, 462);
		BattleButtonView buttonView = new BattleButtonView(trainer, pokemonController, npc.getActivePokemon());
		this.setBottom(buttonView);
		
		animate();
		
	}
	
	public void animate() {
			
			GraphicsContext gc = canvasAnimate.getGraphicsContext2D();
			GraphicsContext gc2 = canvasAnimate2.getGraphicsContext2D();
			gc.setFill(Color.BLUE);
			gc2.setFill(Color.BLUE);
			bossImage = new Image("File:Images/boss.png");
			trainerImage = new Image("File:Images/trainer2.png");
			img2 = new ImageView(trainerImage);
			img = new ImageView(bossImage);
			img.setFitWidth(125);
			img.setPreserveRatio(true);
			gc.drawImage(bossImage, 550, 100);
			gc2.drawImage(trainerImage, 60, 355, 74, 125);
			TranslateTransition tt2 = new TranslateTransition(Duration.millis(3500), img2);
			tt2.setFromX(60);
			tt2.setFromY(330);
			tt2.setByX(0);
			tt2.setByY(0);
			tt2.setCycleCount(1);
			tt2.play();
			tt2.setOnFinished( e -> {
				setPokemon();
			});
			TranslateTransition tt = new TranslateTransition(Duration.millis(3500), img);
			tt.setFromX(550);
			tt.setFromY(100);
			tt.setByX(0);
			tt.setByY(0);
			tt.setCycleCount(1);
			tt.play();
			
			Pane root = new Pane(canvasMain, img, img2);
			
			this.setCenter(root);
			
	}

	private void setPokemon() {
		GraphicsContext gc = canvasAnimate.getGraphicsContext2D();
		GraphicsContext gc2 = canvasAnimate2.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc2.setFill(Color.BLUE);
		bossImage = new Image("File:Images/boss.png");
		trainerImage = new Image("File:Images/trainer2.png");
		img2 = new ImageView(trainerImage);
		img = new ImageView(bossImage);
		img.setFitWidth(125);
		img.setPreserveRatio(true);
		gc.drawImage(bossImage, 550, 100);	
		gc2.drawImage(trainerImage, 60, 355, 74, 125);
		TranslateTransition tt2 = new TranslateTransition(Duration.millis(1000), img2);
		tt2.setFromX(60);
		tt2.setFromY(330);
		tt2.setByX(-1000);
		tt2.setByY(0);
		tt2.setCycleCount(1);
		tt2.play();
		TranslateTransition tt = new TranslateTransition(Duration.millis(1000), img);
		tt.setFromX(550);
		tt.setFromY(100);
		tt.setByX(800);
		tt.setByY(0);
		tt.setCycleCount(1);
		tt.play();
		tt.setOnFinished( e -> {
			flyPokemonIn();
		});
		
		Pane root = new Pane(canvasMain, img, img2);
		
		this.setCenter(root);
		
	}

	private void flyPokemonIn() {
		GraphicsContext gc = canvasAnimate.getGraphicsContext2D();
		GraphicsContext gc2 = canvasAnimate2.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc2.setFill(Color.BLUE);
		if(trainer.getActivePokemon().getName().equals("Charmander")) {
			pokemonImage = new Image("File:Images/Pokemon/Charmander_Back.gif");
		}
		else {
			pokemonImage = new Image("File:Images/Pokemon/" + (trainer.getActivePokemon().getName() + "Back.png"));
		}
		enemyPokemonImage = new Image("File:Images/Pokemon/Dragonite" + ".gif");
		img2 = new ImageView(pokemonImage);
		img = new ImageView(enemyPokemonImage);
		
		gc.drawImage(enemyPokemonImage, 1500, 100);
		gc2.drawImage(pokemonImage, -1000, 340);
		TranslateTransition tt2 = new TranslateTransition(Duration.millis(1000), img2);
		tt2.setFromX(-1000);
		tt2.setFromY(340);
		tt2.setByX(1060);
		tt2.setByY(0);
		tt2.setCycleCount(1);
		tt2.play();
		TranslateTransition tt = new TranslateTransition(Duration.millis(1000), img);
		tt.setFromX(1500);
		tt.setFromY(100);
		tt.setByX(-900);
		tt.setByY(0);
		tt.setCycleCount(1);
		tt.play();
		
		Pane root = new Pane(canvasMain, img, img2);
		
		this.setCenter(root);
		
	}
	public class BattleButtonView extends BorderPane {
		
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
		
		public BattleButtonView(Trainer trainer /*PokemonController control*/, PokemonController pokemonController, Pokemon wild) {
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
			buttons[0][0].setText("Attack");
			buttons[0][1].setText("Raise Defense");
			buttons[1][0].setText("Raise Attack");
			buttons[1][1].setText("Lower Enemy Defense");
			buttonView.add(buttons[0][0], 1, 1);
			buttonView.add(buttons[0][1], 0, 1);
			buttonView.add(buttons[1][0], 0, 2);
			buttonView.add(buttons[1][1], 1, 2);
			this.setBottom(buttonView);
		}
	private class ButtonListener implements EventHandler<ActionEvent> {
			int rand;
			@Override
			public void handle(ActionEvent e) {
				String text = ((Button) e.getSource()).getText();
				if (text.equals("Attack")) {
					
					trainer.getActivePokemon().useAbility(0, wild);
//					System.out.println("Trainer used Attack!");
					if(wild.getHp() <= 0) {
						System.out.println("Boss Nick was Defeated!");
						cap.playSound(mpCatch);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						cap.setToMapScene();
					}
					else {
						rand = 0 + (int)(Math.random() * ((3-0) + 1));
						if(rand == 1 || rand == 2) {
							wild.useAbility(rand, wild);
						}
						else {
							wild.useAbility(rand, trainer.getActivePokemon());
						}
						if(trainer.getActivePokemon().getHp() <= 0) {
							System.out.println("Boss Nick has Defeated Trainer!");
							cap.playSound(mpRunAway);
							try {
								TimeUnit.MILLISECONDS.sleep(1000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							cap.setToMapScene();
						}
					}
					
				}
				else if (text.equals("Raise Defense")) {
					
					trainer.getActivePokemon().useAbility(1, trainer.getActivePokemon());
//					System.out.println("Trainer Raised Defense!");
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					rand = 0 + (int)(Math.random() * ((3-0) + 1));
					if(rand == 1 || rand == 2) {
						wild.useAbility(rand, wild);
					}
					else {
						wild.useAbility(rand, trainer.getActivePokemon());
					}
					if(trainer.getActivePokemon().getHp() <= 0) {
						System.out.println("Boss Nick has Defeated Trainer!");
						cap.playSound(mpRunAway);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						cap.setToMapScene();
				}
				}
				else if (text.equals("Raise Attack")) {
					
					trainer.getActivePokemon().useAbility(2, trainer.getActivePokemon());
//					System.out.println("Trainer Raised Attack!");
					
					trainer.getActivePokemon().useAbility(1, trainer.getActivePokemon());
//					System.out.println("Trainer Raised Defense!");
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					rand = 0 + (int)(Math.random() * ((3-0) + 1));
					if(rand == 1 || rand == 2) {
						wild.useAbility(rand, wild);
					}
					else {
						wild.useAbility(rand, trainer.getActivePokemon());
					}
					if(trainer.getActivePokemon().getHp() <= 0) {
						System.out.println("Boss Nick has Defeated Trainer!");
						cap.playSound(mpRunAway);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						cap.setToMapScene();
					
				}
				}
				else if (text.equals("Lower Enemy Defense")) {
					trainer.getActivePokemon().useAbility(3, wild);
//					System.out.println("Trainer Lowered Enemy Defense!");
					
					trainer.getActivePokemon().useAbility(1, trainer.getActivePokemon());
//					System.out.println("Trainer Raised Defense!");
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					rand = 0 + (int)(Math.random() * ((3-0) + 1));
					if(rand == 1 || rand == 2) {
						wild.useAbility(rand, wild);
					}
					else {
						wild.useAbility(rand, trainer.getActivePokemon());
					}
					if(trainer.getActivePokemon().getHp() <= 0) {
						System.out.println("Boss Nick has Defeated Trainer!");
						cap.playSound(mpRunAway);
						try {
							TimeUnit.MILLISECONDS.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						cap.setToMapScene();
				}
				}
			}
		}
	}
}
