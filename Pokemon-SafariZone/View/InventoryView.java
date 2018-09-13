package View;

import Controller.PokemonController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Pokemon.Pokemon;
import model.Trainer.Trainer;

public class InventoryView extends BorderPane{
	private static final int BUTTON_HEIGHT = 50;
	private static final int BUTTON_WIDTH = 120;
	private static final int HGAP = 20;
	private static final int VGAP = 20;
	
	private Trainer trainer;
	private PokemonController controller;
	private Stage s;
	private ListView<Pokemon> pokemonList;
	private Button backButton;
	private Button backButton2;
	private Button potionButton;
	private Button lifestoneButton;
	private Button pokeballButton;
	private Button candyButton;
	private Button berryButton;
	private Button givePotionButton;
	private Button giveBerryButton;
	private Button giveCandyButton;
	private Button giveLifestoneButton;
	private GridPane buttonPanel;
	private GridPane backPanel;
	private GridPane givePotionPanel;
	private GridPane giveBerryPanel;
	private GridPane giveCandyPanel;
	private GridPane giveLifestonePanel;
	private GridPane safariBallPanel;
	private MediaPlayer mpButton;
	private BackgroundSize size;
	private BackgroundImage background;
	
	public InventoryView(Trainer trainer, PokemonController controller, Stage s) {
		this.trainer = trainer;
		this.controller = controller;
		this.s = s;
		
		size = new BackgroundSize(650,650,false,false,false,true);
		background = new BackgroundImage(new Image("Images/Pokemon/grey.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, size);
		
		buttonPanel = new GridPane();
		buttonPanel.setHgap(HGAP);
		buttonPanel.setVgap(VGAP);
		buttonPanel.setPadding(new Insets(10, 10, 10, 10));
		buttonPanel.setBackground(new Background(background));
		
		backPanel = new GridPane();
		backPanel.setHgap(HGAP);
		backPanel.setVgap(VGAP);
		backPanel.setPadding(new Insets(10, 10, 10, 10));
		backPanel.setBackground(new Background(background));
		
		ButtonListener buttonListener = new ButtonListener();
		
		backButton = new Button();
		backButton.setText("Back");
		backButton.setPrefHeight(BUTTON_HEIGHT);
		backButton.setPrefWidth(BUTTON_WIDTH);
		backButton.setOnAction(buttonListener);
		
		potionButton = new Button();
		potionButton.setText("Potions x" + trainer.getPotions().size());
		potionButton.setPrefHeight(BUTTON_HEIGHT);
		potionButton.setPrefWidth(BUTTON_WIDTH);
		potionButton.setOnAction(buttonListener);
		
		candyButton = new Button();
		candyButton.setText("Candies x" + trainer.getCandies().size());
		candyButton.setPrefHeight(BUTTON_HEIGHT);
		candyButton.setPrefWidth(BUTTON_WIDTH);
		candyButton.setOnAction(buttonListener);
		
		pokeballButton = new Button();
		pokeballButton.setText("SafariBalls x" + trainer.getSafariBalls().size());
		pokeballButton.setPrefHeight(BUTTON_HEIGHT);
		pokeballButton.setPrefWidth(BUTTON_WIDTH);
		pokeballButton.setOnAction(buttonListener);
		
		lifestoneButton = new Button();
		lifestoneButton.setText("Lifestones x" + trainer.getLifestones().size());
		lifestoneButton.setPrefHeight(BUTTON_HEIGHT);
		lifestoneButton.setPrefWidth(BUTTON_WIDTH);
		lifestoneButton.setOnAction(buttonListener);
		
		berryButton = new Button();
		berryButton.setText("Berries x" + trainer.getBerries().size());
		berryButton.setPrefHeight(BUTTON_HEIGHT);
		berryButton.setPrefWidth(BUTTON_WIDTH);
		berryButton.setOnAction(buttonListener);
		
		
		buttonPanel.add(potionButton, 0, 0);
		buttonPanel.add(berryButton, 0, 1);
		buttonPanel.add(candyButton, 0, 2);
		buttonPanel.add(lifestoneButton, 0, 3);
		buttonPanel.add(pokeballButton, 0, 4);
		
		backPanel.add(backButton, 0, 0);
		
		this.setCenter(buttonPanel);
		this.setBottom(backPanel);
		
	}
	
	private void showButtonPanel() {
		this.getChildren().remove(givePotionPanel);
		this.getChildren().remove(giveBerryPanel);
		this.getChildren().remove(giveCandyPanel);
		this.getChildren().remove(giveLifestonePanel);
		this.getChildren().remove(safariBallPanel);
		this.setCenter(buttonPanel);
		this.setBottom(backPanel);
	}
	
	private class ButtonListener implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			final String resource = getClass().getResource("/Sounds/button.wav").toString();
			final Media media = new Media(resource);
		    mpButton = new MediaPlayer(media);
		    mpButton.play();
			
			if(e.getSource() == backButton) {
				s.hide();
				controller.setSceneToPause();
			}
			
			if(e.getSource() == potionButton) {
				setGivePotionView();
			}
			
			if(e.getSource() == berryButton) {
				setGiveBerryView();
			}
			
			if(e.getSource() == candyButton) {
				setGiveCandyView();
			}
			
			if(e.getSource() == lifestoneButton) {
				setGiveLifestoneView();
			}
			
			if(e.getSource() == pokeballButton) {
				setSafariBallView();
			}
			
			if(e.getSource() == backButton2) {
				showButtonPanel();
			}
			
			if(e.getSource() == givePotionButton) {
				Pokemon pokemon = pokemonList.getSelectionModel().getSelectedItem();
				trainer.givePotion(pokemon);
				potionButton.setText("Potions x" + trainer.getPotions().size());
			}
			
			if(e.getSource() == giveBerryButton) {
				Pokemon pokemon = pokemonList.getSelectionModel().getSelectedItem();
				trainer.giveBerry(pokemon);
				berryButton.setText("Berries x" + trainer.getBerries().size());
			}
			
			if(e.getSource() == giveCandyButton) {
				Pokemon pokemon = pokemonList.getSelectionModel().getSelectedItem();
				trainer.givecandy(pokemon);
				candyButton.setText("Candies x" + trainer.getCandies().size());
			}
			
			if(e.getSource() == giveLifestoneButton) {
				Pokemon pokemon = pokemonList.getSelectionModel().getSelectedItem();
				trainer.giveLifestone(pokemon);
				lifestoneButton.setText("Lifestones x" + trainer.getLifestones().size());
				
			}
			
		}
		
	}
	
	public ListView<Pokemon> setList(){
		ListView<Pokemon> list = new ListView<Pokemon>();
		list.setCellFactory(new Callback<ListView<Pokemon>, ListCell<Pokemon>>(){

            @Override
            public ListCell<Pokemon> call(ListView<Pokemon> p) {
                
                ListCell<Pokemon> cell = new ListCell<Pokemon>(){

                    @Override
                    protected void updateItem(Pokemon pokemon, boolean bln) {
                        super.updateItem(pokemon, bln);
                        if (pokemon != null) {
                            setText(pokemon.getName());
                        }
                    }

                };
                
                return cell;
            }

        });
		
		list.setItems(FXCollections.observableArrayList(trainer.getPokemon()));
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		return list;
		
	}
	
	private void setGivePotionView() {
		givePotionPanel = new GridPane();
		GridPane listPanel = new GridPane();
		ButtonListener buttonListener = new ButtonListener();
		pokemonList = setList();
		
		Image potion = new Image("Images/potion.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(potion);
		iv1.setFitWidth(100);
		iv1.setFitHeight(100);
		
		Label desc = new Label();
		desc.setText("Potions increase a pokemon's current hp");
		desc.setWrapText(true);
		
		givePotionPanel.setHgap(HGAP);
		givePotionPanel.setVgap(VGAP);
		givePotionPanel.setPadding(new Insets(10,10,10,10));
		givePotionPanel.setBackground(new Background(background));
		
		listPanel.setHgap(HGAP);
		listPanel.setVgap(VGAP);
		listPanel.setPadding(new Insets(10,10,10,10));
		listPanel.setBackground(new Background(background));
		
		givePotionButton = new Button();
		givePotionButton.setText("Give Potion to Pokemon");
		givePotionButton.setPrefHeight(BUTTON_HEIGHT);
		givePotionButton.setPrefWidth(200);
		givePotionButton.setOnAction(buttonListener);
		
		backButton2 = new Button();
		backButton2.setText("Back");
		backButton2.setPrefHeight(BUTTON_HEIGHT);
		backButton2.setPrefWidth(BUTTON_WIDTH);
		backButton2.setOnAction(buttonListener);
		
		givePotionPanel.add(backButton2, 0, 0);
		givePotionPanel.add(givePotionButton, 1, 0);
		
		listPanel.add(pokemonList, 0, 0);
		listPanel.add(iv1, 1, 0);
		listPanel.add(desc, 2, 0);
		
		this.getChildren().remove(buttonPanel);
		this.setBottom(givePotionPanel);
		this.setCenter(listPanel);
	}
	
	private void setGiveBerryView() {
		giveBerryPanel = new GridPane();
		GridPane listPanel = new GridPane();
		ButtonListener buttonListener = new ButtonListener();
		pokemonList = setList();
		
		Image potion = new Image("Images/berry.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(potion);
		iv1.setFitWidth(100);
		iv1.setFitHeight(100);
		
		Label desc = new Label();
		desc.setText("Berries increase a pokemon's defense stats");
		desc.setWrapText(true);
		
		giveBerryPanel.setHgap(HGAP);
		giveBerryPanel.setVgap(VGAP);
		giveBerryPanel.setPadding(new Insets(10,10,10,10));
		giveBerryPanel.setBackground(new Background(background));
		
		listPanel.setHgap(HGAP);
		listPanel.setVgap(VGAP);
		listPanel.setPadding(new Insets(10,10,10,10));
		listPanel.setBackground(new Background(background));
		
		giveBerryButton = new Button();
		giveBerryButton.setText("Give Berry to Pokemon");
		giveBerryButton.setPrefHeight(BUTTON_HEIGHT);
		giveBerryButton.setPrefWidth(200);
		giveBerryButton.setOnAction(buttonListener);
		
		backButton2 = new Button();
		backButton2.setText("Back");
		backButton2.setPrefHeight(BUTTON_HEIGHT);
		backButton2.setPrefWidth(BUTTON_WIDTH);
		backButton2.setOnAction(buttonListener);
		
		giveBerryPanel.add(backButton2, 0, 0);
		giveBerryPanel.add(giveBerryButton, 1, 0);
		
		listPanel.add(pokemonList, 0, 0);
		listPanel.add(iv1, 1, 0);
		listPanel.add(desc, 2, 0);
		
		this.getChildren().remove(buttonPanel);
		this.setBottom(giveBerryPanel);
		this.setCenter(listPanel);
	}
	
	private void setGiveCandyView() {
		giveCandyPanel = new GridPane();
		GridPane listPanel = new GridPane();
		ButtonListener buttonListener = new ButtonListener();
		pokemonList = setList();
		
		Image potion = new Image("Images/candy.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(potion);
		iv1.setFitWidth(100);
		iv1.setFitHeight(100);
		
		Label desc = new Label();
		desc.setText("Candy increase a pokemon's attack stat");
		desc.setWrapText(true);
		
		giveCandyPanel.setHgap(HGAP);
		giveCandyPanel.setVgap(VGAP);
		giveCandyPanel.setPadding(new Insets(10,10,10,10));
		giveCandyPanel.setBackground(new Background(background));
		
		listPanel.setHgap(HGAP);
		listPanel.setVgap(VGAP);
		listPanel.setPadding(new Insets(10,10,10,10));
		listPanel.setBackground(new Background(background));
		
		giveCandyButton = new Button();
		giveCandyButton.setText("Give Candy to Pokemon");
		giveCandyButton.setPrefHeight(BUTTON_HEIGHT);
		giveCandyButton.setPrefWidth(200);
		giveCandyButton.setOnAction(buttonListener);
		
		backButton2 = new Button();
		backButton2.setText("Back");
		backButton2.setPrefHeight(BUTTON_HEIGHT);
		backButton2.setPrefWidth(BUTTON_WIDTH);
		backButton2.setOnAction(buttonListener);
		
		giveCandyPanel.add(backButton2, 0, 0);
		giveCandyPanel.add(giveCandyButton, 1, 0);
		
		listPanel.add(pokemonList, 0, 0);
		listPanel.add(iv1, 1, 0);
		listPanel.add(desc, 2, 0);
		
		this.getChildren().remove(buttonPanel);
		this.setBottom(giveCandyPanel);
		this.setCenter(listPanel);
	}
	
	private void setGiveLifestoneView() {
		giveLifestonePanel = new GridPane();
		GridPane listPanel = new GridPane();
		ButtonListener buttonListener = new ButtonListener();
		pokemonList = setList();
		
		Image potion = new Image("Images/lifestone.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(potion);
		iv1.setFitWidth(100);
		iv1.setFitHeight(100);
		
		Label desc = new Label();
		desc.setText("Lifestones bring back pokemon and heal them back to half health");
		desc.setWrapText(true);
		
		giveLifestonePanel.setHgap(HGAP);
		giveLifestonePanel.setVgap(VGAP);
		giveLifestonePanel.setPadding(new Insets(10,10,10,10));
		giveLifestonePanel.setBackground(new Background(background));
		
		listPanel.setHgap(HGAP);
		listPanel.setVgap(VGAP);
		listPanel.setPadding(new Insets(10,10,10,10));
		listPanel.setBackground(new Background(background));
		
		giveLifestoneButton = new Button();
		giveLifestoneButton.setText("Give lifestone to Pokemon");
		giveLifestoneButton.setPrefHeight(BUTTON_HEIGHT);
		giveLifestoneButton.setPrefWidth(200);
		giveLifestoneButton.setOnAction(buttonListener);
		
		backButton2 = new Button();
		backButton2.setText("Back");
		backButton2.setPrefHeight(BUTTON_HEIGHT);
		backButton2.setPrefWidth(BUTTON_WIDTH);
		backButton2.setOnAction(buttonListener);
		
		giveLifestonePanel.add(backButton2, 0, 0);
		giveLifestonePanel.add(giveLifestoneButton, 1, 0);
		
		listPanel.add(pokemonList, 0, 0);
		listPanel.add(iv1, 1, 0);
		listPanel.add(desc, 2, 0);
		
		this.getChildren().remove(buttonPanel);
		this.setBottom(giveLifestonePanel);
		this.setCenter(listPanel);
	}
	
	private void setSafariBallView() {
		safariBallPanel = new GridPane();
		GridPane listPanel = new GridPane();
		ButtonListener buttonListener = new ButtonListener();
		pokemonList = setList();
		
		Image potion = new Image("Images/safariBall.png");
		ImageView iv1 = new ImageView();
		iv1.setImage(potion);
		iv1.setFitWidth(100);
		iv1.setFitHeight(100);
		
		Label desc = new Label();
		//desc.setFont(value);
		desc.setText("Safari Balls are used to capture Pokemon in the Safari zone");
		desc.setWrapText(true);
		
		safariBallPanel.setHgap(HGAP);
		safariBallPanel.setVgap(VGAP);
		safariBallPanel.setPadding(new Insets(10,10,10,10));	
		safariBallPanel.setBackground(new Background(background));
		
		listPanel.setHgap(HGAP);
		listPanel.setVgap(VGAP);
		listPanel.setPadding(new Insets(10,10,10,10));
		listPanel.setBackground(new Background(background));
		
		backButton2 = new Button();
		backButton2.setText("Back");
		backButton2.setPrefHeight(BUTTON_HEIGHT);
		backButton2.setPrefWidth(BUTTON_WIDTH);
		backButton2.setOnAction(buttonListener);
		
		safariBallPanel.add(backButton2, 0, 0);
		
		listPanel.add(iv1, 0, 0);
		listPanel.add(desc, 1, 0);
		
		this.getChildren().remove(buttonPanel);
		this.setBottom(safariBallPanel);
		this.setCenter(listPanel);
	}
	
}
