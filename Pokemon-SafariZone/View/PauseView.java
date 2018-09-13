package View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

import Controller.PokemonController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Pokemon.Pokemon;
import model.Trainer.Trainer;

public class PauseView extends BorderPane {
	private static final int BUTTON_HEIGHT = 80;
	private static final int BUTTON_WIDTH = 290;
	private static final int VGAP = 20;
	private static final int HGAP = 20;
	
	private GridPane panel;
	private Button escapeButton;
	private Button continueButton;
	private Button inventoryButton;
	private Button pokemonButton;
	private Button viewPokemonButton;
	private Button saveButton;
	private Trainer trainer;
	private Boolean pokemonView; 
	private ImageView pokemonImageView;
	private ListView<Pokemon> pokemonList;
	private PokemonController controller;
	private Stage s;
	private MediaPlayer mpButton;
	
	public PauseView(Trainer trainer, PokemonController controller, Stage s) {
		this.trainer = trainer;
		this.controller = controller;
		this.s = s;
		
		BackgroundSize  size = new BackgroundSize(650,650,false,false,false,true);
		BackgroundImage background = new BackgroundImage(new Image("Images/Pokemon/grey.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, size);
		
		panel = new GridPane();
		panel.setVgap(VGAP);
		panel.setHgap(HGAP);
		panel.setPadding(new Insets(20, 30, 20, 30));
		panel.setBackground(new Background(background));
		
		ButtonListener buttonListener = new ButtonListener();
		
		escapeButton = new Button();
		escapeButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		escapeButton.setText("Use Escape Rope (Quit)");
		escapeButton.setOnAction(buttonListener);
		
		continueButton = new Button();
		continueButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		continueButton.setText("Continue");
		continueButton.setOnAction(buttonListener);
		
		inventoryButton = new Button();
		inventoryButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		inventoryButton.setText("Inventory");
		inventoryButton.setOnAction(buttonListener);
		
		pokemonButton = new Button();
		pokemonButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		pokemonButton.setText("Pokemon");
		pokemonButton.setOnAction(buttonListener);
		
		saveButton = new Button();
		saveButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		saveButton.setText("Save Game");
		saveButton.setOnAction(buttonListener);
		
		viewPokemonButton = new Button();
		viewPokemonButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		viewPokemonButton.setText("View stats");
		viewPokemonButton.setOnAction(buttonListener);
		
		panel.add(continueButton, 0, 0);
		panel.add(inventoryButton, 0, 1);
		panel.add(pokemonButton, 0, 2);
		panel.add(saveButton, 0, 3);
		panel.add(escapeButton, 0, 4);
		
		
		this.setCenter(panel);
	}
	
	private class ButtonListener implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
			final String resource = getClass().getResource("/Sounds/button.wav").toString();
			final Media media = new Media(resource);
		    mpButton = new MediaPlayer(media);
		    mpButton.play();
		    
			if(e.getSource() == pokemonButton) {
				s.hide();
				controller.setSceneToPokemon();
			}
			
			else if(e.getSource() == continueButton) {
				s.hide();
				controller.setToMapScene();
			}
			else if(e.getSource() == inventoryButton) {
				s.hide();
				controller.setSceneToInventory();
			}
			else if(e.getSource() == escapeButton) {
				controller.setSceneToEnd();
			}
			
			else if(e.getSource() == saveButton) {
				try {
					FileOutputStream fileOut = new FileOutputStream("Images/Pokemon/save");
					ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
					
					objectOut.writeObject(trainer);
					
					objectOut.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				System.out.println("Game Saved, Thanks for Playing!");
				controller.setSceneToEnd();
			}
		}
	}
	
	private void setPokemonView() {
		pokemonList = setPokemonList();
		pokemonImageView = new ImageView();
		panel.add(pokemonList, 0, 0);
		panel.add(viewPokemonButton, 0, 1);
	}
	
	private ListView<Pokemon> setPokemonList(){
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
}
