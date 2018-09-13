package view;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Observer;

import client.ICritterClient;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import model.Owner;
import model.Treat;

//Written by Nick Forbes
public class TreatView extends BorderPane implements Observer {
	private static final int HGAP = 2;
	private static final int VGAP = 2;
	private static final int BUTTON_WIDTH = 550;
	private static final int BUTTON_HEIGHT = 90;
	private ListView<Treat> purchasedTreats;
	private Owner owner;
	private Text credits;
	private ObjectOutputStream outputStream;
	GridPane treatView;
	
	public TreatView(Owner owner, ObjectOutputStream outputStream) {
		this.owner = owner;
		treatView = new GridPane();
		treatView.setHgap(HGAP);
		treatView.setVgap(VGAP);
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
		buttons[0][1].setText("Give Treat");
		buttons[1][0].setText("Buy Fancy Treat");
		buttons[1][1].setText("Buy Cheap Treat");
		treatView.add(buttons[0][1], 1, 1);
		treatView.add(buttons[1][0], 0, 2);
		treatView.add(buttons[1][1], 1, 2);
		credits = new Text(0,0,"Credits: "+ owner.getCredits());
		BorderPane.setAlignment(credits, Pos.TOP_LEFT);
		treatView.add(credits, 0,0);
		this.setCenter(treatView);
		ListView<Treat> purchasedTreats = treatList();
		treatView.add(purchasedTreats, 0, 1);
	}
	private ListView<Treat> treatList() {
		purchasedTreats = new ListView<Treat>();
		purchasedTreats.setCellFactory(new Callback<ListView<Treat>, ListCell<Treat>>(){

            @Override
            public ListCell<Treat> call(ListView<Treat> p) {
                
                ListCell<Treat> cell = new ListCell<Treat>(){

                    @Override
                    protected void updateItem(Treat t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.toString());
                        }
                    }

                };
                
                return cell;
            }

        });
		purchasedTreats.setItems(FXCollections.observableArrayList(owner.listTreats()));
		purchasedTreats.setPrefHeight(90);
		
		return purchasedTreats;
	}
	@Override
	public void update(Observable o, Object arg) {
		owner = (Owner) o;
		treatView.getChildren().remove(purchasedTreats);
		purchasedTreats = treatList();
		treatView.add(purchasedTreats, 0, 1);
		credits.setText("Credits: "+ owner.getCredits());
		
		
	}
	
private class ButtonListener implements EventHandler<ActionEvent> {
		
		@Override
		public void handle(ActionEvent e) {
			String text = ((Button) e.getSource()).getText();
			if (text.equals("Give Treat")) {
				owner.giveTreat(purchasedTreats.getSelectionModel().getSelectedItem());
				ICritterClient.sendOwner(owner);
			}
			else if (text.equals("Buy Fancy Treat")) {
				owner.buyFancyTreat("Fancy Treat");
				ICritterClient.sendOwner(owner);
			}
			else if (text.equals("Buy Cheap Treat")) {
				owner.buyCheapTreat("Cheap Treat");
				ICritterClient.sendOwner(owner);
			}
		}
	}
}
