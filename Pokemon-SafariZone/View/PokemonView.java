package View;


import Controller.PokemonController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

public class PokemonView extends BorderPane{
	private static final int BUTTON_HEIGHT = 80;
	private static final int BUTTON_WIDTH = 290;
	private static final int HGAP = 20;
	private static final int VGAP = 20;
	
	private Trainer trainer;
	private PokemonController controller;
	private GridPane panel;
	private GridPane buttons, listPane;
	private ListView<Pokemon> pokemonList;
	private ImageView pokemonImageView;
	private Button backButton, setActiveButton;
	private Stage s;
	private MediaPlayer mpButton;
	
	public PokemonView(Trainer trainer, PokemonController controller, Stage s) {
		this.trainer = trainer;
		this.controller = controller;
		this.s = s;
		
		BackgroundSize  size = new BackgroundSize(650,650,false,false,false,true);
		BackgroundImage background = new BackgroundImage(new Image("Images/Pokemon/grey.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, size);
		
		panel = new GridPane();
		panel.setBackground(new Background(background));
		panel.setPadding(new Insets(30,30,30,30));
		
		buttons = new GridPane();
		buttons.setBackground(new Background(background));
		pokemonList = setList();
		listPane = new GridPane();
		listPane.setBackground(new Background(background));
		listPane.setPadding(new Insets(20,20,20,20));
		listPane.setVgap(VGAP);
		
		panel.setHgap(HGAP);
		panel.setVgap(VGAP);
		panel.setPadding(new Insets(20,20,20,20));
		
		buttons.setHgap(HGAP);
		buttons.setVgap(VGAP);
		buttons.setPadding(new Insets(20,20,20,20));
		
		listPane.add(pokemonList, 0, 0);
		
		backButton = new Button();
		backButton.setText("Back");
		backButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		
		
		setActiveButton = new Button();
		setActiveButton.setText("Set as primary Pokemon");
		setActiveButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		
		ButtonListener buttonListener = new ButtonListener();
		backButton.setOnAction(buttonListener);
		setActiveButton.setOnAction(buttonListener);
		
		buttons.add(backButton, 0, 0);
		buttons.add(setActiveButton, 1, 0);
		
		this.setCenter(panel);
		this.setBottom(buttons);
		this.setLeft(listPane);
	}
	
	public void clearPanel() {
		Image blankImage = new Image("Images/Pokemon/grey.jpg");
		ImageView iv1 = new ImageView();
		ImageView iv2 = new ImageView();
		ImageView iv3 = new ImageView();
		ImageView iv4 = new ImageView();
		iv1.setImage(blankImage);
		iv1.setFitWidth(310);
		iv1.setFitHeight(310);
		iv2.setImage(blankImage);
		iv2.setFitWidth(100);
		iv2.setFitHeight(30);
		iv3.setImage(blankImage);
		iv3.setFitWidth(100);
		iv3.setFitHeight(30);
		iv4.setImage(blankImage);
		iv4.setFitWidth(100);
		iv4.setFitHeight(30);
		panel.add(iv1, 1, 0);
		panel.add(iv2, 0, 1);
		panel.add(iv3, 0, 2);
		panel.add(iv4, 0, 3);
		
		listPane.add(iv2, 0, 1);
		listPane.add(iv3, 0, 2);
		listPane.add(iv4, 0, 3);
		
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
			
			if(e.getSource() == setActiveButton) {
				trainer.changeActivePokemon(pokemonList.getSelectionModel().getSelectedItem());
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
                        	Image im1 = new Image("Images/Pokemon/" + pokemon.getName() + ".png");
                        	//Image im1 = new Image("Images/Pokemon/Bulbasaur.gif");
                        	ImageView iv1 = new ImageView(im1);
                        	iv1.setFitWidth(150);
                        	iv1.setPreserveRatio(true);
                        	iv1.setFitHeight(30);
                        	iv1.setFitWidth(30);
                            setText(pokemon.getName());
                            setGraphic(iv1);
                        }
                    }
                };
                
                cell.setOnMousePressed((MouseEvent event) -> {
					Pokemon pokemon = pokemonList.getSelectionModel().getSelectedItem();
					
					String name = pokemon.getName();
					//String imageFile = "Images/Pokemon/" + name + "_hd.png";
					//String imageFile = "Images/Pokemon/Bulbasaur.gif";
					String imageFile = "Images/Pokemon/" + name + ".gif";
					pokemonImageView = new ImageView();
					pokemonImageView.setImage(new Image(imageFile));
					pokemonImageView.setFitWidth(150);
                	pokemonImageView.setPreserveRatio(true);
					
					Label hp = new Label();
					hp.setText("HP: " + pokemon.getHp());
					
					Label attack = new Label();
					attack.setText("Attack: " + pokemon.getAttack());
					
					Label defense = new Label();
					defense.setText("Defense: " + pokemon.getDefense());
					
					clearPanel();
					panel.add(pokemonImageView, 1, 0);
					listPane.add(hp, 0, 1);
					listPane.add(attack, 0, 2);
					listPane.add(defense, 0, 3);
                });
                
                return cell;
            }

        });
		
		list.setItems(FXCollections.observableArrayList(trainer.getPokemon()));
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		return list;
		
	}
	
	private class ListListener implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
