package TrainerView;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Map.Map;

public class TrainerView {
    private Image IMAGE = new Image("file:images/trainer_sheet.png");
    
    private ImageView imageView;

    //walk downwards
    private int COLUMNS;
    private int COUNT;
    private double OFFSET_X;
    private double OFFSET_Y;
    private double WIDTH;
    private double HEIGHT;
    
    //Trainer location
    private double xCoord;
    private double yCoord;

    public TrainerView() {
    	this.xCoord = 20; //starter position
    	this.yCoord = 10;
    	setStandingSouth();
    	
        imageView = new ImageView(IMAGE);
        imageView.setFitHeight(30);
        imageView.setFitWidth(40);
        setAnimation();
    }
    
    public void setAnimation() {
    	imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        Animation animation = new SpriteAnimation(
                imageView,
                Duration.millis(500),
                COUNT, COLUMNS,
                OFFSET_X, OFFSET_Y,
                WIDTH, HEIGHT
        );
        animation.setCycleCount(1);
        animation.play();
    }
    public void setStandingSouth() {
    	COLUMNS  =  1;
        COUNT    =  1;
        OFFSET_X =  0;
        OFFSET_Y =  0;
        WIDTH    = 28.6428;
        HEIGHT   = 29.25;
    }
    public void setStandingNorth() {
    	COLUMNS  =  1;
        COUNT    =  1;
        OFFSET_X = 60;
        OFFSET_Y =  0;
        WIDTH    = 30;
        HEIGHT   = 30;
    }
    public void setStandingEast() {
    	COLUMNS  =  1;
        COUNT    =  1;
        OFFSET_X = 90;
        OFFSET_Y =  0;
        WIDTH    = 30;
        HEIGHT   = 30;
    }
    public void setStandingWest() {
    	COLUMNS  =  1;
        COUNT    =  1;
        OFFSET_X = 30;
        OFFSET_Y =  0;
        WIDTH    = 30;
        HEIGHT   = 30;
    }
    public void setWalkingSouth() {
    	COLUMNS  =  1;
        COUNT    =  5;
        OFFSET_X =  0;
        OFFSET_Y = 30;
        WIDTH    = 30;
        HEIGHT   = 30;
    }
    public void setWalkingNorth() {
    	COLUMNS  =  1;
        COUNT    =  5;
        OFFSET_X = 60;
        OFFSET_Y = 30;
        WIDTH    = 30;
        HEIGHT   = 30;
    }
    public void setWalkingEast() {
    	COLUMNS  =  1;
        COUNT    =  5;
        OFFSET_X = 90;
        OFFSET_Y = 30;
        WIDTH    = 30;
        HEIGHT   = 30;
    }
    public void setWalkingWest() {
    	COLUMNS  =  1;
        COUNT    =  5;
        OFFSET_X = 30;
        OFFSET_Y = 30;
        WIDTH    = 30;
        HEIGHT   = 30;
    }
    
    public Image getTrainerImage() {
    	return imageView.getImage();
    }
    public ImageView getTrainerImageView() {
    	return imageView;
    }
    public double getXCoord() {
    	return xCoord;
    }
    public double getYCoord() {
    	return yCoord;
    }
    public void changeYCoord(double val) {
    	this.yCoord += val;
    }
    public void changeXCoord(double val) {
    	this.xCoord += val;
    }
    
    
}