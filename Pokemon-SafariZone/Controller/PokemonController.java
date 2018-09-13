package Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Observer;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import View.BattleView;
import View.CaptureButtonView;
import View.CaptureView;
import View.ChooseTrainerView;
import View.EndView;
import View.InventoryView;
import View.MapView;
import View.PauseView;
import View.PokemonView;
import View.StartScreenView;
import View.TrainerView;
import View.nameView;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Map.Barrier;
import model.Map.LeftMap;
import model.Map.Map;
import model.Map.Pathway;
import model.Map.RightMap;
import model.Map.ShortGrass;
import model.Map.TallGrass;
import model.Map.Wall;
import model.Pokemon.CommonPokemon;
import model.Pokemon.Pokemon;
import model.Pokemon.RarePokemon;
import model.Trainer.Trainer;

public class PokemonController<T> extends Application {
	
	private GridPane mainGrid, leftMainGrid;
	private Stage stage;
	private Scene startScene, chooseScene, nameScene, mapScene;
	private Scene pauseScene, pokemonScene, inventoryScene, captureScene;
	private Stage startStage, pauseStage, pokemonStage, inventoryStage;
	private nameView nv;
	private RightMap map;
	private LeftMap leftMap;
	private MapView mapView;
	private Trainer trainer;
	private PauseView pauseView;
	private PokemonView pokemonView;
	private CaptureView captureView;
	private StartScreenView startView;
	private InventoryView inventoryView;
	private ChooseTrainerView chooseView;
	private boolean moveNorth, moveSouth, moveEast, moveWest;
	private Group pokeMap;
	private MediaPlayer mpMap, mpBattle, mpPause, bump;
	private ImageView player;
	private boolean onLeftMap, gotKey;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle("Be the best that there ever was!");
		
		startView = new StartScreenView(this);
		startStage = new Stage();
		startScene = new Scene(startView, 1067, 600);
		
		startStage.setScene(startScene);
		startStage.show();
		startScene.setOnKeyPressed(event -> {
			switch(event.getCode()){
			case SPACE:
				setSceneToGetTrainer();
			}
		});
		
		BackgroundImage grassyField = new BackgroundImage(new Image("File:images/grassBackgnd.png"), null,null,null,null);
		
		mainGrid = new GridPane();
		mainGrid.setBackground(new Background(grassyField));
		leftMainGrid = new GridPane();
		leftMainGrid.setBackground(new Background(grassyField));
		
		//make starter map and left map
		map = new RightMap("map", false);
		leftMap = new LeftMap("map", false);
		//put map into the mapview...which sets up a gridpane
		mapView = new MapView(map);

		//Rightmap is now added to the gird
		mainGrid.add(mapView.getGetGridPane(), 0, 0);
		
		final String resource = getClass().getResource("/Sounds/music.mp3").toString();
		final Media media = new Media(resource);
	    mpMap = new MediaPlayer(media);
	    mpMap.setCycleCount(10);
	    mpMap.play();
	    
	    final String resource2 = getClass().getResource("/Sounds/battleMusic.mp3").toString();
		final Media media2 = new Media(resource2);
	    mpBattle = new MediaPlayer(media2);
	    
	    final String resource3 = getClass().getResource("/Sounds/pause.wav").toString();
		final Media media3 = new Media(resource3);
	    mpPause = new MediaPlayer(media3);
	    
	    final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
		final Media media4 = new Media(resource4);
	    bump = new MediaPlayer(media4);
	}

	private void setSceneToGetTrainer() {
		chooseView = new ChooseTrainerView(this);
		chooseScene = new Scene(chooseView, 1067, 600);
		startStage.setScene(chooseScene);
		
	}
	
	public void setSceneToAskName(String load) {
		nv = new nameView(this, load);
		nameScene = new Scene(nv);
		startStage.setScene(nameScene);
		
	}
	
	public void setToMapSceneInit(String name) {
		trainer = new Trainer(name, map, chooseView.getTrainerGender());
		
		pokeMap = new Group();///////////////////////////
		TrainerView trainerView = new TrainerView(trainer);///////////////
		player = trainerView.getTrainerImageView();/////////////////
		
		pokeMap.getChildren().add(mainGrid);////////////////////////
		pokeMap.getChildren().add(player);/////////////////
		
		//Play with the scene size;1250 worked good
		mapScene = new Scene(pokeMap,1000,1000);
		
		onLeftMap = false;
		gotKey = false;
		
		mapScene.setOnKeyReleased(new EventHandler<KeyEvent>() { //check for a key to be pressed and released
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case W: moveNorth = true; 
						if(trainer.moveUp()) {moveTrainerTo(0, -20, player, trainerView);}
						else{
							moveNorth = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						} break;
						
					case UP: moveNorth = true; 
						if(trainer.moveUp()) {moveTrainerTo(0, -20, player, trainerView);} 
						else{
							moveNorth = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						} break;
						
					case S: moveSouth = true;
						if(trainer.moveDown()) {moveTrainerTo(0, 20, player, trainerView);} 
						else {
							moveSouth = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						}break;
					case DOWN: moveSouth = true; if(trainer.moveDown()) {moveTrainerTo(0, 20, player, trainerView);} 
						else {
							moveSouth = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						}break;
					
					case A: moveWest = true;  if(trainer.moveLeft()) {moveTrainerTo(-20, 0, player, trainerView);}
						else {
							moveWest = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
							bump = new MediaPlayer(media4);
							bump.play();
						} break;
					case LEFT: moveWest = true;  if(trainer.moveLeft()) {moveTrainerTo(-20, 0, player, trainerView);} 
						else {
							moveWest = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
							bump = new MediaPlayer(media4);
							bump.play();
						}break;
					
					case D: moveEast = true; if(trainer.moveRight()) {moveTrainerTo(20, 0, player, trainerView);} 
						else {
							moveEast = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						}break;
					
					case RIGHT: moveEast = true; if(trainer.moveRight()) {moveTrainerTo(20, 0, player, trainerView);} 
						else {
							moveEast = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
					    	bump = new MediaPlayer(media4);
					    	bump.play();
						}break;
				}
			}
		});
		
		mapScene.setOnKeyPressed(
				event -> {
					switch(event.getCode()) {
					case P:
						mpMap.pause();
						mpPause.seek(Duration.ZERO);
						mpPause.play();
						setSceneToPause();
						break;
						
					case SPACE:
						mpMap.pause();
						mpPause.seek(Duration.ZERO);
						mpPause.play();
						setSceneToPause();
						break;
					}
						
				});
		
		//3 is the farthest left it should go --- -5 farthest right it should go
		//20 px across
		//20 px up and down
		player.relocate(trainer.getXPos() * 20, trainer.getYPos() * 19.6);//starting position
		
		startStage.hide();
		setToMapScene();
		
	}
	
	public void setToMapSceneInit(boolean load) {
		String filepath = "Images/Pokemon/save";
		
		try {
			 FileInputStream fileIn = new FileInputStream(filepath);
			 ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			 Object obj = objectIn.readObject();
			 System.out.println("Game Loaded");
			 objectIn.close();
			 
			 trainer = (Trainer) obj;
			 
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		pokeMap = new Group();///////////////////////////
		TrainerView trainerView = new TrainerView(trainer);///////////////
		player = trainerView.getTrainerImageView();/////////////////
		
		onLeftMap = trainer.getOnLeftMap();
		gotKey = false;
		
		pokeMap.getChildren().add(mainGrid);////////////////////////
		pokeMap.getChildren().add(player);/////////////////
		
		//Play with the scene size;1250 worked good
		mapScene = new Scene(pokeMap,1000,1000);
		
		if(onLeftMap) {
			player.setX(trainer.getXPos()*20);
			player.setY(trainer.getYPos()*19.6);
			mapLeftTransition();
		}
		
		mapScene.setOnKeyReleased(new EventHandler<KeyEvent>() { //check for a key to be pressed and released
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case W: moveNorth = true; 
						if(trainer.moveUp()) {moveTrainerTo(0, -20, player, trainerView);}
						else{
							moveNorth = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						} break;
						
					case UP: moveNorth = true; 
						if(trainer.moveUp()) {moveTrainerTo(0, -20, player, trainerView);} 
						else{
							moveNorth = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						} break;
						
					case S: moveSouth = true;
						if(trainer.moveDown()) {moveTrainerTo(0, 20, player, trainerView);} 
						else {
							moveSouth = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						}break;
					case DOWN: moveSouth = true; if(trainer.moveDown()) {moveTrainerTo(0, 20, player, trainerView);} 
						else {
							moveSouth = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						}break;
					
					case A: moveWest = true;  if(trainer.moveLeft()) {moveTrainerTo(-20, 0, player, trainerView);}
						else {
							moveWest = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
							bump = new MediaPlayer(media4);
							bump.play();
						} break;
					case LEFT: moveWest = true;  if(trainer.moveLeft()) {moveTrainerTo(-20, 0, player, trainerView);} 
						else {
							moveWest = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
							bump = new MediaPlayer(media4);
							bump.play();
						}break;
					
					case D: moveEast = true; if(trainer.moveRight()) {moveTrainerTo(20, 0, player, trainerView);} 
						else {
							moveEast = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
						    bump = new MediaPlayer(media4);
							bump.play();
						}break;
					
					case RIGHT: moveEast = true; if(trainer.moveRight()) {moveTrainerTo(20, 0, player, trainerView);} 
						else {
							moveEast = false;
							final String resource4 = getClass().getResource("/Sounds/WallBump.mp3").toString();
							final Media media4 = new Media(resource4);
					    	bump = new MediaPlayer(media4);
					    	bump.play();
						}break;
				}
			}
		});
		
		mapScene.setOnKeyPressed(
				event -> {
					switch(event.getCode()) {
					case P:
						mpMap.pause();
						mpPause.seek(Duration.ZERO);
						mpPause.play();
						setSceneToPause();
						break;
						
					case SPACE:
						mpMap.pause();
						mpPause.seek(Duration.ZERO);
						mpPause.play();
						setSceneToPause();
						break;
					}
						
				});
		
		//3 is the farthest left it should go --- -5 farthest right it should go
		//20 px across
		//20 px up and down
		player.relocate(trainer.getXPos() *20, trainer.getYPos() * 19.6);//starting position
		
		startStage.hide();
		setToMapScene();
		
	}

	/**
	 * Set the scene back to the map scene
	 */
	public void setToMapScene() {
		mpBattle.stop();
		mpMap.play();
		stage.setScene(mapScene);
		stage.show();	
	}
	
	/**
	 * <b>setSceneToPause</b>
	 * <p>
	 * Sets the scene to the pause screen
	 * 
	 */
	public void setSceneToPause() {
		pauseStage = new Stage();
		pauseView = new PauseView(trainer, this, pauseStage);
		pauseScene = new Scene(pauseView,300,500);
		pauseStage.setScene(pauseScene);
		pauseStage.setTitle("Paused");
		pauseStage.show();
	}
	
	
	/**
	 * Set the scene to the pokemon management screen
	 */
	public void setSceneToPokemon() {
		pokemonStage = new Stage();
		pokemonStage.setTitle("Your Pokemon");
		pokemonView = new PokemonView(trainer, this, pokemonStage);
		pokemonScene = new Scene(pokemonView, 650, 650);
		pokemonStage.setScene(pokemonScene);
		pokemonStage.show();
	}
	
	public void setSceneToCapture() {
		mpMap.stop();
		mpBattle.play();
		try {
			TimeUnit.MILLISECONDS.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Pokemon p = trainer.generatePokemon();
		captureView = new CaptureView(trainer, this, p);
		captureScene = new Scene(captureView);
		stage.setScene(captureScene);
		stage.show();
	}
	
	public void setSceneToInventory() {
		inventoryStage = new Stage();
		inventoryStage.setTitle("Inventory");
		inventoryView = new InventoryView(trainer, this, inventoryStage);
		inventoryScene = new Scene(inventoryView, 700, 700);
		inventoryStage.setScene(inventoryScene);
		inventoryStage.show();
	}
	
	public void setSceneToBattle() {
		mpMap.stop();
		mpBattle.play();
		Pokemon p = trainer.generatePokemon();
		captureView = new CaptureView(trainer, this, p);
		captureScene = new Scene(captureView);
		Trainer boss = new Trainer("Nick", map, "male");
        RarePokemon dragon = new RarePokemon("Dragonite", boss, null);
        boss.addPokemon(dragon);
        boss.changeActivePokemon(dragon);
        BattleView battleView = new BattleView(trainer, boss, this);
        Scene battleScene = new Scene(battleView);
        stage.setScene(battleScene);
	}

	public void setSceneToCapture(BorderPane capture) {
		stage.setScene(new Scene(capture));
		stage.show();
	}
	
	public void setSceneToEnd() {
		EndView end = new EndView(this, trainer);
		Scene endScene = new Scene(end, 650, 650);
		stage.setScene(endScene);
		pauseStage.hide();
	}
	
	private void moveTrainerTo(int dx, int dy, ImageView player, TrainerView trainerV) {
		
		TranslateTransition translate = new TranslateTransition();
		
		trainerV.changeXCoord(dx);
		trainerV.changeYCoord(dy);
		
		if(moveNorth){ trainerV.setWalkingNorth(); moveNorth = false;}
		if(moveSouth){ trainerV.setWalkingSouth(); moveSouth = false;}
		if(moveEast){  trainerV.setWalkingEast();  moveEast = false;}
		if(moveWest){  trainerV.setWalkingWest();  moveWest = false;}
		
		trainerV.setAnimation();
		
		translate.setNode(player);
		translate.setDuration(Duration.millis(100));
		translate.setByX(dx);
		translate.setByY(dy);
		translate.play();
		
		trainerV.setAnimation();
		
		checkForPokemon();
		
		if(trainer.getXPos() == 0 && trainer.getYPos() == 25) { //if player is at map transition spot
			if(dx == -20) {
				mapLeftTransition();
				onLeftMap = true;
				
				trainerV.setAnimation();
				
				translate.setNode(player);
				translate.setDuration(Duration.millis(10));
				translate.setToX(980);
				translate.setByY(490);
				translate.play();
				trainerV.setAnimation();
				return;
			}
		}
		if(trainer.getXPos() == 49 && trainer.getYPos() == 25) { //if player is at map transition spot
			if(dx == 20) {
				mapRightTransition();
				onLeftMap = false;
				
				trainerV.setAnimation();
				
				translate.setNode(player);
				translate.setDuration(Duration.millis(10));
				translate.setToX(20);
				translate.setByY(490);
				translate.play();
				trainerV.setAnimation();
				return;
			}
		}
		
		if(trainer.getXPos() == 22 && trainer.getYPos() == 25 && !onLeftMap) {
			System.out.println("Got Key");
			gotKey = true;
		}
		
		if((trainer.getXPos() == 25 || trainer.getXPos() == 24) && trainer.getYPos() == 7 && onLeftMap && gotKey) {
			if(trainer.getPokemon().size() != 0) {
				setSceneToBattle();
			}  
		}
	}

	private void mapLeftTransition(){
		mapView = new MapView(leftMap);
		
		leftMainGrid.add(mapView.getGetGridPane(), 0, 0);
		
		pokeMap.getChildren().remove(mainGrid);
		pokeMap.getChildren().remove(player);
		
		pokeMap.getChildren().add(leftMainGrid);
		pokeMap.getChildren().add(player);
		
		trainer.changeMap(leftMap, true);
		player.relocate(trainer.getXPos() *20, trainer.getYPos() * 19.6);//starting position
	}
	private void mapRightTransition(){
		mapView = new MapView(map);
		
		pokeMap.getChildren().remove(leftMainGrid);
		pokeMap.getChildren().remove(player);
		
		pokeMap.getChildren().add(mainGrid);////////////////////////
		pokeMap.getChildren().add(player);/////////////////
		
		trainer.changeMap(map, false);
		player.relocate(trainer.getXPos() *20, trainer.getYPos() * 19.6);//starting position
	}
	
	// When the trainer walks into tall grass check for a wild pokemon
	private void checkForPokemon() {
		if(onLeftMap) {
			return;
		}
		if(map.getMapObjects()[trainer.getXPos()][trainer.getYPos()] instanceof TallGrass){
			Random rand = new Random();
			int roll = (int) rand.nextInt(100 - 1);
			
			if(roll < 15) {
				setSceneToCapture();
			}
		}
	}

	public void playSound(MediaPlayer sound) {
		mpBattle.stop();
		sound.play();
		
	}
	
	public void end() {
		stage.hide();
		return;
	}
		
}
