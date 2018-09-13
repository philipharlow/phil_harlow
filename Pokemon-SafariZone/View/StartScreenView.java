package View;

import Controller.PokemonController;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class StartScreenView extends BorderPane{
	
	private PokemonController controller;
	private GridPane panel;
	
	public StartScreenView(PokemonController controller) {
		
		this.controller = controller;
		
		BackgroundSize  size = new BackgroundSize(650,650,false,false,false,true);
		BackgroundImage background = new BackgroundImage(new Image("Images/start_screen.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, size);
		
		panel = new GridPane();
		panel.setBackground(new Background(background));
		panel.setHgap(150);
		panel.setPadding(new Insets(25,50,75,50));
		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		
		Text startText = new Text();
		startText.setText("Press Spacebar To Begin");
		startText.setFont(Font.font(20));
		startText.setEffect(ds);
		
		Image logo = new Image("Images/pokemonLogo.png");
		ImageView iv1 = new ImageView(logo);
		iv1.setFitHeight(150);
		iv1.setPreserveRatio(true);
		
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), startText);
		fadeTransition.setFromValue(1.0);
		fadeTransition.setToValue(0.0);
		fadeTransition.setCycleCount(Animation.INDEFINITE);
		fadeTransition.play();
		
		panel.add(startText, 0, 0);
		panel.add(iv1, 1, 0);
		
		this.setCenter(panel);
	}
}
