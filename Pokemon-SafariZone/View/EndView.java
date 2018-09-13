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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Pokemon.Pokemon;
import model.Trainer.Trainer;
import javafx.scene.layout.Background;

public class EndView extends BorderPane{
	private static final int BUTTON_HEIGHT = 80;
	private static final int BUTTON_WIDTH = 290;
	
	GridPane panel, bottomPanel, topPanel;
	Button endButton;
	Trainer trainer;
	private ListView<Pokemon> pokemonList;
	private ImageView pokemonImageView;
	private PokemonController controller;
	
	
	public EndView(PokemonController controller, Trainer trainer) {
		this.controller = controller;
		this.trainer = trainer;
		
		BackgroundSize  size = new BackgroundSize(650,650,false,false,false,true);
		BackgroundImage background = new BackgroundImage(new Image("Images/Pokemon/grey.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, size);
		
		panel = new GridPane();
		panel.setPadding(new Insets(30, 30, 30, 30));
		panel.setBackground(new Background(background));
		
		bottomPanel = new GridPane();
		bottomPanel.setPadding(new Insets(30, 30, 30, 30));
		bottomPanel.setBackground(new Background(background));
		
		topPanel = new GridPane();
		topPanel.setPadding(new Insets(30, 30, 0, 30));
		topPanel.setBackground(new Background(background));
		
		ButtonListener buttonListener = new ButtonListener();
		
		endButton = new Button();
		endButton.setText("End");
		endButton.setPrefHeight(BUTTON_HEIGHT);
		endButton.setPrefWidth(BUTTON_WIDTH);
		endButton.setOnAction(buttonListener);
		
		pokemonList = setList();
		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		
		Text text = new Text();
		text.setText("Congratulations! You caught " + trainer.getPokemon().size() + " Pokemon! Thanks for Playing!");
		text.setFont(Font.font(20));
		text.setEffect(ds);
		
		
		panel.add(pokemonList, 0, 0);
		bottomPanel.add(endButton, 0, 0);
		topPanel.add(text, 0, 0);
		
		this.setCenter(panel);
		this.setBottom(bottomPanel);
		this.setTop(topPanel);
		
	}
	
	private class ButtonListener implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent e) {
		    
			if(e.getSource() == endButton) {
				controller.end();
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
                        	ImageView iv1 = new ImageView(im1);
                        	iv1.setFitWidth(30);
                        	iv1.setFitHeight(30);
                            setText(pokemon.getName());
                            setGraphic(iv1);
                        }
                    }
                };
                
                cell.setOnMousePressed((MouseEvent event) -> {
					Pokemon pokemon = pokemonList.getSelectionModel().getSelectedItem();
					
					String name = pokemon.getName();
					String imageFile = "Images/Pokemon/" + name + "_hd.png";
					pokemonImageView = new ImageView();
					pokemonImageView.setImage(new Image(imageFile));
					pokemonImageView.setFitWidth(300);
					pokemonImageView.setPreserveRatio(true);
					
					Label hp = new Label();
					hp.setText("HP: " + pokemon.getHp());
					
					Label attack = new Label();
					attack.setText("Attack: " + pokemon.getAttack());
					
					Label defense = new Label();
					defense.setText("Defense: " + pokemon.getDefense());
					
					clearPanel();
					panel.add(pokemonImageView, 1, 0);
					panel.add(hp, 0, 1);
					panel.add(attack, 0, 2);
					panel.add(defense, 0, 3);
                });
                
                return cell;
            }

        });
		
		list.setItems(FXCollections.observableArrayList(trainer.getPokemon()));
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		return list;
		
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
	}
}
